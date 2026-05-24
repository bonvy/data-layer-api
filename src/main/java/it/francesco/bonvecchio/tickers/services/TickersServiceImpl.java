package it.francesco.bonvecchio.tickers.services;

import it.francesco.bonvecchio.tickers.models.domain.FetchTickersResult;
import it.francesco.bonvecchio.tickers.models.domain.SearchTickers;
import it.francesco.bonvecchio.tickers.models.domain.Ticker;
import it.francesco.bonvecchio.tickers.ports.FetchTickersPort;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
class TickersServiceImpl implements TickersService{

    private final FetchTickersPort fetchTickersPort;

    public TickersServiceImpl(FetchTickersPort fetchTickersPort) {
        this.fetchTickersPort = fetchTickersPort;
    }


    @Override
    public FetchTickersResult fetchTickers(SearchTickers searchTickers) {
        return fetchTickersPort.fetchTickers(searchTickers);
    }
}
