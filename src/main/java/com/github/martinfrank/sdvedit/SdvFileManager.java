package com.github.martinfrank.sdvedit;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SdvFileManager {

    private static final int UNDEFINDED_INDEX = 111111111;

    private final File root;

    public SdvFileManager(File root) {
        this.root = root;
    }

    public List<SdvFileSet> LoadSdvFileSets() {
        File filesDir = new File(root, SdvNamePattern.SDV_DIR_NAME);
        File[] files = filesDir.listFiles(SdvNamePattern.filenameFilter());
        return files == null ?
                Collections.emptyList() :
                Arrays.stream(files).map(SdvFileSet::new).collect(Collectors.toList());
    }

    public boolean contains(SdvFileSet sdvFileSet) {
        for (File dir : Objects.requireNonNull(root.listFiles(SdvNamePattern.filenameFilter()))) {
            if (dir.getName().equalsIgnoreCase(sdvFileSet.getDirectoryName())) {
                return true;
            }
        }
        return false;
    }

    public void saveAsNext(SdvFileSet fileSet) {
        int max = getMaxIndex(fileSet);
        save(fileSet, new SaveAsNextNameMapping(fileSet, max));
    }

    public void save(SdvFileSet sdvFileSet) {
        save(sdvFileSet, s -> s);
    }

    private void save(SdvFileSet sdvFileSet, Function<String, String> nameMapper) {
        File sdvDir = sdvFileSet.getDirectory();
        File filesDir = new File(root, SdvNamePattern.SDV_DIR_NAME);
        File destDir = new File(filesDir, nameMapper.apply(sdvDir.getName()));
        createDirectory(destDir);
        for (File f : sdvFileSet.getDirectory().listFiles()) {
            try {
                Path destFile = new File(destDir, nameMapper.apply(f.getName())).toPath();
                Files.copy(f.toPath(), destFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createDirectory(File dir) {
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }


    private int getMaxIndex(SdvFileSet fileSet) {
        String filesetName = getFileSetName(fileSet);
        File[] filesWithName = getFilesWithName(filesetName);
        return Arrays.stream(filesWithName).mapToInt(f -> getSdvFileSetIndex(f, filesetName)).max().orElse(UNDEFINDED_INDEX);
    }

    private int getSdvFileSetIndex(File candidate, String sdvFileSetName) {
        System.out.println("candidate:"+candidate);
        System.out.println("sdvFileSetName:"+sdvFileSetName);
        return Integer.parseInt(candidate.getName().replace(sdvFileSetName + "_", ""));
    }

    private File[] getFilesWithName(String filesetName) {
        File filesDir = new File(root, SdvNamePattern.SDV_DIR_NAME);
        return filesDir.listFiles(f -> f.getName().startsWith(filesetName));
    }

    private String getFileSetName(SdvFileSet fileSet) {
        int nameLength = fileSet.getDirectoryName().length();
        return fileSet.getDirectoryName().substring(0, nameLength - 10);
    }

}