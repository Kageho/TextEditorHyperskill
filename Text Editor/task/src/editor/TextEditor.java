package editor;


import editor.fileMenuAndButtons.FileMenuAndButtons;
import editor.searchMenuAndButtons.SearchMenu;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;


public final class TextEditor extends JFrame {
    private final JPanel menuAndButtons;
    // for tests sake
    public static final JFileChooser jfc;

    static {
        jfc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
        jfc.setName("FileChooser");

    }

    public TextEditor() {
        this("Text Editor");
    }

    public TextEditor(String name) {
        super(name);

        menuAndButtons = new JPanel(new BorderLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);

        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        addTextArea();
        menuAndButtons.add(mergeMenu(), BorderLayout.NORTH);
        menuAndButtons.add(mergeButtons(), BorderLayout.CENTER);
        add(menuAndButtons, BorderLayout.NORTH);
        forBeauty();
        addJfc();
    }

    // unites two menu: file and search
    private JMenuBar mergeMenu() {
        JMenuBar menuBar = new JMenuBar();

        menuBar.add(FileMenuAndButtons.getFileMenu());
        menuBar.add(SearchMenu.getSearchMenu());

        return menuBar;
    }

    // unites two logical groups of buttons
    private JPanel mergeButtons() {
        JPanel buttonsCheckBoxSearchField = new JPanel();
        buttonsCheckBoxSearchField.add(FileMenuAndButtons.getButtons());
        buttonsCheckBoxSearchField.add(SearchMenu.getTextFieldAndButtons());
        return buttonsCheckBoxSearchField;
    }

    // adds text area to main window
    private void addTextArea() {
        add(editor.textArea.TextArea.getScrollPane(), BorderLayout.CENTER);
    }
    // to pass tests
    private void addJfc() {
        JPanel spacesAndJfc = new JPanel();
        spacesAndJfc.add(jfc);
        spacesAndJfc.add(new JLabel("    "));
        add(spacesAndJfc, BorderLayout.EAST);  //Просто для красоты
        jfc.setVisible(false);
    }

    private void forBeauty() {
        menuAndButtons.add(new JLabel("           "), BorderLayout.EAST);
        Container container = getContentPane();
        container.add(new JLabel(" "), BorderLayout.SOUTH);    //Просто для красоты
        container.add(new JLabel("    "), BorderLayout.WEST);  //Просто для красоты
    }
}
