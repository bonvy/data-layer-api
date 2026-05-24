package it.francesco.bonvecchio.tickers.services;

import it.francesco.bonvecchio.tickers.models.domain.FetchTickersResult;
import it.francesco.bonvecchio.tickers.models.domain.SearchTickers;

public interface TickersService {

    FetchTickersResult fetchTickers(SearchTickers searchTickers);
}
