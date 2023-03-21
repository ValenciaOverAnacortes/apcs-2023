package unit10;

public class Piday{

    //private double n = 4.0;
    //private int div = 1;
    //public boolean bool = true;
    //private int count = 0;
    public static void main(String[] args) {
        System.out.print(pi(0.0, true, 1));
    }

    public static double pi(double n, boolean bool, int div){
        if(div < 100){
            if(bool == false){
                n = n - (4.0/(div));
                div = div + 2;
                bool = true;
                pi(n, bool, div);
                
            }
    
            if(bool == true){
                n = n + (4.0/(div));
                div = div + 2;
                bool = false;
                pi(n, bool, div);
            }
        }
        return n;
    }
}
