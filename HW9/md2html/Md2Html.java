package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Md2Html {
    public static void main(String[] args) {
        ArrayList<String> rawData = new ArrayList<>();
        try (Scanner in = new Scanner(new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8))) {

            while (in.hasNextLine()) {
                StringBuilder block = new StringBuilder();
                String str = in.nextLine();
                if (!str.isEmpty()) {
                    block.append(str);
                    while (in.hasNextLine()) {
                        str = in.nextLine();
                        if (!str.isEmpty()) {
                            block.append(System.lineSeparator()).append(str);
                        } else {
                            break;
                        }
                    }
                    rawData.add(makeHtmlHeader(replaceFormatting(block)) + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Input exception: " + e);
            return;
        }
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
            for (String element : rawData) {
                out.write(element);
            }
        } catch (IOException e) {
            System.out.println("Output exception: " + e);
        }
    }

    static final Map<String, String> tags = new HashMap<>(Map.of(
            "*", "em>",
            "_", "em>",
            "**", "strong>",
            "__", "strong>",
            "--", "s>",
            "`", "code>",
            "''", "q>"));

    public static int getHeaderLevel(StringBuilder block) {
        int i = 0;
        int h = 0;
        while (i < block.length() && i < 7) {
            if (block.charAt(i) == '#') {
                h++;
            } else if (Character.isWhitespace(block.charAt(i))) {
                return h;
            } else {
                return 0;
            }
            i++;
        }
        return 0;
    }

    public static StringBuilder makeHtmlHeader(StringBuilder block) {
        int h = getHeaderLevel(block);
        StringBuilder sb = new StringBuilder();
        if (h > 0) {
            sb.append("<h");
            sb.append(h);
            sb.append(">");
            sb.append(block.substring(h + 1));
            sb.append("</h");
            sb.append(h);
            sb.append(">");
        } else {
            sb.append("<p>");
            sb.append(block);
            sb.append("</p>");
        }
        return sb;
    }

    private static StringBuilder replaceFormatting(StringBuilder block) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < block.length()) {
            if (block.charAt(i) == '<' || block.charAt(i) == '>' || block.charAt(i) == '&' || block.charAt(i) == '\\') {
                sb.append(block.substring(0, i));
                switch (block.charAt(i)) {
                    case '<':
                        sb.append("&lt;");
                        break;
                    case '>':
                        sb.append("&gt;");
                        break;
                    case '&':
                        sb.append("&amp;");
                        break;
                    case '\\':
                        sb.append(block.charAt(i + 1));
                        i++;
                }
                sb.append(replaceFormatting(new StringBuilder(block.substring(i + 1))));
                return sb;
            }
            if (!(block.charAt(i) == '*' || block.charAt(i) == '_' || block.charAt(i) == '-' || block.charAt(i) == '`' || (block.charAt(i) == '\'' && block.charAt(i + 1) == '\''))) {
                i++;
            } else {
                if (i + 1 < block.length() && block.charAt(i) == block.charAt(i + 1)) {
                    int formattingSize = closeFormatting(new StringBuilder(block.substring(i + 2)), ("" + block.charAt(i) + block.charAt(i + 1)));
                    if (formattingSize == -1) {
                        i++;
                    } else {
                        sb.append(block.substring(0, i)).append("<").append(tags.get("" + block.charAt(i) + block.charAt(i + 1)));
                        sb.append(replaceFormatting(new StringBuilder(block.substring(i + 2, i + formattingSize))));
                        sb.append("</").append(tags.get("" + block.charAt(i) + block.charAt(i + 1)));
                        sb.append(replaceFormatting(new StringBuilder(block.substring(i + 2 + formattingSize, block.length()))));
                        return sb;
                    }
                } else {
                    int formattingSize = closeFormatting(new StringBuilder(block.substring(i + 1)), ("" + block.charAt(i)));
                    if (formattingSize == -1) {
                        i++;
                    } else {
                        sb.append(block.substring(0, i)).append("<").append(tags.get("" + block.charAt(i)));
                        sb.append(replaceFormatting(new StringBuilder(block.substring(i + 1, i + formattingSize))));
                        sb.append("</").append(tags.get("" + block.charAt(i)));
                        sb.append(replaceFormatting(new StringBuilder(block.substring(i + 1 + formattingSize, block.length()))));
                        return sb;
                    }
                }
            }
        }
        return block;
    }

    private static int closeFormatting(StringBuilder block, String tag) {
        int i = 0;
        if (tag.length() != 1) {
            while (i + 1 < block.length()) {
                if (("" + block.charAt(i) + block.charAt(i + 1)).equals(tag)) {
                    return i + 2;
                } else {
                    i++;
                }
            }
        } else {
            while (i < block.length()) {
                if (block.charAt(i) == '\\') {
                    i += 2;
                    continue;
                }
                if (("" + block.charAt(i)).equals(tag) && ("" + block.charAt(i + 1)).equals(tag)) {
                    i += 2;
                    continue;
                }
                if (("" + block.charAt(i)).equals(tag)) {
                    return i + 1;
                } else {
                    i++;
                }
            }
        }
        return -1;
    }
}
