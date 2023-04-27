package unit11;

public class BeatThat {
    

    private int dice1;
    private int dice2;
    private int marcPoints;
    private int frankPoints;


    public void roll(){
        dice1 = (int)(Math.random()*6)+1;
        dice2 = (int)(Math.random()*6)+1;
    }

    public int compare(){
        if(dice1 > dice2){
            return dice1*10 + dice2;
        }
        else if(dice2 < dice1){
            return dice2*10 + dice1;
        }
        else{
            return dice1*10 + dice2;
        }
    }

    public void startGame(){
        while(marcPoints < 5 && frankPoints < 5){
            BeatThat Marc = new BeatThat();
            BeatThat Frank = new BeatThat();    
            Marc.roll();
            int a = Marc.compare();
            System.out.println("Beat That!" + " " + a);
            Frank.roll();
            int b = Frank.compare() - 54;
            System.out.println("I only got " + b + " 😭");
            if(a > b){
                System.out.println("Marc wins, as usual");
                marcPoints++;
            }
            else if(b > a){
                System.out.println("WOW, this is the first time I see Frank winning something...");
                frankPoints++;
            }
            else{
                System.out.println("Looks like we have a draw! ROLL AGAIN");
            }
        }
    }




    public static void main(String[] args) {
        BeatThat game = new BeatThat();
        game.startGame();
        if(game.marcPoints > game.frankPoints){
            System.out.println("In conclusion, Marc wins");
        }

        else if(game.marcPoints < game.frankPoints){
            System.out.println("WOW, this is incredible, FRANK WON");
        }
    }

}
