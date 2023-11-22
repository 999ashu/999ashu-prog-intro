public class SumDouble {
    public static void main(String[] args) {
        double total = 0;
        for (String current : args) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < current.length()) { 
                sb.setLength(0);
                while ((i < current.length()) && !Character.isWhitespace(current.charAt(i))) {
                    sb.append(current.charAt(i));
                    i++;
                }
                while ((i < current.length()) && Character.isWhitespace(current.charAt(i))) {
                    i++;
                }
                if (!sb.isEmpty()) {
                    total += Double.parseDouble(sb.toString());
                }
            }
        }
        System.out.println(total);
    }
}