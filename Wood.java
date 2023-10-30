public class Wood extends Resource{
    Wood(){
        super("wood");
        super.add(1);
    }

    Wood(int amount){
        super("wood");
        super.add(amount);
    }
}