package com.zhong.cardinals.sample.mode;

import java.io.Serializable;

/**
 * Created by 16570 on 2017/10/30.
 */

public class MarketItem implements Serializable {


    private double buy;
    private double closePrice;
    private double lastPrice;
    private boolean marketState;
    private double sell;
    private String symbol;
    private String symbolStr;
    private double volume;
    private double[] chart;

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public boolean isMarketState() {
        return marketState;
    }

    public void setMarketState(boolean marketState) {
        this.marketState = marketState;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbolStr() {
        return symbolStr;
    }

    public void setSymbolStr(String symbolStr) {
        this.symbolStr = symbolStr;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public double[] getChart() {
        return chart;
    }

    public void setChart(double[] chart) {
        this.chart = chart;
    }
}
