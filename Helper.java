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
}
