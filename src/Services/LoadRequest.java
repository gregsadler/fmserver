package Services;

/**
 * Class that stores load request information
 * Created by Greg on 5/19/2017.
 */

public class LoadRequest {
    Model.User[] users;
    Model.Person[] persons;
    Model.Event[] events;

    /**
     * @return : length of user array
     */
    public int getusersize(){
        return users.length;
    }

    /**
     * @return : length of person array
     */
    public int getpersonssize(){
        return persons.length;
    }

    /**
     * @return : length of events array
     */
    public int geteventssize(){
        return events.length;
    }

    /**
     * Return user object at the given index
     * @param index : index in user array
     * @return : user object
     */
    public Model.User getUser(int index) {
        Model.User user = new Model.User();
        if (index >= 0 && index < users.length){
            return users[index];
        }
        else{
            return user;
        }
    }

    /**
     * return person object at given index
     * @param index : index of person in person array
     * @return : person object
     */
    public Model.Person getPerson(int index){
        Model.Person pers = new Model.Person();
        if (index >= 0 || index < persons.length){
            return persons[index];
        }
        else{
            return pers;
        }
    }

    /**
     * return event object at given index
     * @param index : index of event in event array
     * @return : event object
     */
    public Model.Event getEvent(int index)
    {
        Model.Event event = new Model.Event();
        if (index >= 0 || index < events.length){
            return events[index];
        }
        else{
            return event;
        }
    }

}
