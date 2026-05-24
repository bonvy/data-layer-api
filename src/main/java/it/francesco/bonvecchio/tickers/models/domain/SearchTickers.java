package it.francesco.bonvecchio.tickers.models.domain;

import it.francesco.bonvecchio.core.utils.markets.Market;

public record SearchTickers(String ticker,
                            String search,
                            Market market,
                            String type,
                            boolean active) {
}
