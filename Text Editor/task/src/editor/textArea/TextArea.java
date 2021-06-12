package editor.textArea;

import javax.swing.*;

//a field that holds our text
public final class TextArea {
    private static JTextArea textArea;
    private static JScrollPane scrollPane;

    static {
        new TextArea();
    }

    private TextArea() {
        textArea = new JTextArea();
        textArea.setName("TextArea");

        scrollPane = new JScrollPane(textArea);
        scrollPane.setName("ScrollPane");
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    public static JScrollPane getScrollPane() {
        return scrollPane;
    }

    public static String getText() {
        return textArea.getText();
    }

    public static void setText(String text) {
        textArea.setText(text);
    }

    public static void highlightResult(int idx, int length) {
        textArea.setCaretPosition(idx + length);
        textArea.select(idx, idx + length);
        textArea.grabFocus();
    }
}
