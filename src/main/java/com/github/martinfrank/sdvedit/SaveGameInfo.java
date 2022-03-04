package com.github.martinfrank.sdvedit;

import java.io.File;

public class SaveGameInfo extends SdvDocument {

    private final SdvDocumentProperty name = new SdvDocumentProperty("NAME", "/Farmer/name");
    private final SdvDocumentProperty money = new SdvDocumentProperty("MONEY", "/Farmer/money");

    private final SdvDocumentProperty day = new SdvDocumentProperty("DAY", "/Farmer/dayOfMonthForSaveGame");
    private final SdvDocumentProperty year = new SdvDocumentProperty("YEAR", "/Farmer/yearForSaveGame");
    private final SdvDocumentProperty season = new SdvDocumentProperty("SEASON", "/Farmer/seasonForSaveGame");


    public SaveGameInfo(File file) {
        super(file);
        register(name);
        register(money);
        register(day);
        register(year);
        register(season);
    }

    public String getName() {
        return name.getCurrentValue();
    }

    public void setName(String newValue) {
        name.setCurrent(newValue);
    }

    public int getMoney() {
        return Integer.parseInt(money.getCurrentValue());
    }

    public void setMoney(int newValue) {
        money.setCurrent(Integer.toString(newValue));
    }

    public void setDay(int value) {
        day.setCurrent(Integer.toString(value));
    }

    public void setYear(int value) {
        year.setCurrent(Integer.toString(value));
    }

    public void setSeason(SdvSeason value) {
        season.setCurrent(Integer.toString(value.getIndex()));
    }
}
