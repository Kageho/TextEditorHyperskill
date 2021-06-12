package editor.searchMenuAndButtons;

import editor.textArea.TextArea;

import javax.swing.*;
import java.awt.event.ActionListener;

// class represents buttons and menu
// it provides abilities to use substrings in a text
public final class SearchMenu {
    private static JMenu search;
    private static JPanel textFieldAndButtons;
    private static MatchIterator matchIterator;
    private static JTextField searchField;
    private static JCheckBox useRegexBox;

    static {
        useRegexBox = new JCheckBox("Use regex");
        useRegexBox.setName("UseRegExCheckbox");
        new SearchMenu();
        initTextFieldAndButtons();
    }

    private SearchMenu() {
        search = new JMenu("Search");
        search.setName("MenuSearch");

        JMenuItem startSearch = new JMenuItem("Start search");
        startSearch.setName("MenuStartSearch");
        startSearch.addActionListener(startSearch());

        JMenuItem nextMatch = new JMenuItem("Next match");
        nextMatch.setName("MenuNextMatch");
        nextMatch.addActionListener(nextMatch());

        JMenuItem prevMatch = new JMenuItem("Previous match");
        prevMatch.setName("MenuPreviousMatch");
        prevMatch.addActionListener(prevMatch());

        JMenuItem useRegex = new JMenuItem("Use regular expressions");
        useRegex.setName("MenuUseRegExp");
        useRegex.addActionListener(e -> useRegexBox.doClick());

        search.add(startSearch);
        search.add(prevMatch);
        search.add(nextMatch);
        search.add(useRegex);
    }

    private static void initTextFieldAndButtons() {
        textFieldAndButtons = new JPanel();

        searchField = new JTextField();
        searchField.setColumns(10);
        searchField.setName("SearchField");

        JButton search = new JButton(new ImageIcon("Text Editor/task/src/icons/search.png"));
        search.setName("StartSearchButton");
        search.addActionListener(startSearch());

        JButton next = new JButton(new ImageIcon("Text Editor/task/src/icons/>.png"));
        next.setName("NextMatchButton");
        next.addActionListener(nextMatch());

        JButton prev = new JButton(new ImageIcon("Text Editor/task/src/icons/<.png"));
        prev.setName("PreviousMatchButton");
        prev.addActionListener(prevMatch());

        textFieldAndButtons.add(searchField);
        textFieldAndButtons.add(search);
        textFieldAndButtons.add(next);
        textFieldAndButtons.add(prev);
        textFieldAndButtons.add(useRegexBox);
    }

    public static JPanel getTextFieldAndButtons() {
        return textFieldAndButtons;
    }

    private static ActionListener startSearch() {
        return e -> {
            if (MatchSearcher.isGoing()) {
                System.out.println("Search isn't available now! It's going");
            } else if (searchField.getText() != null) {
                MatchSearcher matchSer = new MatchSearcher(TextArea.getText(), searchField.getText());
                matchSer.execute();

            } else {
                System.out.println("There is no text!");
            }
        };
    }

    private static ActionListener nextMatch() {
        return e -> {
            if (matchIterator == null) {
                System.out.println("There is no results!");
            } else {
                matchIterator.nextMatch();
            }
        };
    }

    private static ActionListener prevMatch() {
        return e -> {
            if (matchIterator == null) {
                System.out.println("There is no results!");
            } else {
                matchIterator.prevMatch();
            }
        };
    }

    public static void setMatchIterator(MatchIterator result) {
        matchIterator = result;
    }

    public static JMenu getSearchMenu() {
        return search;
    }

    public static boolean isClicked() {
        return useRegexBox.isSelected();
    }
}
