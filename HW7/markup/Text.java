package markup;

public class Text implements Markup {
    private final String text;

    public Text(String value) {
        this.text = value;
    }

    public void toMarkdown(StringBuilder value) {
        value.append(this.text);
    }

    public void toBBCode(StringBuilder value) {
        value.append(this.text);
    }
}
