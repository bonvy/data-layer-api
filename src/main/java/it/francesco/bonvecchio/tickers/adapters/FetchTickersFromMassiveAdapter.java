package it.francesco.bonvecchio.tickers.adapters;

import it.francesco.bonvecchio.api.client.com.massive.MassiveApiClient;
import it.francesco.bonvecchio.api.client.com.massive.models.dto.response.MassiveTickersResponseDto;
import it.francesco.bonvecchio.tickers.mappers.TickersMapper;
import it.francesco.bonvecchio.tickers.models.domain.FetchTickersResult;
import it.francesco.bonvecchio.tickers.models.domain.SearchTickers;
import it.francesco.bonvecchio.tickers.ports.FetchTickersPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ApplicationScoped
class FetchTickersFromMassiveAdapter implements FetchTickersPort {

    private final MassiveApiClient massiveApiClient;
    private final TickersMapper tickersMapper;
    private final static Logger logger = LoggerFactory.getLogger(FetchTickersFromMassiveAdapter.class);
    public FetchTickersFromMassiveAdapter(@RestClient MassiveApiClient massiveApiClient, TickersMapper tickersMapper) {
        this.massiveApiClient = massiveApiClient;
        this.tickersMapper = tickersMapper;
    }

    @Override
    public FetchTickersResult fetchTickers(SearchTickers searchTickers) {
        try{
            MassiveTickersResponseDto responseDto = massiveApiClient.fetchTickers(tickersMapper.mapMassiveRequestParamDto(searchTickers));
            return new FetchTickersResult.Found(tickersMapper.mapTickersMassiveResponseDto(responseDto.results()));
        }catch(WebApplicationException e){
            String body = e.getResponse().readEntity(String.class);
            logger.error("Status: {}, Body: {}", e.getResponse().getStatus(), body);
            return new FetchTickersResult.BadRequest(searchTickers);
        }

    }
}
