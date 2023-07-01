import java.util.*;

public class StringProcess {
    public static void main(String[] args) {
        String[] words = {"мама", "тато", "їж їжак желе", "бабуся", "папа"};

        Set<Character> uniqueLetters = findUniqueLetters(words);

        System.out.println("Набір унікальних літер: " + uniqueLetters);
    }

    public static Set<Character> findUniqueLetters(String[] words) {
        List<String> matchingWords = new ArrayList<>();

        // Перший вид циклу - цикл for-each для перебору слів у масиві
        for (String word : words) {
            int[] letterCounts = new int[33];

            for (char letter : word.toCharArray()) {
                if (Character.isLetter(letter)) {
                    letterCounts[findIndex(letter)]++;
                }
            }

            // Другий вид циклу while для перевірки, чи кожна літера трапляється парну кількість разів
            boolean isEvenCount = true;
            int pairLetter = 0;
            while (isEvenCount && pairLetter < letterCounts.length) {
                if (letterCounts[pairLetter] % 2 != 0) {
                    isEvenCount = false;
                }
                pairLetter++;
            }

            if (isEvenCount) {
                matchingWords.add(word);
            }

            // Вихід з циклу for-each, якщо знайдено перші два слова
            if (matchingWords.size() == 2) {
                break;
            }
        }

        Set<Character> uniqueLetters = new HashSet<>();

        // Третій вид циклу - цикл for для додавання унікальних літер у набір
        for (String word : matchingWords) {
            for (char letter : word.toCharArray()) {
                if (Character.isLetter(letter)) {
                    uniqueLetters.add(letter);
                }
            }
        }

        return uniqueLetters;
    }

    /*
        Метод для перерахунку українського алфавіту, який використовується для індексування українських літер у методі
        findUniqueLetters
    */
    private static int findIndex(char letter) {
        char[] ukrainianAlphabet = {'а', 'б', 'в', 'г', 'ґ', 'д', 'е', 'є', 'ж', 'з', 'и', 'і', 'ї', 'й', 'к', 'л', 'м',
                'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ь', 'ю', 'я'};
        for (int ukrainianLetter = 0; ukrainianLetter < ukrainianAlphabet.length; ukrainianLetter++) {
            if (ukrainianAlphabet[ukrainianLetter] == letter) {
                return ukrainianLetter;
            }
        }
        return -1; // Якщо літера не знайдена, повертаємо -1
    }
}
