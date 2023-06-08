import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class SpellingBee {

    private char[] letters;
    private char mustUse;

    public SpellingBee(char[] a, char b){
        letters = a;
        mustUse = b;

    }
    public boolean checkWord(String word) {
        if (word.length() < 4) {
            //System.out.println("The word has to be at least 4 characters");
            return false;
        }

        char[] arr = word.toCharArray();
        boolean containsMustUse = false;
        boolean containsAllLetters = true;

        for (char c : arr) {
            if (c == mustUse) {
                containsMustUse = true;
            }
            if (!containsLetter(c)) {
                containsAllLetters = false;
                break;
            }
        }

        return containsMustUse && containsAllLetters;
    }

    private boolean containsLetter(char c) {
        for (char letter : letters) {
            if (letter == c) {
                return true;
            }
        }
        return false;
    }

        

    /**
     * Loads the contents of file "filename" as a String.
     * 
     * @param filename the file to load
     * @return the contents of file "filename"
     */
    public static String loadFile(String filename) {
        String contents = "";
        Path path = Paths.get(filename);
        try {
            path = Path.of(ClassLoader.getSystemResource(filename).toURI());
            contents = Files.readString(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return contents;
    }

    public static void main(String[] args) {
        String[] words = loadFile("words_dropped.txt").split("\n");
        System.out.println("Loaded " + words.length + " words");
        // TODO solve me!
        SpellingBee bee = new SpellingBee("ranglty".toCharArray(), 'y');
        

        // TODO sort words!
        Arrays.sort(words);


        // TODO what position in the sorted list is the word "search" ?

        int index = Arrays.binarySearch(words, "search");
        if (index >= 0) {
            System.out.println("Position of the word search: " + (index + 1));
        } else {
            System.out.println("Word not found.");
        }

        // linear search
        int n = 0;
        for(String word : words){
            n++;
            if(bee.checkWord(word)){
                System.out.println(word);
            }
        }

        String findMe = "potato";
        int begin = 0;
        int end = words.length-1;
        int guess = (begin + end/2);
        int check = words[guess].compareTo(findMe);
        System.out.println("word is " + words[guess]);
        System.out.println("check is " + check);

        begin = guess;
        guess= (begin + end/2);
        check = words[guess].compareTo(findMe);
        System.out.println("word is " + words[guess]);
        System.out.println("check is " + check);
        // while(check != 0){
        //     if(check < 0){
        //         begin = guess;
        //     }
        //     else if(check > 0){
        //         end = guess;
        //     }
        //     guess = (begin +end) / 2;
        // }
            

    }
}
