import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SpellingBee {

    private char[] letters;
    private char mustUse;

    public SpellingBee(char[] a, char b){
        letters = a;
        mustUse = b;

    }
    public boolean checkWord(String word) {
        char[] arr = word.toCharArray();
        boolean both = false;
        boolean both2 = false;
        if(arr.length >= 4){
            for (int a = 0; a < arr.length; a++) {
                if(arr[a] == mustUse){
                    both = true;
                }
            }
            for(int b = 0; b < letters.length; b++){
                if(arr[b] == letters[b]){
                    both2 = true;
                }
            }
            if(both && both2){
                return true;
            }
            else{
                return false;
            }
        }
        
        else{
            System.out.println("The word has to be at least 4 characters");
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
        // SpellingBee bee = new SpellingBee("ranglty".toCharArray(), 'y');

        // TODO sort words!

        // TODO what position in the sorted list is the word "search" ?
        // linear search
        int n = 0;
        for(String word : words){
            n++;
            if(word.equals("frank")){
                System.out.println(n);
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
        while(check != 0){
            if(check < 0){
                begin = guess;
            }
            else if(check > 0){
                end = guess;
            }
            guess = (begin +end) / 2;
        }
            

    }
}
