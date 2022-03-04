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

    //<Speed>5</Speed>
//    <stats><seedsSown>1523</seedsSown>
    //<hasRustyKey>true</hasRustyKey>
//    <farmName>Trollfest</farmName>
//        <favoriteThing>Suomi</favoriteThing>
//        <horseName>Fury</horseName>
//    <whichPetBreed>0</whichPetBreed>


//    <NPC p3:type="Cat">
//                    <name>Tiger</name>

    //<hasSkullKey>true</hasSkullKey>
    //<canUnderstandDwarves>false</canUnderstandDwarves>
    //<clubCoins>4790</clubCoins>
//    <farmingLevel>10</farmingLevel>
//        <miningLevel>9</miningLevel>
//        <combatLevel>9</combatLevel>
//        <foragingLevel>9</foragingLevel>
//        <fishingLevel>6</fishingLevel>
//        <luckLevel>0</luckLevel>
//    <maxStamina>338</maxStamina>
//        <maxItems>36</maxItems>
//        <resilience>4</resilience>
//        <attack>0</attack>
//        <immunity>4</immunity>
//    <attackIncreaseModifier>0.1</attackIncreaseModifier>
//        <knockbackModifier>0</knockbackModifier>
//        <weaponSpeedModifier>0</weaponSpeedModifier>
//        <critChanceModifier>0</critChanceModifier>
//        <critPowerModifier>-0.2</critPowerModifier>
//        <weaponPrecisionModifier>0</weaponPrecisionModifier>
    //<houseUpgradeLevel>3</houseUpgradeLevel>
//    <hasUnlockedSkullDoor>true</hasUnlockedSkullDoor>
//        <hasDarkTalisman>false</hasDarkTalisman>
//        <hasMagicInk>false</hasMagicInk>
//        <showChestColorPicker>false</showChestColorPicker>
//        <hasMagnifyingGlass>true</hasMagnifyingGlass>
//        <hasWateringCanEnchantment>false</hasWateringCanEnchantment>
//        <magneticRadius>256</magneticRadius>
//        <temporaryInvincibilityTimer>0</temporaryInvincibilityTimer>
//        <health>165</health>
//        <maxHealth>165</maxHealth>
//        <difficultyModifier>1</difficultyModifier>
//        <isMale>false</isMale>
//        <hasBusTicket>false</hasBusTicket>
//        <stardewHero>false</stardewHero>
//        <hasClubCard>true</hasClubCard>
//        <hasSpecialCharm>true</hasSpecialCharm>
//    <dailyLuck>0.092</dailyLuck>
//    <uniqueIDForThisGame>268385354</uniqueIDForThisGame>
//    <weddingToday>false</weddingToday>
//    <isRaining>false</isRaining>
//    <isDebrisWeather>false</isDebrisWeather>
//    <shippingTax>false</shippingTax>
//    <bloomDay>false</bloomDay>
//    <isLightning>false</isLightning>
//    <isSnowing>false</isSnowing>
//    <shouldSpawnMonsters>false</shouldSpawnMonsters>
    //

    public SdvDate getDate(){
        return gameContent.getDate();
    }

    public void setMoney(int money) {
        isDirty = true;
        saveGameInfo.setMoney(money);
        gameContent.setMoney(money);
    }

    public void setDay(int day) {
        isDirty = true;
        saveGameInfo.setDay(day);
        gameContent.setDay(day);
    }

    public void setYear(int year) {
        isDirty = true;
        saveGameInfo.setYear(year);
        gameContent.setYear(year);
    }

    public void setSeason(SdvSeason season) {
        isDirty = true;
        saveGameInfo.setSeason(season);
        gameContent.setSeason(season);
    }

    public boolean hasSkullKey(){
        return gameContent.hasSkullKey();
    }

    public void setSkullKey(boolean hasSkullKey){
        isDirty = true;
        gameContent.setSkullKey(hasSkullKey);
    }

//    public boolean hasRustyKey(){
//        return false;
//    }

    public void saveChanges() {
        if(isDirty) {
            saveGameInfo.saveChanges();
            gameContent.saveChanges();
            isDirty = false;
        }
    }
}
