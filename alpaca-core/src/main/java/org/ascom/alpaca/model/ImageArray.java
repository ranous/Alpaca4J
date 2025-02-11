package org.ascom.alpaca.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

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
}
