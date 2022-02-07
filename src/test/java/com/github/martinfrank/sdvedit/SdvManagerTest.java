package com.github.martinfrank.sdvedit;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class SdvManagerTest {

    private static final String TEST_RESOURCE_DIR = "src/test/resources";

    @Test
    public void testLoad(){
        SdvFileManager sdvFileManager = new SdvFileManager(new File(TEST_RESOURCE_DIR));
        List<SdvFileSet> sdvFileSets = sdvFileManager.LoadSdvFileSets();

        //we have three test data sets in our resources
        Assert.assertEquals(3, sdvFileSets.size());
    }

    @Test
    public void saveAsTest(){
        SdvFileManager sdvFileManager = new SdvFileManager(new File(TEST_RESOURCE_DIR));
        List<SdvFileSet> sdvFileSets = sdvFileManager.LoadSdvFileSets();

        sdvFileManager.saveAsNext(sdvFileSets.get(1));
    }
}
