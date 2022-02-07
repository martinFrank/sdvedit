package com.github.martinfrank.sdvedit;


import java.io.File;

public class SdvFileSet {

    private static final String SAVE_GAME_INFO = "SaveGameInfo";
    //see https://www.journaldev.com/901/modify-xml-file-in-java-dom-parser

    private final File directory;
    final GameContent gameContent; //visible for testing
    final SaveGameInfo saveGameInfo; //visible for testing

    private boolean isDirty;

    public SdvFileSet(File directory) {
        this.directory = directory;
        gameContent = new GameContent(new File(directory, directory.getName()));
        gameContent.readProperties();
        saveGameInfo = new SaveGameInfo(new File(directory, SAVE_GAME_INFO));
        saveGameInfo.readProperties();
    }

    public File getDirectory(){
        return directory;
    }

    @Override
    public String toString() {
        return directory.getName();
    }

    public String getDirectoryName(){
        return directory.getName();
    }

    public String getName(){
        return saveGameInfo.getName();
    }

    public int getMoney(){
        return saveGameInfo.getMoney();
    }

    //<hasRustyKey>true</hasRustyKey>
    //        <hasSkullKey>true</hasSkullKey>

    public SdvDate getDate(){
        return gameContent.getDate();
    }

    public void setMoney(int money) {
        isDirty = true;
        saveGameInfo.setMoney(money);
        gameContent.setMoney(money);
    }

    public void saveChanges() {
        if(isDirty) {
            saveGameInfo.saveChanges();
            gameContent.saveChanges();
            isDirty = false;
        }
    }
}
