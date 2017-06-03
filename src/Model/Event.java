package Model;

/**
 * Created by Greg on 5/19/2017.
 */

public class Event {

    private String descendant;
    private String eventID;
    private String personID;
    private String latitude;
    private String longitude;
    private String country;
    private String city;
    private String eventType;
    private String year;


    public void setEventID(){
        String id;
        int ids;
        if(this.eventID == null) {
            DatabaseAccess.DBConnection dobj = new DatabaseAccess.DBConnection();
            id = dobj.getlasteventid();
            if(id != null && !id.equals("")){
                try {
                    ids = Integer.parseInt(id);
                    ids++;
                    this.eventID = Integer.toString(ids);
                }catch(NumberFormatException e){
                    this.eventID = Integer.toString((int)(Math.random()*100+50));
                }
            }
            else{
                this.eventID = "0";
            }
            dobj.CloseConnection();
        }
    }


    public String getDescendant() {
        return descendant;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID)
    {
        if(this.eventID == null) {
            this.eventID = eventID;
        }
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }


    @Override
    public String toString() {
        return "Event{" +
                "descendant='" + descendant + '\'' +
                ", eventID='" + eventID + '\'' +
                ", personID='" + personID + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", eventType='" + eventType + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
