import java.util.ArrayList;

/**
 * This class is pojo for grocery
 * Each item should be a grocery object used for the assignment
 */
public class Grocery {
    private String name;
    private double weight,volume;
    /**
     * This is the constructor used when creating a new instance for each grocery
     * @param name the name of the grocery
     * @param weight the weight of the grocery
     * @param volume the volume of the grocery
     */
    public Grocery(String name,double weight,double volume){
        this.name = name;
        this.weight = weight;
        this.volume = volume;
    }

    /**
     * Method for setting name
     * @param name the name if the item
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method for setting the volume
     * @param volume the volume of item
     */
    public void setVolume(double volume) {
        this.volume = volume;
    }

    /**
     * Method for setting the weight
     * @param weight the weight of the item
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * This method is used for getting the volume of the object
     * @return the volume of the object
     */
    public double getVolume() {
        return volume;
    }

    /**
     * This method is used for getting the weight of the object
     * @return the weight of the object
     */
    public double getWeight() {
        return weight;
    }

    /**
     * This method is used for getting the name of the object
     * @return the name of the object
     */
    public String getName() {
        return name;
    }

}
