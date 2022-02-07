package com.github.martinfrank.sdvedit;

import java.io.File;

public class GameContent extends SdvDocument{

//    private static final String SAVEGAME_PLAYER_NAME = "/SaveGame/player/name";
//    private static final String SAVEGAME_PLAYER_MONEY = "/SaveGame/player/money";

    private final SdvDocumentProperty name = new SdvDocumentProperty("NAME", "/SaveGame/player/name");
    private final SdvDocumentProperty money = new SdvDocumentProperty("MONEY", "/SaveGame/player/money");

    public GameContent(File file) {
        super(file);
        register(name);
        register(money);
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
        return null;
    }
}
