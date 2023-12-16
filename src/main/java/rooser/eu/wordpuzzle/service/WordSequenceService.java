package rooser.eu.wordpuzzle.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WordSequenceService {
    public List<String> findShortestPath(String startWord, String endWord, Set<String> loadedWords) {
        //TODO This could be changed, based on the requirements
        if (!loadedWords.contains(startWord) || !loadedWords.contains(endWord)) {
            throw new IllegalArgumentException("Start or end word not found in the dictionary.");
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        //We need a map to keep track of the progress, and construct the result
        Map<String, String> parentMap = new HashMap<>();

        queue.add(startWord);
        visited.add(startWord);

        while (!queue.isEmpty()) {
            String currentWord = queue.poll();

            // Generate neighboring words by changing one letter at a time
            List<String> neighbors = getNeighbors(currentWord, loadedWords);

            for (String neighbor : neighbors) {
                //go all the way in like a tree, create all the possibilities for each new word
                //until it gets us to do desire word
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    parentMap.put(neighbor, currentWord);

                    if (neighbor.equals(endWord)) {
                        parentMap.put(neighbor, currentWord);
                        // Reconstruct the path
                        return reconstructPath(parentMap, startWord, endWord);
                    }
                }
            }
        }

        // No path found
        return Collections.emptyList();
    }

    private static List<String> getNeighbors(String word, Set<String> loadedWords) {
        List<String> neighbors = new ArrayList<>();
        //break the word into an array of characters
        char[] wordArray = word.toCharArray();

        //for each char, create 25 new words by replacing that char with the rest of alphabet
        //compare these 25 words with our dictionary and return all the matches
        for (int i = 0; i < word.length(); i++) {
            char originalChar = wordArray[i];

            for (char c = 'a'; c <= 'z'; c++) {
                if (c != originalChar) {
                    wordArray[i] = c;
                    String newWord = new String(wordArray);
                    if (loadedWords.contains(newWord)) {
                        neighbors.add(newWord);
                    }
                }
            }

            //Restore original character - we need to do this, otherwise the next iterations (for other chars in the word) will become incorrect
            wordArray[i] = originalChar;
        }

        return neighbors;
    }

    private static List<String> reconstructPath(Map<String, String> parentMap, String startWord, String endWord) {
        List<String> path = new ArrayList<>();
        String currentWord = endWord;

        while (currentWord != null) {
            path.add(currentWord);
            if (currentWord.equals(startWord)) {
                break;
            }
            currentWord = parentMap.get(currentWord);
        }

        Collections.reverse(path);
        return path;
    }

}
