package editor.fileMenuAndButtons;

import editor.fileIO.FileManager;
import editor.textArea.TextArea;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import static editor.TextEditor.jfc;

// buttons and menu for managing file(open/save)
public final class FileMenuAndButtons {
    private static JMenu fileMenu;
    private final JMenuItem openMenu;
    private final JMenuItem saveMenu;
    private final JMenuItem exitMenu;
    private static String fileName;
    private final JButton saveButton;
    private final JButton openButton;
    private static JPanel buttons;

    static {
        new FileMenuAndButtons();
    }

    private FileMenuAndButtons() {
        // menu
        fileMenu = new JMenu("File");
        fileMenu.setName("MenuFile");

        openMenu = new JMenuItem("Open");
        openMenu.setName("MenuOpen");
        saveMenu = new JMenuItem("Save");
        saveMenu.setName("MenuSave");
        exitMenu = new JMenuItem("Exit");
        exitMenu.setName("MenuExit");

        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(openMenu);
        fileMenu.add(saveMenu);
        fileMenu.addSeparator();
        fileMenu.add(exitMenu);

        saveButton = new JButton(new ImageIcon("Text Editor/task/src/icons/save-icon.png"));
        saveButton.setName("SaveButton");
        openButton = new JButton(new ImageIcon("Text Editor/task/src/icons/Folder-icon.png"));
        openButton.setName("OpenButton");
        buttons = new JPanel();
        buttons.add(openButton);
        buttons.add(saveButton);

        addLogic();
    }

    private void addLogic() {
        openMenu.addActionListener(getOpenAction());
        saveMenu.addActionListener(getSaveAction());
        exitMenu.addActionListener(e -> System.exit(0));

        saveButton.addActionListener(getSaveAction());
        openButton.addActionListener(getOpenAction());
    }

    public static JMenu getFileMenu() {
        return fileMenu;
    }

    public static JPanel getButtons() {
        return buttons;
    }

    public ActionListener getOpenAction() {
        return e -> {
            jfc.setVisible(true);
            int returnValue = jfc.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                fileName = jfc.getSelectedFile().getPath();
                TextArea.setText(FileManager.readFileAsString(fileName));
            } else {
                TextArea.setText("");
                System.out.println("You didn't proved with the path!");
            }

        };
    }

    public static ActionListener getSaveAction() {
        return e -> {
            if (fileName != null) {
                FileManager.saveFile(fileName, TextArea.getText());
            } else {
                System.out.println("There is no such file!");
            }
        };
    }
}
