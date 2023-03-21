public class Marc{
    
    public static void main(String[] args) {
        System.out.println("Advanced Micro Devices > Anacortes Mobile Detailing");
        System.out.println(power(2, 5));
    }

    public static int power(int b, int e){
        if(e == 1){
            return b;
        }
        else{
            int nextPower = power(b, e/2);
            if(e%2 == 1){
                //
                return nextPower * nextPower * b;
            }
            return nextPower * nextPower;
        }
    }
}