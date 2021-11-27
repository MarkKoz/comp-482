import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Project3
{
    public static void main(final String[] args) throws IOException
    {
        final Integer[] integers = readIntegers();
        final int sum = maxConsecutiveSum(integers, 0, integers.length - 1);
        System.out.println(sum);
    }

    private static int maxConsecutiveSum(final Integer[] integers, final int start, final int end)
    {
        // Base case.
        if (start == end) {
            return integers[start];
        }

        // Recursive cases.
        final int midpoint = (start + end) / 2;

        // Maybe it's in the left half.
        final int leftSubMax = maxConsecutiveSum(integers, start, midpoint);

        // Maybe it's in the right half.
        final int rightSubMax = maxConsecutiveSum(integers, midpoint + 1, end);

        // Maybe it's across the midpoint.
        // The left half has a step of -1 so that the sum starts from the midpoint to ensure the sum
        // of the halves is also continuous.
        final int leftMax = maxConsecutiveSumFromStart(integers, midpoint, start, -1);
        final int rightMax = maxConsecutiveSumFromStart(integers, midpoint + 1, end, 1);

        return Stream.of(leftMax, rightMax, leftMax + rightMax, leftSubMax, rightSubMax)
            .max(Integer::compareTo)
            .get();
    }

    private static int maxConsecutiveSumFromStart(
        final Integer[] integers, final int start, final int end, final int step)
    {
        // Handle the first value separately so max has a valid value that can be compared against.
        int sum = integers[start];
        int max = sum;

        for (int i = start + step; i != (end + step); i += step) {
            sum += integers[i];
            max = Math.max(max, sum);
        }

        return max;
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
