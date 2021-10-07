import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Project1
{
    public static void main(final String[] args) throws IOException
    {
        final Path file = Paths.get("input.txt");
        final List<String> lines = Files.readAllLines(file);

        final int n = Integer.parseInt(lines.get(0));
        final String[] preferences = new String[n];
        final Iterable<String> linesIterable = lines.stream().skip(1)::iterator;

        for (final String line : linesIterable) {
            int manRank = 1;

            for (final String num : line.split(" ")) {
                final int manIndex = Integer.parseInt(num) - 1;
                preferences[manIndex] = String.valueOf(manRank);
                ++manRank;
            }

            System.out.println(String.join("\t", preferences));
        }
    }
}
