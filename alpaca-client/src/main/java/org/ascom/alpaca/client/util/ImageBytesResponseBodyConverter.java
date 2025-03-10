package org.ascom.alpaca.client.util;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.ascom.alpaca.model.ImageArray;
import org.ascom.alpaca.response.AlpacaResponse;
import org.ascom.alpaca.response.ImageArrayResponse;
import retrofit2.Converter;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


/**
 * A custom response body converter that processes image data in the Alpaca ImageBytes format.
 * If the results don't have a media type of "application/imagebytes" format, if falls back to
 * JSON decoding via the standard Retrofit Jackson-based converter.
 *
 * @param <T> Generic type of the converted output, which should only be a ImageArrayResponse.
 */
public class ImageBytesResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Converter<ResponseBody, T> jacksonConverter;

    ImageBytesResponseBodyConverter(Converter<ResponseBody, T> jacksonConverter) {
        this.jacksonConverter = jacksonConverter;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T convert(ResponseBody response) throws IOException {
        MediaType mediaType = response.contentType();
        // If the device doesn't support ImageBytes encoding, we need to fall back to
        // JSON decoding.
        if (mediaType == null || !mediaType.equals(MediaType.get("application/imagebytes"))) {
            return jacksonConverter.convert(response);
        }
        try (response; DataInputStream is = new DataInputStream(response.byteStream())) {
            byte[] header = new byte[44];
            is.readFully(header);
            ByteBuffer buf = ByteBuffer.wrap(header).order(ByteOrder.LITTLE_ENDIAN);
            int metadataVersion = buf.getInt();
            int errorNumber = buf.getInt();
            int clientTransactionID = buf.getInt();
            int serverTransacitonID = buf.getInt();
            int dataStart = buf.getInt();
            int imageElementType = buf.getInt();
            int transmissionElementType = buf.getInt();
            int rank = buf.getInt();
            int dimension1 = buf.getInt();
            int dimension2 = buf.getInt();
            int dimension3 = buf.getInt();

            // If there is an error, the device may return some error text where the image would normally
            // be returned
            if (errorNumber != 0) {
                String errorString = new String(is.readAllBytes());
                new AlpacaResponse(clientTransactionID, serverTransacitonID, errorNumber, errorString);
            }

            int[][] imageArray = new int[dimension1][dimension2];
            for (int i = 0; i < dimension1; i++) {
                switch (transmissionElementType) {
                    case 1 -> { // short encoded result
                        byte[] rowBuf = new byte[dimension2 * 4];
                        is.readFully(rowBuf);
                        ByteBuffer rowBuffer = ByteBuffer.wrap(rowBuf).order(ByteOrder.LITTLE_ENDIAN);
                        for (int j = 0; j < dimension2; j++) {
                            imageArray[i][j] = rowBuffer.getShort();
                        }
                    }
                    case 2 -> { // int encoded result
                        byte[] rowBuf = new byte[dimension2 * 4];
                        is.readFully(rowBuf);
                        ByteBuffer rowBuffer = ByteBuffer.wrap(rowBuf).order(ByteOrder.LITTLE_ENDIAN);
                        for (int j = 0; j < dimension2; j++) {
                            imageArray[i][j] = rowBuffer.getInt();
                        }
                    }
                    case 6 -> { // byte encode result
                        byte[] rowBuf = new byte[dimension2];
                        is.readFully(rowBuf);
                        for (int j = 0; j < dimension2; j++) {
                            imageArray[i][j] = rowBuf[j] & 0xFF;
                        }
                    }
                    default -> throw new IOException("Unknown or unsupported transmissionElementType: " + transmissionElementType);
                }
            }
            return (T) new ImageArrayResponse(ImageArray.Type.Integer, ImageArray.Rank.SinglePlane, imageArray);
        }
    }
}
