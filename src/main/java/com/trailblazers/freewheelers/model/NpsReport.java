package com.trailblazers.freewheelers.model;

public class NpsReport {
    private int promoters;
    private int detractors;
    private int passives;
    private int total;

    public Double getPromotersPercentage() {
        return ((double) promoters / total) * 100;
    }

    public Double getDetractorsPercentage() {
        return ((double) detractors / total) * 100;
    }

    public Double getPassivesPercentage() {
        return ((double) passives / total) * 100;
    }

    public Double getNpsScore() {
        return (double) (promoters - detractors) / total * 100;
    }

    public int getTotal(){
        return total;
    }
}
