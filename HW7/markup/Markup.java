package markup;

public interface Markup {
    void toMarkdown(StringBuilder value);
    void toBBCode(StringBuilder value);
}