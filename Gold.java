public class Gold extends Resource{
    Gold(){
        super("gold");
        super.add(500);
        super.setIsCrticial(true);
    }

    Gold(int amount){
        super("gold");
        super.add(amount);
        this.setIsCrticial(true);
        
    }


}