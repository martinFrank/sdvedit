package com.github.martinfrank.sdvedit;

public class SdvDate {

    //<currentSeason>spring</currentSeason>
    // <dayOfMonth>14</dayOfMonth>
    //    <year>3</year>
    public final int day;
    public final int year;
    public final SdvSeason sdvSeason;

    public SdvDate(int day, int year, SdvSeason sdvSeason) {
        this.day = day;
        this.year = year;
        this.sdvSeason = sdvSeason;
    }

    @Override
    public String toString() {
        return day + " of Season " + sdvSeason + "in year " + year;
    }

}
