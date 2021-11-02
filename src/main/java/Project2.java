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
    }

    private static Rating[] readRatings() throws IOException
    {
        final Path file = Paths.get("input.txt");
        final List<String> lines = Files.readAllLines(file);

        // Get the first line and use it to allocate an array of that size.
        final int n = Integer.parseInt(lines.get(0));
        final Rating[] ratings = new Rating[n];

        // Create an iterator of lines that skips the 1st line, size that was already read above.
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
