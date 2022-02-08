package com.github.martinfrank.sdvedit;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class SdvFileSetTest {

    private static final String TEST_RESOURCE_DIR = "src/test/resources";

    @Test
    public void testGetFarmersName() {
        SdvFileManager sdvFileManager = new SdvFileManager(new File(TEST_RESOURCE_DIR));
        List<SdvFileSet> sdvFileSets = sdvFileManager.loadSdvFileSets();

        //one farmer's name is "Ninja"
        final String farmersName = "Ninja";
        Assert.assertTrue(sdvFileSets.stream().anyMatch(s -> s.getName().equalsIgnoreCase(farmersName)));
    }

    @Test
    public void testLoadSave(){

        final int twentyBucks = 20;

        SdvFileManager sdvFileManager = new SdvFileManager(new File(TEST_RESOURCE_DIR));
        SdvFileSet sdvFileSet = sdvFileManager.loadSdvFileSets().get(1);
        int money = sdvFileSet.getMoney();
        sdvFileSet.setMoney(twentyBucks);
        Assert.assertEquals(twentyBucks,sdvFileSet.getMoney() );

        //validate that values are also saved
        sdvFileSet.saveChanges();
        //reload
        sdvFileSet = sdvFileManager.loadSdvFileSets().get(1);
        Assert.assertEquals(twentyBucks,sdvFileSet.getMoney() );

        //set back to original
        sdvFileSet.setMoney(money);
        sdvFileSet.saveChanges();
        Assert.assertEquals(money,sdvFileSet.getMoney() );
    }

    @Test
    public void testFileConsistency(){
        SdvFileManager sdvFileManager = new SdvFileManager(new File(TEST_RESOURCE_DIR));
        SdvFileSet sdvFileSet = sdvFileManager.loadSdvFileSets().get(1);

        Assert.assertEquals(sdvFileSet.gameContent.getName(), sdvFileSet.saveGameInfo.getName());

        testLoadSave();

        sdvFileSet = sdvFileManager.loadSdvFileSets().get(1);

        Assert.assertEquals(sdvFileSet.gameContent.getName(), sdvFileSet.saveGameInfo.getName());
    }

}
