import static java.lang.Math.max;
import static java.lang.Math.min;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Project2
{
    public record Rating(int rating, int position) implements Comparable<Rating>
    {
        @Override
        public int compareTo(final Rating other)
        {
            return Integer.compare(rating, other.rating);
        }
    }

    public static void main(final String[] args) throws IOException
    {
        final Rating[] ratings = readRatings();
        Arrays.sort(ratings);

        // Final values must be > 1, so the default of 0 is a perfect "empty" value.
        final int[] out = new int[ratings.length];
        int total = 0;

        for (final Rating rating : ratings) {
            // Clamp the indices to the bounds of the array.
            // If the neighbour is out of bounds, it will just stay at the current
            // position, which has a value of 0 and is therefore equivalent to
            // it having a neighbour there that was empty.
            final int left = out[max(rating.position - 1, 0)];
            final int right = out[min(rating.position + 1, out.length - 1)];

            // To minimise the value being inserted, find the greater neighbour and
            // add 1 to it. Why +1? Because the list is sorted in ascending order.
            // Therefore, the current item will always be larger than its neighbours.
            // Thus, the resulting value must also be larger than its neighbours.
            out[rating.position] = max(left, right) + 1;

            // Track the total now to avoid an extra iteration later to sum it up.
            total += out[rating.position];
        }

        System.out.println(total);
    }

    private static Rating[] readRatings() throws IOException
    {
        final Path file = Paths.get("input.txt");
        final List<String> lines = Files.readAllLines(file);

        // Get the first line and use it to allocate an array of that size.
        final int n = Integer.parseInt(lines.get(0));
        final Rating[] ratings = new Rating[n];

        // Create an iterator of lines that skips the 1st line, since that was already read above.
        final Iterable<String> linesIterable = lines.stream().skip(1)::iterator;
        int position = 0;

        // Populate the ratings arrays with pairs of the value and position.
        // Iterate each line since ratings could be on multiple lines.
        for (final String line : linesIterable) {
            // Split each line by any whitespace chars.
            for (final String num : line.split("\\s+")) {
                ratings[position] = new Rating(Integer.parseInt(num), position);
                ++position;
            }
        }

        return ratings;
    }
}
