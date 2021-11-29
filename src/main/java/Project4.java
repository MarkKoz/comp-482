import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Project4
{
    public static void main(final String[] args) throws IOException
    {
        final Integer[] integers = readIntegers();
        final int[] sums = new int[integers.length];

        final int sum = maxNonConsecutiveSum(integers, integers.length - 1, sums);
        System.out.println(sum);
    }

    private static int maxNonConsecutiveSum(
        final Integer[] integers, final int end, final int[] sums)
    {
        // Base case
        if (end < 2) {
            return sums[end] = integers[end];
        }

        // Recursive cases
        // The max sum which ends at the previous index.
        sums[end - 1] = maxNonConsecutiveSum(integers, end - 1, sums);

        // The max sum which ends at the current index.
        // It could be the current integer added to the previous sum.
        // Alternatively, it could be the current integer or the previous sum alone.
        final int prevSum = maxNonConsecutiveSum(integers, end - 2, sums);
        sums[end] = Math.max(integers[end], Math.max(prevSum + integers[end], prevSum));

        return Math.max(sums[end - 1], sums[end]);
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
