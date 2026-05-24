package it.francesco.bonvecchio.api.client.com.massive;


import it.francesco.bonvecchio.api.client.com.massive.models.dto.request.MassiveRequestParamDto;
import it.francesco.bonvecchio.api.client.com.massive.models.dto.response.MassiveTickersResponseDto;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/reference")
@RegisterRestClient(configKey = "massive-api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface MassiveApiClient {

    @GET
    @Path("/tickers")
    MassiveTickersResponseDto fetchTickers(@BeanParam @Valid MassiveRequestParamDto massiveRequestParamDto);
}
