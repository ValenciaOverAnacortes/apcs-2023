package unit10;

public class Piday{

    //private double n = 4.0;
    //private int div = 1;
    //public boolean bool = true;
    //private int count = 0;
    public static void main(String[] args) {
        System.out.print(pi(false, 1));
    }

    public static double pi(boolean bool, int div){
        if(div >= 10000){
            return 0.0;
        }
        else{
            
            if(bool== true){
                return -4.0/div + pi(false, div+2);
            }
            if (bool == false){
                return 4.0/div + pi(true, div+2);
            }
    }
    return 0;
}
}
