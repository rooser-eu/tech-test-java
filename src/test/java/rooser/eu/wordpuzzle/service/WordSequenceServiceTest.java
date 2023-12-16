package rooser.eu.wordpuzzle.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class WordSequenceServiceTest {

    @Autowired
    private WordSequenceService wordSequenceService;

    @Test
    void findShortestPath() {
        String startWord = "cat";
        String endWord = "dog";

        Set<String> dictionary = Set.of("boot","cat","gggggg","dat","item","dot","dog","hhhhh");

        //sequence: cat -> dat -> dot -> dog
        List<String> result = wordSequenceService.findShortestPath(startWord, endWord, dictionary);

        assertEquals(4, result.size());
        assertEquals(startWord, result.get(0));
        assertEquals(endWord, result.get(3));
    }
}