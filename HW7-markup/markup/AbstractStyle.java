package markup;

import java.util.List;

public abstract class AbstractStyle implements Markup {
    private final List<Markup> abstractStyle;
    private final String markdownClassTag;
    private final String bbcodeClassTag;

    public AbstractStyle(List<Markup> abstractStyle, String MarkdownClassTag, String bbcodeClassTag) {
        this.abstractStyle = abstractStyle;
        this.markdownClassTag = MarkdownClassTag;
        this.bbcodeClassTag = bbcodeClassTag;
    }

    public void toMarkdown(StringBuilder value) {
        value.append(markdownClassTag);
        for (Markup element : abstractStyle) {
            element.toMarkdown(value);
        }
        value.append(markdownClassTag);
    }

    public void toBBCode(StringBuilder value) {
        value.append('[');
        value.append(bbcodeClassTag);
        for (Markup element : abstractStyle) {
            element.toBBCode(value);
        }
        value.append("[/");
        value.append(bbcodeClassTag);
    }
}
