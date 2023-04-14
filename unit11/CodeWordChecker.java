package unit11;

// 2018 FRQ #3
// https://secure-media.collegeboard.org/ap/pdf/ap18-frq-computer-science-a.pdf#page=12
interface StringChecker {
    /** Returns true if str is valid. */
    boolean isValid(String str);
}

public class CodeWordChecker implements StringChecker {

    private int max;
    private int min;
    private String notContaining;
    // TODO implement me!

    public CodeWordChecker(int ma, int mi, String nc){
        max = ma;
        min = mi;
        notContaining = nc;
    }

    /** Returns true if str is valid. */
    public boolean isValid(String str) {
        String[] a = str.toCharArray();
        boolean temp = false;
        boolean temp2 = false;
        if(str.length() >= min && str.length()<= max){
            temp = true;
        }
        else{
            return false;
        }
        for(int i = 0; i< a.length; i++){
            if(a[i] == notContaining){
                return false;
            }
            else{
                temp2 = true;
            }
        }
        if(temp && temp2){
            return true;
        }
        return false; // replace me!
    }

    static boolean check(boolean test) throws AssertionError {
        if (!test)
            throw new AssertionError("sad panda");
        return test;
    }

    public static void main(String[] args) {
        // uncomment the following lines to test your code
        StringChecker sc1 = new CodeWordChecker(5, 8, "$");
        check(sc1.isValid("happy"));
        check(!sc1.isValid("happy$"));
        check(!sc1.isValid("Code"));
        check(!sc1.isValid("happyCode"));
        check(!sc1.isValid("happy$Code"));
        check(!sc1.isValid("Code$happy"));

        StringChecker sc2 = new CodeWordChecker("pass");
        check(sc2.isValid("MyPass"));
        check(!sc2.isValid("Mypassport"));
        check(!sc2.isValid("happy"));
        check(!sc2.isValid("1,000,000,000,000,000"));

        System.out.println("Happy Panda! \uD83D\uDC3C");
    }

}
