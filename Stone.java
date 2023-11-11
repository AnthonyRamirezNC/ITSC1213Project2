public class Stone extends Resource{
    Stone(){
        super("stone");
        super.add(1);
    }

    Stone(int amount){
        super("stone");
        super.add(amount);
    }

    @Override
    public int scoreImpact(){
        return getQuantity();
    }
}