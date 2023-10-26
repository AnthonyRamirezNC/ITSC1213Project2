public class Stone extends Resource{
    Stone(){
        super("stone");
        super.setQuantity(200);
        this.isCritical = false;
    }

    Stone(int amount){
        super("stone");
        super.setQuantity(amount);
        this.isCritical = false;
        
    }
}