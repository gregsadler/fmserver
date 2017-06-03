package Services;

import com.google.gson.Gson;
import java.io.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 *  A class to generate ancestor data
 * Created by Greg on 5/26/2017.
 */

public class GenerateData {

    String path = "resources/json/";

    private int generations;

    private String username;

    /**
     * Constructor to the number generations
     * @param gens
     * @param username
     */
    public GenerateData(int gens, String username){
        generations = gens;
        this.username = username;
    }


    /**
     * Nested class of array full of fake events
     */
    private class events{
        private String[] data;
        public void setArray(String[] array){
            this.data = array;
        }
        public String getevent(int index){
            return data[index];
        }
        public int getdatasize(){
            return data.length;
        }
    }

    /**
     * Nested class of generated names
     */
    private class names{
        private String[] data;
        public String getname(int index){
            return data[index];
        }
        public int getdatasize(){
            return data.length;
        }
    }

    /**
     * Nested class of generated locations
     */
    private class locationshell{
        private locations[] data;
        public locations getlocation(int index){
            return data[index];
        }
        public int getdatasize(){
            return data.length;
        }
    }

    /**
     * Nested location class
     */
    private class locations{
        String country;
        String city;
        String latitude;
        String longitude;

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
    }


    /**
     * Main function to generate ancestor data
     * @param username
     */
    public void generate(String username){
        int numanceestors = numanceestors(generations);
        Model.Person[] individs = new Model.Person[numanceestors];
        int counter = 0;

        int numevents = 2;
        //Data from json files:
        names fnames = generatenames("fnames");
        names mnames = generatenames("mnames");
        names lnames = generatenames("snames");

        //database access objects
       DatabaseAccess.DBConnection dbobj = new DatabaseAccess.DBConnection();

        //person model objects
        Model.Person pers;
        Model.Person pers2;

        Model.User thisuser = dbobj.getUser(username);


        //variables to fill

        int numfnames = fnames.getdatasize(); //sizes of json objects
        int numlnames = lnames.getdatasize();

        int fnameind; //indexes to use when randomly selecting data
        int lnameind;
        int personid;
        int evcount= 0;
        int eventid;
        try {
            personid = Integer.parseInt(dbobj.getlastpersonid());
            eventid = Integer.parseInt(dbobj.getlasteventid());
        }catch(NumberFormatException e){
            personid = (int)(Math.random()*100 + 40);
            eventid = (int)(Math.random()*100 + 40);
        }

        for(int i = 0; i < numanceestors; i++){ //Create the correct number of generations
            pers = new Model.Person(); //Each iteration is a new ancestor
            fnameind = (int) (Math.random() * numfnames );
            lnameind = (int)(Math.random() * numlnames);
            if(i != 0) {
                pers.setpersonID(Integer.toString(personid));
                pers.setGender(getgender());
            }
            pers.setDescendant(username);
            pers.setFirstName(fnames.getname(fnameind));
            pers.setLastName(lnames.getname(lnameind));
            if(i==0){
                pers.setpersonID(thisuser.getpersonID());
                pers.setFirstName(thisuser.getfirstName());
                pers.setLastName(thisuser.getlastName());
                pers.setGender(thisuser.getGender());
            }
            for(int j = 0; j < numevents; j++){ //Add specified number of events for every ancestor
                dbobj.addevent(genevents(pers, eventid, evcount));
                eventid++;
                evcount++;
                if(evcount > 4){
                    evcount = 0;
                }
            }
            individs[counter] = pers;
            counter++;
            personid++;
        }

        int fatherindex;
        int motherindex;
        for(int k =0; k < individs.length; k++){ //iterate through and add family relationships (father, mother, spouse)
            fatherindex = k * 2 + 2; //This is the position of the individuals father in array (if there are enough generations)
            motherindex = k * 2 + 1; //This is the position of the individuals mother in array (if there are enough generations)
            if(k!= 0) {
                if ((k % 2) == 0) {
                    individs[k].setSpouse(individs[k - 1].getpersonID());
                } else {
                    individs[k].setSpouse(individs[k + 1].getpersonID());
                }
            }
            if(fatherindex < individs.length){
                individs[k].setFather(individs[fatherindex].getpersonID());
            }
            if(motherindex < individs.length){
                individs[k].setMother(individs[motherindex].getpersonID());
            }
            dbobj.addPerson(individs[k]);
        }
        dbobj.CloseConnection();
    }


    /**
     * Generate events using the person object given
     * @param pers
     * @param eventid
     * @param evcount
     * @return
     */
    public Model.Event genevents(Model.Person pers, int eventid, int evcount){
        Model.Event ev1 = new Model.Event();
        locationshell loc = generatelocations();
        events evs = generateevents();
        int numevs = evs.getdatasize();
        int numloc = loc.getdatasize();
        int locind = (int) (Math.random() * numloc);
        String year = Integer.toString((int)(Math.random()*300 + 1700));
        ev1.setEventID(Integer.toString(eventid));
        ev1.setDescendant(username);
        ev1.setPersonID(pers.getpersonID());
        ev1.setYear(year);
        ev1.setCity(loc.getlocation(locind).getCity());
        ev1.setCountry(loc.getlocation(locind).getCountry());
        ev1.setLatitude(loc.getlocation(locind).getLatitude());
        ev1.setLongitude(loc.getlocation(locind).getLongitude());
        ev1.setEventType(evs.getevent(evcount));
        ev1.setPersonID(pers.getpersonID());
        return ev1;
}


    /**
     * return event type
     * @return
     */
    private events generateevents(){
        String[] evs = {"Birth","Death","Christening","Birth","Marriage"};
        events objec = new events();
        objec.setArray(evs);
        return objec;
    }

    /**
     * randomly returns a gender as string
     * @return
     */
    private String getgender(){
        String[] gender = {"m","f"};
        String out = gender[(int)(Math.random()+.5)]; //randomly generate gender
        return out;
    }


    /**
     * Returns names class full of generated names
     * @param file
     * @return
     */
    private names generatenames(String file){
        names firstnames = new names();
        try {
            Gson gson = new Gson();
            String text;
            FileReader fil = new FileReader(path + file + ".json");

            // Convert
            firstnames = gson.fromJson(fil, names.class);
            return firstnames;
        }catch(java.io.FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return firstnames;
    }

    /**
     * Returns object full of generated locations from stored files
     * @return
     */
    private locationshell generatelocations(){
        locationshell locs = new locationshell();
        try {
            Gson gson = new Gson();
            String text;
            FileReader fil = new FileReader(path  + "locations.json");

            // Convert
            locs = gson.fromJson(fil, locationshell.class);
            return locs;
        }catch(java.io.FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return locs;
    }

    /**
     * Calculates the number of ancestors to generate based on the number of generations
     * @param generations
     * @return
     */
    private int numanceestors(int generations){
        int sum = 0;
        for(int i = 0; i < generations; i++){
            sum += Math.pow(2,i+1);
        }
        sum = sum + 1;
        return sum;
    }


}
