package com.github.martinfrank.sdvedit;

import java.util.function.Function;

public class SaveAsNextNameMapping implements Function<String, String> {

    private final String currentNumber;
    private final String newNumber;

    public SaveAsNextNameMapping(SdvFileSet fileSet, int maxIndex) {
        String name = fileSet.getDirectoryName();
        int index = name.indexOf("_")+1;//just AFTER the underscore
        currentNumber = name.substring(index);
        newNumber = ""+(maxIndex+1);//this makes the next
    }

    @Override
    public String apply(String s) {
        return s.replace(currentNumber, newNumber);
    }
}
