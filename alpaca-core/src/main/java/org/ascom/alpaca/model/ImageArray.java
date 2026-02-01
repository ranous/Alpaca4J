package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Objects;

/**
 * The Alpaca response for an image array. It contain an array of 32bit integers containing the pixel
 * values from the last exposure. This call can return either a 2 dimension (monochrome images) or 3
 * dimension (colour or multi-plane images) array of size NumX * NumY or NumX * NumY * NumPlanes.
 * Where applicable, the size of NumPlanes has to be determined by inspection of the returned array.
 *
 * When the SensorType is Monochrome, RGGB, CMYG, CMYG2 or LRGB, the array should have 2 dimensions.
 * For example, the returned array should appear as below if NumX = 7, NumY = 5 and Pxy represents the
 * pixel value at the zero based position x across and y down the image with the origin in the top left
 * corner of the image.
 *
 * The ImageArray can be inefficient to serialise and deserialise due to the large size of the array.
 * Alpaca as of version 7 provides the ImageBytes mechanism to transfer image data as raw binary data.
 * This is transaparent to the user as the binary data is marshalled into an ImageArray object.
 *
 * @see <a href="https://ascom-standards.org/api/#/Camera%20Specific%20Methods/get_camera__device_number__imagearray">A full description of is provided here</a>
 */
@SuppressWarnings("unused")
public class ImageArray {
    @SuppressWarnings("unused")
    public enum Type {
        Unknown(0), Short(1), Integer(2), Double(3);

        private final int type;

        Type(int type) {
            this.type = type;
        }

        @JsonValue
        public int getType() {
            return type;
        }

        @JsonCreator
        public static Type fromType(int type) {
            return switch (type) {
                case 0 -> Unknown;
                case 1 -> Short;
                case 2 -> Integer;
                case 3 -> Double;
                default -> throw new IllegalArgumentException("Cannot have a type of value " + type);
            };
        }
    }

    @SuppressWarnings("unused")
    public enum Rank {
        SinglePlane(2),
        MultiPlan(3);

        private int rank;

        Rank(int rank) {
            this.rank = rank;
        }

        @JsonValue
        public int getRank() {
            return rank;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }

        @JsonCreator
        public static Rank fromRank(int rank) {
            return switch (rank) {
                case 2 -> SinglePlane;
                case 3 -> MultiPlan;
                default -> throw new IllegalArgumentException("Cannot have a rank of value " + rank);
            };
        }
    }

    private Type type;
    private Rank rank;
    private int[][] value;

    public ImageArray(Type type,
                      Rank rank,
                      int[][] value) {
        this.type = type;
        this.rank = rank;
        this.value = value;
    }

    @JsonProperty("Type")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @JsonProperty("Rank")
    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    @JsonProperty("Value")
    public int[][] getValue() {
        return value;
    }

    public void setValue(int[][] value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ImageArray that = (ImageArray) o;
        return type == that.type && rank == that.rank && Objects.deepEquals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, rank, Arrays.deepHashCode(value));
    }
}
