package com.ibn.firnas.dto.googleFlights;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;
public record PriceInsights(@JsonProperty("lowest_price") Integer lowestPrice,@JsonProperty("price_level") String priceLevel,
                            @JsonProperty("typical_price_range") Integer [] typicalPriceRange,
                            @JsonProperty("price_history") Integer [][] priceHistory) {
}
