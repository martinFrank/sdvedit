package com.github.martinfrank.sdvedit;

import org.junit.Test;

import java.io.File;
import java.util.List;

public class DateTest {

    private static final String TEST_RESOURCE_DIR = "src/test/resources";

    @Test
    public void readDateTest(){
        SdvFileManager sdvFileManager = new SdvFileManager(new File(TEST_RESOURCE_DIR));
        List<SdvFileSet> sdvFileSets = sdvFileManager.loadSdvFileSets();
        SdvFileSet fileSet = sdvFileSets.get(0);
        System.out.println(fileSet.getName() + " "+ fileSet.getDirectoryName());
        SdvDate date = fileSet.getDate();
        System.out.println("date: "+date);
        fileSet.setDay(11);
        fileSet.saveChanges();
    }
}
