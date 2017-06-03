package Model;

/**
 * Created by Greg on 5/19/2017.
 */

public class AuthToken {
    private String userName;
    private String personID;

    private String authToken;
    private String DateTime;

    private static Long duration;

    public static void setduration(Long parameterduration){
        duration = parameterduration;
    }


    public void setAuthToken(){
        String id;
        int ids;
        if(this.authToken == null) {
            DatabaseAccess.DBConnection dobj = new DatabaseAccess.DBConnection();
            id = dobj.getlastauthid();
            if(id != null && !id.equals("")){
                try {
                    ids = Integer.parseInt(id);
                    ids++;
                    this.authToken = Integer.toString(ids);
                }catch(NumberFormatException e){
                    this.authToken = Integer.toString((int)(Math.random()*100+50));
                }
            }
            else{
                this.authToken = "0";
            }
            dobj.CloseConnection();
        }

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getDateTime() {
        return DateTime;
    }

    public void setDateTime(String dateTime) {
        DateTime = dateTime;
    }

    public boolean validtoken(){
        if(userName == null || authToken == null || DateTime == null){
            return false;
        }
        else{
            Long timenow = System.currentTimeMillis();
            Long toketime = Long.parseLong(DateTime);
            Long thisduration = timenow - toketime;
            thisduration = thisduration/1000; //convert duration from milliseconds to seconds
            if(thisduration >= duration){
                return false;
            }
            else{
                return true;
            }
        }
    }


    @Override
    public String toString() {
        return "AuthToken{" +
                "userName='" + userName + '\'' +
                ", personID='" + personID + '\'' +
                ", authToken='" + authToken + '\'' +
                ", DateTime='" + DateTime + '\'' +
                '}';
    }
}
