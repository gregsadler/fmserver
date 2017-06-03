package Services;

/**
 * Created by Greg on 5/26/2017.
 */

public class EventResult {

    Model.Event[] events;

    public void addarray(Model.Event[] thisarray){
        events = thisarray;
    }


    public Model.Event[]  getarray(){
        return events;
    }
}
