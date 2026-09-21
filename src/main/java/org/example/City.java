package org.example;

public enum City {
    Tambov(1, 52.6667, 41.3333),
    Moscow(2 , 55.7558, 37.6173),
    Kazan(3,55.4724,49.0748),
    Voronesh(4,51.660781,39.200296),
    Volgograd(5, 48.7117, 44.5139);
    private final int number;
    private final Double lons;
    private final Double lots;
    City(int number,Double lons, Double  lots)
    {
        this.number = number;
        this.lons = lons;
        this.lots = lots;
    }

    public Double getLons() {
        return lons;
    }

    public Double getLots() {
        return lots;
    }

    public int getNumber() {
        return number;
    }
}
