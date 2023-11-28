import java.io.Reader;
import java.io.StringReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FScanner {
    private final Reader reader;
    private final char[] buff = new char[192];
    private int total = 0;
    private int iterator = 0;
    private Integer intBuff;
    public static final String lineSep = System.lineSeparator();
    public static final String goodChars = "abcdefghij";

    public FScanner(String str) {
        this.reader = new StringReader(str);
    }

    public FScanner(InputStream streamName) {
        this.reader = new InputStreamReader(streamName);
    }

    public FScanner(File fileName) throws IOException {
        this.reader = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8);
    }

    public boolean ready() {
        return (total != -1);
    }

    public boolean hasNextInt() throws IOException {
        intBuff = null;
        intBuff = nextInt();
        return intBuff != null;
    }

    public Integer nextInt() throws IOException {
        if (intBuff != null) {
            Integer newInt = intBuff;
            intBuff = null;
            return newInt;
        }
        StringBuilder number = new StringBuilder();
        fill();
        while (iterator < total) {
            while ((iterator < total) && isType("digit")) {
                number.append(buff[iterator]);
                refill();
            }
            if (!number.isEmpty() && number.length() < 12) {
                return Integer.parseInt(number.toString());
            }
        }
        return null;
    }

    private boolean isType(String type) {
        return switch (type) {
            case "word" -> ((Character.getType(buff[iterator]) == (Character.DASH_PUNCTUATION)) ||
                    (buff[iterator] == '\'') ||
                    (Character.isLetter(buff[iterator])));
            case "abc" -> ((goodChars.indexOf(buff[iterator]) > -1) || (buff[iterator] == '-'));
            case "digit" -> (Character.isDigit(buff[iterator]) || (buff[iterator] == '-'));
            case "line" -> (buff[iterator] != lineSep.charAt(0));
            default -> false;
        };
    }

    public boolean hasNext(String type) throws IOException {
        fill();
        while (iterator < total) {
            if (isType(type)) {
                return true;
            }
            if (buff[iterator] == lineSep.charAt(0)) {
                refill();
                return false;
            }
            refill();
        }
        return false;
    }

    public String next(String type) throws IOException {
        StringBuilder chr = new StringBuilder();
        fill();
        while (iterator < total) {
            while ((iterator < total) && isType(type)) {
                chr.append(buff[iterator]);
                refill();
            }
            if (!chr.isEmpty()) {
                return chr.toString();
            }
        }
        return chr.toString();
    }

    private void fill() throws IOException {
        if (total == 0) {
            total = reader.read(buff);
        }
    }

    private void refill() throws IOException {
        if (iterator == total - 1) {
            total = reader.read(buff);
            iterator = -1;
        }
        iterator++;
    }

    public void close() throws IOException {
        reader.close();
    }
}
