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

public class Wspp {

    public static void main(String[] args) {
        Map<String, ArrayList<Integer>> wordStat = new LinkedHashMap<>();
        try {
            FScanner textIn = new FScanner(new File(args[0]));
            try {
                int counter = 1;
                while (textIn.ready()) {
                    while (textIn.hasNext("word")) {
                        String curr = textIn.next("word").toLowerCase();
                        if (wordStat.containsKey(curr)) {
                            ArrayList<Integer> tmp = wordStat.get(curr);
                            tmp.set(0, tmp.get(0) + 1);
                            tmp.add(counter);
                            wordStat.put(curr, tmp);
                        } else {
                            wordStat.put(curr, new ArrayList<>(List.of(1, counter)));
                        }
                        counter++;
                    }
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
                for (Map.Entry<String, ArrayList<Integer>> pair: wordStat.entrySet()) {
                    textOut.write(pair.getKey());
                    for (Integer number: pair.getValue()) {
                        textOut.write(' ');
                        textOut.write(number.toString());
                    }
                    textOut.newLine();
                }
            }
        } catch (IOException e) {
            System.out.print("Problem with output: " + e);
        }
    }
}