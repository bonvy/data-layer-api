package it.francesco.bonvecchio.tickers.mappers;

import it.francesco.bonvecchio.api.client.com.massive.models.dto.request.MassiveRequestParamDto;
import it.francesco.bonvecchio.api.client.com.massive.models.dto.response.MassiveTickersResponseDto;
import it.francesco.bonvecchio.core.utils.markets.Market;
import it.francesco.bonvecchio.tickers.models.domain.SearchTickers;
import it.francesco.bonvecchio.tickers.models.domain.Ticker;
import it.francesco.bonvecchio.tickers.models.dto.request.TickerQueryParamDto;
import it.francesco.bonvecchio.tickers.models.dto.response.RetrivedTickerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "jakarta")
public interface TickersMapper {


    SearchTickers mapTickersQueryParamDto(TickerQueryParamDto queryParamDto);

    MassiveRequestParamDto mapMassiveRequestParamDto(SearchTickers searchTickers);

    List<Ticker> mapTickersMassiveResponseDto(List<MassiveTickersResponseDto.TickerResultDto> tickerResultDtos);

    @Mapping(source = "market", target = "market")
    Ticker mapTicker(MassiveTickersResponseDto.TickerResultDto tickerResultDto);

    RetrivedTickerDto mapRetrivedTickerDto(Ticker ticker);

    List<RetrivedTickerDto> mapRetrivedTickersDto(List<Ticker> tickers);


    default Market mapMarket(String market) {
        return Market.fromString(market);
    }
}
