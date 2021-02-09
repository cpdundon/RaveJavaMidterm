package com.example.ravejavamidterm.model;

public class Country {
    private String name;
    private String capital;
    private String region;
    private String subregion;
    private Long population;

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public String getRegion() {
        return region;
    }

    public String getSubregion() {
        return subregion;
    }

    public Long getPopulation() {
        return population;
    }

    public Country(String name, String capital, String region, String subregion, Long population) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
    }
}