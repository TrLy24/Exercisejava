package Model;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TextEditorModel {
    public List<String> readFile(File file) throws IOException {
        List<String> lines;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            lines = reader.lines().collect(Collectors.toList());
        }
        return lines;
    }

    public void saveFile(File file, List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    public List<File> listFilesRecursively(File directory) {
        List<File> fileList = new ArrayList<>();
        listFilesRecursivelyHelper(directory, fileList);
        return fileList;
    }

    private void listFilesRecursivelyHelper(File directory, List<File> fileList) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listFilesRecursivelyHelper(file, fileList);
                } else {
                    fileList.add(file);
                }
            }
        }
    }

    public List<String> searchInFile(File file, String keyword) throws IOException {
        List<String> searchResults;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            searchResults = reader.lines()
                    .filter(line -> line.contains(keyword))
                    .collect(Collectors.toList());
        }
        return searchResults;
    }
}
