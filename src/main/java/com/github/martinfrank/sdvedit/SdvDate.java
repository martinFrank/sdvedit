package com.github.martinfrank.sdvedit;

public class SdvDate {

    public final int day; //1..28
    public final int year; //1..99
    public final SdvSeason sdvSeason;

    public SdvDate(int day, int year, SdvSeason sdvSeason) {
        this.day = day;
        this.year = year;
        this.sdvSeason = sdvSeason;
    }


    @Override
    public String toString() {
        return "day " + day + " of season '" + sdvSeason.getDescription() + "' in year " + year;
    }

    public String shortDate() {
        return day + ". " + sdvSeason.getDescription() + " year " + year;
    }

}
