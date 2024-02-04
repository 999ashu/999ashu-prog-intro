package markup;

import java.util.List;

public class Strikeout extends AbstractStyle {
    public Strikeout(List<Markup> value) {
        super(value, "~", "s]");
    }
}
