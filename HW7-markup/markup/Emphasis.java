package markup;

import java.util.List;

public class Emphasis extends AbstractStyle {
    public Emphasis(List<Markup> value) {
        super(value, "*", "i]");
    }
}
