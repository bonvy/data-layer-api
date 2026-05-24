package it.francesco.bonvecchio.tickers.models.dto.response;

import it.francesco.bonvecchio.core.utils.markets.Market;

public record RetrivedTickerDto(boolean active,
                                String cik,
                                String currencyName,
                                String lastUpdatedUtc,
                                String locale,
                                Market market,
                                String name,
                                String primaryExchange,
                                String ticker,
                                String type) {
}
