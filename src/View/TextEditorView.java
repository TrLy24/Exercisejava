package View;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;

public class TextEditorView extends JFrame {
    private JButton openButton;
    private JButton saveButton;
    private JButton browseButton;
    private JButton searchButton;
    private JTextField searchField;
    private JTextArea textArea;

    public TextEditorView() {
        setTitle("Text Editor");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        openButton = new JButton("Open");
        saveButton = new JButton("Save");
        browseButton = new JButton("Browse");
        searchButton = new JButton("Search");
        searchField = new JTextField(20);
        textArea = new JTextArea();

        JPanel panel = new JPanel();
        panel.add(openButton);
        panel.add(saveButton);
        panel.add(browseButton);
        panel.add(searchField);
        panel.add(searchButton);

        JScrollPane scrollPane = new JScrollPane(textArea);

        getContentPane().add(panel, "North");
        getContentPane().add(scrollPane, "Center");
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public String getText() {
        return textArea.getText();
    }

    public JButton getOpenButton() {
        return openButton;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public JButton getBrowseButton() {
        return browseButton;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public void displayFilesList(File[] files) {
        StringBuilder fileList = new StringBuilder("Files in directory:\n");
        for (File file : files) {
            fileList.append(file.getAbsolutePath()).append("\n");
        }
        JOptionPane.showMessageDialog(this, fileList.toString(), "Files List", JOptionPane.INFORMATION_MESSAGE);
    }
}
