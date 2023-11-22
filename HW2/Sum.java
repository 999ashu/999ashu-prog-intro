public class Sum {
    public static void main(String[] args) {
        int total = 0;
        for (String str: args) {
            var sb = new StringBuilder();
            int i = 0;
            while (i < str.length()) {
                sb.setLength(0);
                while (i < str.length() && Character.isWhitespace(str.charAt(i))) {
                    i++;
                }
                while (i < str.length() && !Character.isWhitespace(str.charAt(i))) {
                    sb.append(str.charAt(i));
                    i++;
                }
                if (!sb.isEmpty()) {
                    total += Integer.parseInt(sb.toString());
                }
            }
        }
        System.out.println(total);
    }
}
