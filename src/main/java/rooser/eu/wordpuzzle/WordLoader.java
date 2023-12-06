package rooser.eu.wordpuzzle;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class WordLoader {

    private final Set<String> words;

    public WordLoader() throws URISyntaxException, IOException {
        Path path = Paths.get(Objects.requireNonNull(getClass().getClassLoader()
            .getResource("words.txt")).toURI());

        try (Stream<String> lines = Files.lines(path)) {
            words = lines.collect(Collectors.toSet());
        }
    }

    public Set<String> getWords() {
        return words;
    }
}
