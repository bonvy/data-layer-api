package it.francesco.bonvecchio.core.utils.markets;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Market {
    STOCKS("stocks"),
    CRYPTO("crypto"),
    FOREX("fx"),
    OTC("otc"),
    INDICES("indices");

    private final String value;

    Market(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Market fromString(String value) {
        return Arrays.stream(Market.values())
                .filter(m -> m.value.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid market: " + value + ". Allowed: " +
                                Arrays.toString(Market.values())
                ));
    }
}