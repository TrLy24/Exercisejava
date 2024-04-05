package Main;

import Controller.TextEditorController;
import Model.TextEditorModel;
import View.TextEditorView;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TextEditorModel model = new TextEditorModel();
                TextEditorView view = new TextEditorView();
                TextEditorController controller = new TextEditorController(view, model);

                view.setVisible(true);
            }
        });
    }
}
