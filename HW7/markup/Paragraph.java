package markup;

import java.util.List;

public class Paragraph implements Markup {
    private final List<Markup> paragraph;

    public Paragraph(List<Markup> value) {
        this.paragraph = value;
    }

    public void toMarkdown(StringBuilder value) {
        for (Markup element : paragraph) {
            element.toMarkdown(value);
        }
    }

    public void toBBCode(StringBuilder value) {
        for (Markup element : paragraph) {
            element.toBBCode(value);
        }
    }
}
