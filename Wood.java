public class Wood extends Resource{
    Wood(){
        super("wood");
        super.setQuantity(0);
        this.isCritical = false;
    }

    Wood(int amount){
        super("wood");
        super.setQuantity(amount);
        this.isCritical = false;
    }
}