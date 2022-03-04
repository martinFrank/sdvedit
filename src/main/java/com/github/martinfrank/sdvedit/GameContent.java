package com.github.martinfrank.sdvedit;

import java.io.File;

public class GameContent extends SdvDocument{

//    private static final String SAVEGAME_PLAYER_NAME = "/SaveGame/player/name";
//    private static final String SAVEGAME_PLAYER_MONEY = "/SaveGame/player/money";

    private final SdvDocumentProperty name = new SdvDocumentProperty("NAME", "/SaveGame/player/name");
    private final SdvDocumentProperty money = new SdvDocumentProperty("MONEY", "/SaveGame/player/money");

    private final SdvDocumentProperty day = new SdvDocumentProperty("DAY", "/SaveGame/dayOfMonth");
    private final SdvDocumentProperty year = new SdvDocumentProperty("YEAR", "/SaveGame/year");
    private final SdvDocumentProperty season = new SdvDocumentProperty("SEASON", "/SaveGame/currentSeason");

    private final SdvDocumentProperty dayInfo = new SdvDocumentProperty("DAY_INFO", "/SaveGame/player/dayOfMonthForSaveGame");
    private final SdvDocumentProperty yearInfo = new SdvDocumentProperty("YEAR_INFO", "/SaveGame/player/yearForSaveGame");
    private final SdvDocumentProperty seasonInfo = new SdvDocumentProperty("SEASON_INFO", "/SaveGame/player/seasonForSaveGame");

    private final SdvDocumentProperty skullKey = new SdvDocumentProperty("SKULL_KEY", "/SaveGame/player/hasSkullKey");


    public GameContent(File file) {
        super(file);
        register(name);
        register(money);
        register(day);
        register(year);
        register(season);
        register(dayInfo);
        register(yearInfo);
        register(seasonInfo);
        register(skullKey);
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
        money.setCurrent(""+newValue);
    }

    public SdvDate getDate() {
        int dayValue = Integer.parseInt(day.getCurrentValue());
        int yearValue = Integer.parseInt(year.getCurrentValue());
        SdvSeason seasonValue = SdvSeason.byDescription(season.getCurrentValue());
        return new SdvDate(dayValue, yearValue, seasonValue);
    }

    public void setDay(int value) {
        day.setCurrent(Integer.toString(value));
        dayInfo.setCurrent(Integer.toString(value));
    }

    public void setYear(int value) {
        year.setCurrent(Integer.toString(value));
        yearInfo.setCurrent(Integer.toString(value));
    }

    public void setSeason(SdvSeason value) {
        season.setCurrent(value.getDescription());
        seasonInfo.setCurrent(Integer.toString(value.getIndex()));
    }

    public boolean hasSkullKey(){
        return Boolean.parseBoolean(skullKey.getCurrentValue());
    }

    public void setSkullKey(boolean value){
        skullKey.setCurrent(Boolean.toString(value));
    }
}
