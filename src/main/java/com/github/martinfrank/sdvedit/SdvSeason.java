package com.github.martinfrank.sdvedit;

public enum SdvSeason {

    SPRING (0, "spring"), SUMMER(1, "summer"), AUTUMN(2, "fall"), WINTER(3, "winter");

    private final int index;
    private final String description;

    SdvSeason(int index, String description){
        this.index = index;
        this.description = description;
    }

    static SdvSeason byIndex(int index){
        for(SdvSeason sdvSeason: values()){
            if (sdvSeason.index == index){
                return sdvSeason;
            }
        }
        throw new IllegalArgumentException(""+index+"is not a valid index (must be 0...3).");
    }

    public static SdvSeason byDescription(String description){
        for(SdvSeason sdvSeason: values()){
            if (sdvSeason.description.equalsIgnoreCase(description)){
                return sdvSeason;
            }
        }
        throw new IllegalArgumentException(""+description+"is not a valid index (must be 0...3).");
    }


    public String getDescription(){
        return description;
    }

    public int getIndex(){
        return index;
    }
}
