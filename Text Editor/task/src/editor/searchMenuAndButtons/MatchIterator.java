package editor.searchMenuAndButtons;

import editor.textArea.TextArea;


import java.util.List;

// class to iterate over search results
public final class MatchIterator {
    private List<Integer> indexes;
    private int lengthOfPattern;
    private int currentIndex;
    private boolean hasResult;
    private boolean firstTime = true;

    public MatchIterator(List<Integer> indexes, int lengthOfPattern) {
        this.indexes = indexes;
        this.lengthOfPattern = lengthOfPattern;
        if (indexes.size() > 0) {
            this.currentIndex = 0;
            hasResult = true;
        } else {
            hasResult = false;
        }
        nextMatch();
    }

    public void nextMatch() {
        if (!hasResult) {
            System.out.println("There are no results!");
        } else {
            if (!firstTime) {
                currentIndex++;
            }
            firstTime = false;
            TextArea.highlightResult(indexes.get(currentIndex % indexes.size()), lengthOfPattern);
        }
    }

    public void prevMatch() {
        if (!hasResult) {
            System.out.println("There are no results!");
        } else {
            if (!firstTime) {
                currentIndex--;
            }
            firstTime = false;
            currentIndex = currentIndex < 0 ? indexes.size() - 1 : currentIndex;
            TextArea.highlightResult(indexes.get(currentIndex % indexes.size()), lengthOfPattern);

        }
    }
}
