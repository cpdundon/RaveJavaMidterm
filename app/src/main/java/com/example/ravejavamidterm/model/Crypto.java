package com.example.ravejavamidterm.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Crypto {
    public Crypto(Ticker ticker, Long timestamp, Boolean success, String error) {
        this.ticker = ticker;
        this.error = error;
        this.timestamp = timestamp;
        this.success = success;
    }

    @SerializedName("ticker")
    public Ticker ticker;
    @SerializedName("timestamp")
    public Long timestamp;
    @SerializedName("success")
    public Boolean success;
    @SerializedName("error")
    public String error;


    public class Market {

        public Market (String market, String price, Double volume) {
            this.market = market;
            this.price = price;
            this.volume = volume;
        }

        @SerializedName("market")
        public String market;
        @SerializedName("price")
        public String price;
        @SerializedName("volume")
        public Double volume;

        //public String coinName;

    }

    public class Ticker {

        public Ticker(String base, String target, String price, String volume, String change,
                      List<Market> markets) {
            this.markets = markets;
            this.base = base;
            this.target = target;
            this.price = price;
            this.volume = volume;
            this.change = change;
        }

        @SerializedName("base")
        public String base;
        @SerializedName("target")
        public String target;
        @SerializedName("price")
        public String price;
        @SerializedName("volume")
        public String volume;
        @SerializedName("change")
        public String change;
        @SerializedName("markets")
        public List<Market> markets = null;
    }
}