package it.francesco.bonvecchio.tickers.ports;

import it.francesco.bonvecchio.tickers.models.domain.FetchTickersResult;
import it.francesco.bonvecchio.tickers.models.domain.SearchTickers;

public interface FetchTickersPort {

    FetchTickersResult fetchTickers(SearchTickers searchTickers);
}
