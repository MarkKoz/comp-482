import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Project3
{
    public static void main(final String[] args) throws IOException
    {
        final Integer[] integers = readIntegers();
    }

    private static Integer[] readIntegers() throws IOException
    {
        final Path file = Paths.get("input.txt");
        final String content = Files.readString(file);
        final Matcher matcher = Pattern.compile("-?\\d+").matcher(content);

        // Skip the 1st item (length) because it's redundant with streams.
        return matcher.results()
            .skip(1)
            .map(MatchResult::group)
            .map(Integer::parseInt)
            .toArray(Integer[] ::new);
    }
}
