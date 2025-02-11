package org.ascom.alpaca.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.ascom.alpaca.model.ImageArray;

@SuppressWarnings("unused")
public class ImageArrayResponse extends AlpacaResponse {
    private ImageArray.Type type;
    private ImageArray.Rank rank;
    private int[][] value;

    public ImageArrayResponse() {
        super();
    }

    public ImageArrayResponse(ImageArray.Type type,
                              ImageArray.Rank rank,
                              int[][] value) {
        super();
        this.type = type;
        this.rank = rank;
        this.value = value;
    }

    public ImageArrayResponse(long clientTransactionID,
                              ImageArray.Type type,
                              ImageArray.Rank rank,
                              int[][] value) {
        super(clientTransactionID);
        this.type = type;
        this.rank = rank;
        this.value = value;
    }

    public ImageArrayResponse(long clientTransactionID, ImageArray imageArray) {
        super(clientTransactionID);
        this.type = imageArray.getType();
        this.rank = imageArray.getRank();
        this.value = imageArray.getValue();
    }

    @JsonProperty("Type")
    public ImageArray.Type getType() {
        return type;
    }

    public void setType(ImageArray.Type type) {
        this.type = type;
    }

    @JsonProperty("Rank")
    public ImageArray.Rank getRank() {
        return rank;
    }

    public void setRank(ImageArray.Rank rank) {
        this.rank = rank;
    }

    @JsonProperty("Value")
    public int[][] getValue() {
        return value;
    }

    public void setValue(int[][] value) {
        this.value = value;
    }
}
