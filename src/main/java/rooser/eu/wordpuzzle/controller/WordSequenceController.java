package rooser.eu.wordpuzzle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rooser.eu.wordpuzzle.WordLoader;
import rooser.eu.wordpuzzle.service.WordSequenceService;

import java.util.List;
import java.util.Set;

@RestController
public class WordSequenceController {
    private final WordLoader wordLoader;
    private final WordSequenceService wordSequenceService;

    @Autowired
    public WordSequenceController(WordLoader wordLoader, WordSequenceService wordSequenceService) {
        this.wordLoader = wordLoader;
       this.wordSequenceService = wordSequenceService;
    }

    @GetMapping("/word-sequence")
    public List<String> getWordSequence(
            @RequestParam String startWord,
            @RequestParam String endWord){
        Set<String> dic = wordLoader.getWords();
        return  wordSequenceService.findShortestPath(startWord,endWord,dic);
    }
}
