import java.io.*;
import java.util.Arrays;

public class WordStatInput {

    public static boolean goodChar(char x) {
        return ((Character.getType(x) == (Character.DASH_PUNCTUATION)) || (x == '\'') || (Character.isLetter(x)));
    }
    
    public static void main(String[] args) {
        String[] words = new String[2];
        int[] wordsCount = new int[2];
        int wordsAmount = 0;
        try {
            BufferedReader textIn = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), "UTF-8"));
            try {
                String current = textIn.readLine();
                StringBuilder word = new StringBuilder();
                while (current != null) {
                    int i = 0;
                    while (i < current.length()) {
                        word.setLength(0);
                        while ((i < current.length()) && goodChar(current.charAt(i))) {
                            word.append(current.charAt(i));
                            i++;
                        }
                        while ((i < current.length()) && !goodChar(current.charAt(i))) {
                            i++;
                        }
                        if (!word.isEmpty()) {
                            boolean match = false;
                            String check = word.toString().toLowerCase();
                            for (int j = 0; j < wordsAmount; j++) {
                                if (check.equals(words[j])) {
                                    match = true;
                                    wordsCount[j]++;
                                    break;
                                }
                            }
                            if (!match) {
                                if (wordsAmount == words.length) {
                                    words = Arrays.copyOf(words, words.length * 2);
                                    wordsCount = Arrays.copyOf(wordsCount, wordsCount.length * 2);
                                }
                                words[wordsAmount] = word.toString().toLowerCase();
                                wordsCount[wordsAmount] = 1;
                                wordsAmount++;
                            }
                        }
                    }
                    current = textIn.readLine();
                }
            } finally {
                textIn.close();
            }
        } catch(IOException e) {
            System.out.print("Problem with input: " + e);
            return;
        }
        try {
            BufferedWriter textOut = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8"));
            try {
                for (int i = 0; i < wordsAmount; i++) {
                    textOut.write(words[i] + " " + Integer.toString(wordsCount[i]));
                    textOut.newLine();
                }
            } finally {
                textOut.close();
            }
        } catch(IOException e) {
            System.out.print("Problem with output: " + e);
        }
    }
}
