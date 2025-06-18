package com.google;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int index = 0;

        while (index < words.length) {
            // Calculate how many words fit in this line
            int count = 1;
            int lineLength = words[index].length();

            while (index + count < words.length && // word count
                    lineLength + 1 + words[index + count].length() <= maxWidth) { // line count
                lineLength += 1 + words[index + count].length();
                count++;
            }

            StringBuilder line = new StringBuilder();

            // Last line or line with only one word - left justify
            if (count == 1 || index + count == words.length) {
                for (int i = 0; i < count; i++) {
                    line.append(words[index + i]);
                    if (i < count - 1) {
                        line.append(" ");
                    }
                }

                while (line.length() < maxWidth) {
                    line.append(" ");
                }
            } else {
                // Middle lines - distribute spaces evenly
                int totalSpaces = maxWidth - (lineLength - (count - 1));
                int spacesBetweenWords = totalSpaces / (count - 1);
                int extraSpaces = totalSpaces % (count - 1);

                for (int i = 0; i < count; i++) {
                    line.append(words[index + i]);

                    if (i < count - 1) {
                        int spacesToAdd = spacesBetweenWords + (i < extraSpaces ? 1 : 0);
                        for (int j = 0; j < spacesToAdd; j++) {
                            line.append(" ");
                        }
                    }
                }
            }

            result.add(line.toString());
            index += count;
        }

        return result;
    }

    public static void main(String[] args) {
        TextJustification textJustification = new TextJustification();
        String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        textJustification.fullJustify(words, 16);
    }
}
