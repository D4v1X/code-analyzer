package codeanalyzer;

import codeanalyzer.GenericFileTree.CodeFile;
import codeanalyzer.GenericFileTree.Directory;
import codeanalyzer.GenericFileTree.GenericFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileAnalyzer {
    
    public static GenericFile createDirectoryTree(String path) throws IOException{
        File file = new File(path);
        if (!file.exists()) throw new FileNotFoundException();
        Directory directory = new Directory(file.getPath());
        for (File fileIterator : file.listFiles()){
            if (fileIterator.isDirectory()){
                directory.addGenericFileChild(FileAnalyzer.createDirectoryTree(fileIterator.getPath()));
            }
            if (fileIterator.getName().endsWith(".java")){
                directory.addGenericFileChild(new CodeFile(fileIterator.getPath()));
            }
        }
        return directory;
    }
}