public class Gold extends Resource{
    Gold(){
        super("gold");
        super.add(200);
        super.setIsCrticial(true);
    }

    Gold(int amount){
        super("gold");
        super.add(amount);
        this.setIsCrticial(true);
        
    }


}