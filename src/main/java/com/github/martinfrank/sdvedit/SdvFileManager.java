package com.github.martinfrank.sdvedit;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SdvFileManager {

    private static final int UNDEFINED_INDEX = 111111111;

    private final File root;

    public SdvFileManager(File root) {
        this.root = root;
    }

    public List<SdvFileSet> loadSdvFileSets() {
        File filesDir = new File(root, SdvNamePattern.SDV_DIR_NAME);
        File[] files = filesDir.listFiles(SdvNamePattern.filenameFilter());
        return files == null ?
                Collections.emptyList() :
                Arrays.stream(files).map(SdvFileSet::new).collect(Collectors.toList());
    }

    public File getRootDir() {
        return root;
    }

    public boolean exists(SdvFileSet sdvFileSet) {
        File filesDir = new File(root, SdvNamePattern.SDV_DIR_NAME);
        File[] candidates = filesDir.listFiles(SdvNamePattern.filenameFilter());
        if(candidates == null){
            return false;
        }
        for (File dir : candidates) {
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

    public void delete(SdvFileSet sdvFileSet) {
        try (Stream<Path> walk = Files.walk(sdvFileSet.getDirectory().toPath())) {
            walk.sorted(Comparator.reverseOrder()).forEach(this::deleteFile);
        } catch (IOException e) {
            //FIXME better exception handling
        }
    }

    private void deleteFile(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            //FIXME better exception handling
        }
    }

    private void save(SdvFileSet sdvFileSet, Function<String, String> nameMapper) {
        File sdvDir = sdvFileSet.getDirectory();
        File filesDir = new File(root, SdvNamePattern.SDV_DIR_NAME);
        File destDir = new File(filesDir, nameMapper.apply(sdvDir.getName()));
        try {
            createDirectory(destDir);
            for (File f : Objects.requireNonNull(sdvFileSet.getDirectory().listFiles())) {
                Path destFile = new File(destDir, nameMapper.apply(f.getName())).toPath();
                Files.copy(f.toPath(), destFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDirectory(File dir) throws IOException {
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                throw new IOException("cannot create directory");
            }
        }
    }


    private int getMaxIndex(SdvFileSet fileSet) {
        String filesetName = getFileSetName(fileSet);
        File[] filesWithName = getFilesWithName(filesetName);
        return Arrays.stream(filesWithName).mapToInt(f -> getSdvFileSetIndex(f, filesetName)).max().orElse(UNDEFINED_INDEX);
    }

    private int getSdvFileSetIndex(File candidate, String sdvFileSetName) {
        System.out.println("candidate:" + candidate);
        System.out.println("sdvFileSetName:" + sdvFileSetName);
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
