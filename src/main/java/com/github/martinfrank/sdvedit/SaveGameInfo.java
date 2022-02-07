package com.github.martinfrank.sdvedit;

import java.io.File;

public class SaveGameInfo extends SdvDocument{

    private final SdvDocumentProperty name = new SdvDocumentProperty("NAME", "/Farmer/name");
    private final SdvDocumentProperty money = new SdvDocumentProperty("MONEY", "/Farmer/money");

    public SaveGameInfo(File file) {
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

}
