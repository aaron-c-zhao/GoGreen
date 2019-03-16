package gogreenserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "food_emission")
public class FoodEmission {

    // define fields
    @Id
    @Column(name = "food")
    private String food;

    @Column(name = "emission")
    private String emission;

    @Column(name = "miles")
    private String miles;

    public FoodEmission() {
        food = null;
        emission = null;
        miles = null;
    }

    /**
     * Constructor for FoodEmission.
     *
     * @param food     Primary key. This is Food name.
     * @param emission The emission of that particular food.
     * @param miles    The equivalent Co2 produced by driving X number of miles.
     */
    public FoodEmission(String food, String emission, String miles) {
        super();
        this.food = food;
        this.emission = emission;
        this.miles = miles;
    }

    // define getters and setters to be used by JACKSON

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public String getEmission() {
        return emission;
    }

    public void setEmission(String emission) {
        this.emission = emission;
    }

    public String getMiles() {
        return miles;
    }

    public void setMiles(String miles) {
        this.miles = miles;
    }

    // define toString for JACKSON
    @Override
    public String toString() {
        return "FoodEmission [food=" + food + ", emission=" + emission + ", miles=" + miles + "]";
    }


}
