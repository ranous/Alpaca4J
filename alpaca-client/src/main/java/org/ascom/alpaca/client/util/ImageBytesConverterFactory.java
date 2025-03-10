package org.ascom.alpaca.client.util;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.ascom.alpaca.response.ImageArrayResponse;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * A custom {@link Converter.Factory} implementation for handling image data in response bodies
 * encoded in the Alpaca binary image bytes format. This factory is used to create a specialized
 * converter compatible with the "application/imagebytes" media type.  Not all Camera devices
 * implement the image bytes format, so this factory relies on an instance of {@link JacksonConverterFactory}
 * to handle standard JSON deserialization when the media type does not match "application/imagebytes."
 */
public class ImageBytesConverterFactory extends Converter.Factory {
    private static final MediaType mediaType = MediaType.get("application/imagebytes");
    private final JacksonConverterFactory jacksonConverterFactory;
    public static ImageBytesConverterFactory create(JacksonConverterFactory jacksonConverterFactory) {
        return new ImageBytesConverterFactory(jacksonConverterFactory);
    }

    private ImageBytesConverterFactory(JacksonConverterFactory jacksonConverterFactory) {
        this.jacksonConverterFactory = jacksonConverterFactory;;

    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type != ImageArrayResponse.class) {
            return null;
        }

        return new ImageBytesResponseBodyConverter<>(jacksonConverterFactory.responseBodyConverter(type, annotations, retrofit));
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        // We only receive them so no need to marshall them
        return null;
    }
}
