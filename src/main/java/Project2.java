import static java.lang.Math.max;
import static java.lang.Math.min;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Project2
{
    public static void main(final String[] args) throws IOException
    {
        final int[] ratings = readRatings();

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

    private static int[] readRatings() throws IOException
    {
        final Path file = Paths.get("input.txt");
        final List<String> lines = Files.readAllLines(file);

        // Get the first line and use it to allocate an array of that size.
        final int n = Integer.parseInt(lines.get(0));
        final int[] ratings = new int[n];

        // Create an iterator of lines that skips the 1st line, since that was already read above.
        final Iterable<String> linesIterable = lines.stream().skip(1)::iterator;
        int position = 0;

        // Populate the ratings arrays with pairs of the value and position.
        // Iterate each line since ratings could be on multiple lines.
        for (final String line : linesIterable) {
            // Split each line by any whitespace chars.
            for (final String num : line.split("\\s+")) {
                ratings[position] = Integer.parseInt(num);
                ++position;
            }
        }

        return ratings;
    }
}
