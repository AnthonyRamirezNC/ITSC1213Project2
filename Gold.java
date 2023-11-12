public class Gold extends Resource{
    public int goldGainedOverGame = 0;
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

    @Override
    public int scoreImpact(){
        return 3 * goldGainedOverGame;
    }

    @Override
    public void add(int amountToAdd){
        super.add(amountToAdd);
        goldGainedOverGame += amountToAdd;
    }
}