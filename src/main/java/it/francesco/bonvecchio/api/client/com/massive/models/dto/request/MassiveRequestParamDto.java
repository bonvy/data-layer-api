package it.francesco.bonvecchio.api.client.com.massive.models.dto.request;

import it.francesco.bonvecchio.core.utils.markets.Market;
import jakarta.ws.rs.QueryParam;


public class MassiveRequestParamDto {

    @QueryParam("ticker")
    private String ticker;

    @QueryParam("search")
    private String search;

    @QueryParam("market")
    private Market market;

    @QueryParam("type")
    private String type;

    @QueryParam("active")
    private boolean active;

    public String getTicker() { return ticker; }
    public String getSearch() { return search; }
    public Market getMarket() { return market; }
    public String getType() { return type; }
    public boolean isActive() { return active; }

    public void setTicker(String ticker) { this.ticker = ticker; }
    public void setSearch(String search) { this.search = search; }
    public void setMarket(Market market) { this.market = market; }
    public void setType(String type) { this.type = type; }
    public void setActive(boolean active) { this.active = active; }
}
