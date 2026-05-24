package it.francesco.bonvecchio.tickers.models.domain;

import java.util.List;

public sealed interface FetchTickersResult {

    record BadRequest(SearchTickers searchTickers) implements FetchTickersResult {}
    record Found(List<Ticker> tickers) implements FetchTickersResult {}

}
