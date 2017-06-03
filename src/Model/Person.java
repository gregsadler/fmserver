package Model;

/**
 * Created by Greg on 5/19/2017.
 */

public class Person {
    private String personID;
    private String descendant;
    private String firstName;
    private String lastName;
    private String gender;
    private String father;
    private String mother;
    private String spouse;



    public void setpersonID(){
        String id;
        int ids;
        if(this.personID == null) {
            DatabaseAccess.DBConnection dobj = new DatabaseAccess.DBConnection();
            id = dobj.getlastpersonid();
            if(id != null && !id.equals("")){
                try {
                    ids = Integer.parseInt(id);
                    ids++;
                    this.personID = Integer.toString(ids);
                }catch(NumberFormatException e){
                    this.personID = Integer.toString((int)(Math.random()*100+50));
                }
            }
            else{
                this.personID = "0";
            }
            dobj.CloseConnection();
        }
    }



    /**
     * Creating a person object with a user object
     * @param user : person object to extract data from
     */
    public void adduserdata(User user){
        this.firstName = user.getfirstName();
        this.lastName = user.getlastName();
        this.gender = user.getGender();
        this.descendant = user.getUserName();
    }



    public String getpersonID() {
        setpersonID();
        return personID;
    }

    public void setpersonID(String personID) {
        if(this.personID == null) {
            this.personID = personID;
        }
    }

    public String getDescendant() {
        return descendant;
    }

    public void setDescendant(String descendant) {
        this.descendant = descendant;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getSpouse() {
        return spouse;
    }

    public void setSpouse(String spouse) {
        this.spouse = spouse;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personID='" + personID + '\'' +
                ", descendant='" + descendant + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", father='" + father + '\'' +
                ", mother='" + mother + '\'' +
                ", spouse='" + spouse + '\'' +
                '}';
    }
}
