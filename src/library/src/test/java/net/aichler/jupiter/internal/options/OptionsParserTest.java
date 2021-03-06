package net.aichler.jupiter.internal.options;

import org.junit.Test;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

public class OptionsParserTest {

    @Test
    public void toSetShouldSplitAtComma() {

        Options options = parse("--include-tags=development,production");
        assertThat(options.getIncludeTags(), contains("development", "production"));
    }

    @Test
    public void toSetShouldTrimDoubleQuotes() {

        Options options = parse("--include-tags=\"(development & integration)\",development");
        assertThat(options.getIncludeTags(), contains("(development & integration)", "development"));
    }

    @Test
    public void toSetShouldTrimSingleQuotes() {

        Options options = parse("--include-tags='(development & integration)'");
        assertThat(options.getIncludeTags(), contains("(development & integration)"));
    }

    @Test
    public void toSetShouldTrimSingleQuotesBeforeSplitting() {

        Options options = parse("--include-tags='(development & integration), production'");
        assertThat(options.getIncludeTags(), contains("(development & integration)", "production"));
    }

    @Test
    public void toSetShouldTrimDoubleQuotesBeforeSplitting() {

        Options options = parse("--include-tags=\"(development & integration), production\"");
        assertThat(options.getIncludeTags(), contains("(development & integration)", "production"));
    }

    private static Options parse(String... args) {
        return new OptionsParser().parse(args);
    }
}
