package editor.searchMenuAndButtons;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// class that will do search in the text in background
public final class MatchSearcher extends SwingWorker<MatchIterator, Object> {

    private List<Integer> matches;
    private String data;
    private String template;
    private static boolean going;

    public MatchSearcher(String data, String template) {
        super();
        this.data = data;
        matches = new ArrayList<>();
        this.template = template;
    }

    @Override
    protected MatchIterator doInBackground() throws Exception {
        going = true;
        int length = -1;
        Pattern pattern;
        if (SearchMenu.isClicked()) {
            pattern = Pattern.compile(template);
        } else {
            pattern = Pattern.compile(template, Pattern.LITERAL);
            length = template.length();
        }
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            matches.add(matcher.start());
            if (length == -1) {
                length = matcher.end() - matcher.start();
            }
        }
        going = false;
        return new MatchIterator(matches, length);
    }

    @Override
    protected void done() {
        try {
            SearchMenu.setMatchIterator(this.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static boolean isGoing() {
        return going;
    }
}
