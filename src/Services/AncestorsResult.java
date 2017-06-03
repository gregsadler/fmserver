package Services;

/**
 * Created by Greg on 5/26/2017.
 */

public class AncestorsResult {
    Model.Person[] ancestors;

    public void addarray(Model.Person[] thisarray){
        ancestors = thisarray;
    }


    public Model.Person[]  getarray(){
        return ancestors;
    }
}
