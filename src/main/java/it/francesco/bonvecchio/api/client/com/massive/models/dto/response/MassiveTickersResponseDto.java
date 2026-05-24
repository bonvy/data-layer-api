package it.francesco.bonvecchio.api.client.com.massive.models.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record MassiveTickersResponseDto(
        int count,
        @JsonProperty("next_url") String nextUrl,
        @JsonProperty("request_id") String requestId,
        List<TickerResultDto> results,
        String status
) {
    public record TickerResultDto(
            boolean active,
            String cik,
            @JsonProperty("composite_figi") String compositeFigi,
            @JsonProperty("currency_name") String currencyName,
            @JsonProperty("last_updated_utc") String lastUpdatedUtc,
            String locale,
            String market,
            String name,
            @JsonProperty("primary_exchange") String primaryExchange,
            @JsonProperty("share_class_figi") String shareClassFigi,
            String ticker,
            String type
    ) {}
}