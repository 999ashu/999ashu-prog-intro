import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class WsppPosition {

    public static void main(String[] args) {
        Map<String, ArrayList<Integer>> wordStat = new LinkedHashMap<>();
        try {
            FScanner textIn = new FScanner(new File(args[0]));
            try {
                List<String> words = new ArrayList<>();
                int linesCounter = 1;
                words = new ArrayList<>();
                while (textIn.ready()) {
                    while (textIn.hasNext("word")) {
                        words.add(textIn.next("word").toLowerCase());
                    }
                    int wordsCounter = words.size();
                    for (String curr : words) {
                        ArrayList<Integer> tmp;
                        if (wordStat.containsKey(curr)) {
                            tmp = wordStat.get(curr);
                            tmp.set(0, tmp.get(0) + 1);
                        } else {
                            tmp = new ArrayList<>();
                            tmp.add(1);
                        }
                        tmp.add(linesCounter);
                        tmp.add(wordsCounter);
                        wordStat.put(curr, tmp);
                        wordsCounter--;
                    }
                    words = new ArrayList<>();
                    linesCounter++;
                }
            } finally {
                textIn.close();
            }
        } catch (IOException e) {
            System.out.print("Problem with input: " + e);
            return;
        }
        try {
            try (BufferedWriter textOut = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
                for (Map.Entry<String, ArrayList<Integer>> pair : wordStat.entrySet()) {
                    textOut.write(pair.getKey());
                    Iterator<Integer> value = pair.getValue().iterator();
                    textOut.write(' ' + value.next().toString());
                    while (value.hasNext()) {
                        textOut.write(' ' + value.next().toString() + ':' + value.next().toString());
                    }
                    textOut.newLine();
                }
            }
        } catch (IOException e) {
            System.out.print("Problem with output: " + e);
        }
    }
}