package Controller;

import Model.TextEditorModel;
import View.TextEditorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TextEditorController {
    private TextEditorView view;
    private TextEditorModel model;

    public TextEditorController(TextEditorView view, TextEditorModel model) {
        this.view = view;
        this.model = model;

        initView();
    }

    private void initView() {
        view.getOpenButton().addActionListener(e -> openFile());
        view.getSaveButton().addActionListener(e -> saveFile());
        view.getBrowseButton().addActionListener(e -> browseDirectory());
        view.getSearchButton().addActionListener(e -> searchInFile());
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(view);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                List<String> lines = model.readFile(file);
                view.setText(String.join("\n", lines));
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(view, "Error opening file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(view);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                model.saveFile(file, List.of(view.getText().split("\n")));
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(view, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void browseDirectory() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int option = fileChooser.showOpenDialog(view);
        if (option == JFileChooser.APPROVE_OPTION) {
            File directory = fileChooser.getSelectedFile();
            List<File> files = model.listFilesRecursively(directory); 
            for (File file : files) {
                System.out.println(file.getAbsolutePath());
            }
        }
    }

    private void searchInFile() {
        String keyword = view.getSearchField().getText();
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(view);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                List<String> searchResults = model.searchInFile(file, keyword);
                if (searchResults.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Keyword not found in file.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder resultBuilder = new StringBuilder();
                    for (String line : searchResults) {
                        resultBuilder.append(line).append("\n");
                    }
                    JOptionPane.showMessageDialog(view, resultBuilder.toString(), "Search Result", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "Error searching in file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

