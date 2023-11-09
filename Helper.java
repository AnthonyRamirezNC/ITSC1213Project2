import java.util.ArrayList;

/*
Helper class used to compare generators, check for construction requirements and more.
 */
public class Helper {
    public static boolean canConstruct(ArrayList<Resource> constructionCosts, ArrayList<Resource> currentResources){
        {
            for(Resource constuctResource : constructionCosts){
                boolean foundMatchingType = false;
                for(Resource currentResource : currentResources){
                    if(currentResource.getClass().equals(constuctResource.getClass())){
                        foundMatchingType = true;
                        //check if we have enough resources
                        if(currentResource.getQuantity() >= constuctResource.getQuantity()){
                            break;
                        }
                        else {
                            return false;
                        }
                    }
                }
                if(!foundMatchingType) {
                    return false;
                }
            }
            return true;    //if we get here, we have enough resources
        }
    }

    public static void consumeConstructionCosts(ArrayList<Resource> constructionCost, ArrayList<Resource> currentResources){
        for(Resource currentConstructionResource : constructionCost){
            for(Resource currentResource : currentResources){
                if(currentResource.getClass().equals(currentConstructionResource.getClass())){
                    currentResource.consume(currentConstructionResource.getQuantity());
                }
            }
        }
    }

    public static void addToExistingResources(ArrayList<Resource> currentResources, ArrayList<Resource> resourcesToAddTo){
        for(Resource resource: currentResources){
            for(Resource currentResourceToAdd : resourcesToAddTo){
                if(resource.getClass().equals(currentResourceToAdd.getClass())) {
                    //resource match found so add resource
                    resource.add(currentResourceToAdd.getQuantity());
                }
            }
        }
    }

    public static void collectGeneratorResources(ArrayList<Resource> currentResources, ArrayList<Generator> generators){
        for(Generator generator : generators){
            int amountProduced = generator.getResourceProductionRate();
            if(generator instanceof Mine){
                System.out.println("Collected " + amountProduced + " " + generator.getProduct().getName() + " from " + generator.getName());
                currentResources.get(2).add(amountProduced);
            }else if(generator instanceof Village){
                System.out.println("Collected " + amountProduced + " " + generator.getProduct().getName() + " from " + generator.getName());
                currentResources.get(0).add(amountProduced);
            }else if(generator instanceof Lumberjacks){
                System.out.println("Collected " + amountProduced + " " + generator.getProduct().getName() + " from " + generator.getName());
                currentResources.get(1).add(amountProduced);
            }
        }
    }

    public static void generateScore(TextManagementGame textManagementGame){
        int score = 0;
        int roundScore = TextManagementGame.round * 100;
        int generatorScore = textManagementGame.getGenerators().size() * 500;
        //calculate gold score
        int goldScore = textManagementGame.getResources().get(0).getQuantity() * 3;
        //calculate wood score
        int woodScore = textManagementGame.getResources().get(1).getQuantity() * 2;
        //calculate stone score
        int stoneScore = textManagementGame.getResources().get(2).getQuantity() * 1;

        System.out.println("Final Score Breakdown");
        System.out.println("Rounds Survived: +" + roundScore + " points");
        System.out.println("Generators Built: +" + generatorScore + " points");
        System.out.println("Gold Gained: +" + goldScore + " points");
        System.out.println("Wood Gained: +" + woodScore + " points");
        System.out.println("Stone Gained: +" + stoneScore + " points");

    }
}
