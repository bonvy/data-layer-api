package it.francesco.bonvecchio.tickers;

import it.francesco.bonvecchio.tickers.mappers.TickersMapper;
import it.francesco.bonvecchio.tickers.models.domain.FetchTickersResult;
import it.francesco.bonvecchio.tickers.models.dto.request.TickerQueryParamDto;
import it.francesco.bonvecchio.tickers.services.TickersService;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("tickers/search")
@Produces(MediaType.APPLICATION_JSON)
class TickersResource {

    private final TickersService tickersService;
    private final TickersMapper tickersMapper;

    public TickersResource(TickersService tickersService, TickersMapper tickersMapper) {
        this.tickersService = tickersService;
        this.tickersMapper = tickersMapper;
    }

    @GET
    public Response retriveTickers(@BeanParam @Valid TickerQueryParamDto tickerQueryParamDto) {
        var searchTicker = tickersMapper.mapTickersQueryParamDto(tickerQueryParamDto);
        return switch (tickersService.fetchTickers(searchTicker)){
            case FetchTickersResult.Found(var tickers) -> Response.accepted(tickersMapper.mapRetrivedTickersDto(tickers)).build();
            case FetchTickersResult.BadRequest(var searchTickers) -> Response.status(Response.Status.BAD_REQUEST).build();
        };
    }
}
