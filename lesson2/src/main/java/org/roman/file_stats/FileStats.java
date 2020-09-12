package org.roman.file_stats;

import java.util.*;
import java.util.stream.Collectors;

public class FileStats implements Iterable<String> {

    final private List<String> lines;
    final private Map<String, Integer> wordToFrequency;

    public FileStats(List<String> lines) {
        this.lines = lines;
        this.wordToFrequency = new HashMap<>();
        countFrequencies();
    }

    public Integer countDifferentWordsCount() {
        return wordToFrequency.size();
    }

    public List<String> getWordsSortedByLength() {
        List<String> wordsList = new ArrayList<>(wordToFrequency.keySet());
        wordsList.sort((s1, s2) -> {
            int length1 = s1.length();
            int length2 = s2.length();
            if (length1 < length2 || (length1 == length2 && s1.compareToIgnoreCase(s2) < 0)) {
                return -1;
            } else if (length1 == length2 && s1.compareToIgnoreCase(s2) == 0) {
                return 0;
            }
            return 1;
        });
        return new ArrayList<>(wordsList);
    }

    public Map<String, Integer> countWordsFrequencies() {
        return wordToFrequency;
    }

    public List<String> getReversedLines() {
        List<String> reversed = new ArrayList<>();
        for (int i = lines.size() - 1; i >= 0; i--) {
            reversed.add(lines.get(i));
        }

        return reversed;
    }

    public List<String> getLinesByNumbers(List<Integer> numbers) {
        return numbers.stream()
                .map(lines::get)
                .collect(Collectors.toList());
    }

    private void countFrequencies() {
        for (String line : lines) {
            String[] words = line.split("\\W+");
            for (String word : words) {
                String lowerCaseWord = word.toLowerCase();
                if (wordToFrequency.containsKey(lowerCaseWord)) {
                    wordToFrequency.put(lowerCaseWord, wordToFrequency.get(lowerCaseWord) + 1);
                } else {
                    wordToFrequency.put(lowerCaseWord, 1);
                }
            }
        }
    }

    @Override
    public Iterator<String> iterator() {

        return new Iterator<String>() {

            private int currentIndex = lines.size() - 1;

            @Override
            public boolean hasNext() {
                return currentIndex >= 0;
            }

            @Override
            public String next() {
                return lines.get(currentIndex--);
            }
        };
    }
}
