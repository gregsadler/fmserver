package Services;

/**
 * Created by Greg on 5/19/2017.
 */

public class FillRequest {
    /**
     * Fields from HTTP fill request
     */
    private String userName;
    private String generations;

    /**
     * Getters and Setters for class
     */

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGenerations() {
        return generations;
    }

    public void setGenerations(String generations) {
        this.generations = generations;
    }

    private int numanceestors(int generations){
        int sum = 0;
        for(int i = 0; i < generations; i++){
            sum += Math.pow(2,i+1);
        }
        sum = sum + 1;
        return sum;
    }

}
