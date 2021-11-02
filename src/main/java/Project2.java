import static java.lang.Math.max;
import static java.lang.Math.min;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Project2
{
    public static void main(final String[] args) throws IOException
    {
        final Integer[] ratings = readRatings();

        // clang-format off

        // Sort the indices based on the ratings.
        final Stream<Integer> positions = IntStream.range(0, ratings.length)
          .boxed()
          .sorted(Comparator.comparingInt(i -> ratings[i]));

        // clang-format on

        // Final values must be > 1, so the default of 0 is a perfect "empty" value.
        final int[] out = new int[ratings.length];
        int total = 0;

        for (final int position : (Iterable<Integer>) positions::iterator) {
            // Clamp the indices to the bounds of the array.
            // If the neighbour is out of bounds, it will just stay at the current
            // position, which has a value of 0 and is therefore equivalent to
            // it having a neighbour there that was empty.
            final int left = out[max(position - 1, 0)];
            final int right = out[min(position + 1, out.length - 1)];

            // To minimise the value being inserted, find the greater neighbour and
            // add 1 to it. Why +1? Because the list is sorted in ascending order.
            // Therefore, the current item will always be larger than its neighbours.
            // Thus, the resulting value must also be larger than its neighbours.
            out[position] = max(left, right) + 1;

            // Track the total now to avoid an extra iteration later to sum it up.
            total += out[position];
        }

        System.out.println(total);
    }

    private static Integer[] readRatings() throws IOException
    {
        final Path file = Paths.get("input.txt");
        final String content = Files.readString(file);
        final Matcher matcher = Pattern.compile("\\d+").matcher(content);

        // Skip the 1st item (length) because it's redundant with streams.
        return matcher.results()
            .skip(1)
            .map(MatchResult::group)
            .map(Integer::parseInt)
            .toArray(Integer[] ::new);
    }
}
