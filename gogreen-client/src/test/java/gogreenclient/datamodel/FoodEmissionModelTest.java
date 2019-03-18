package gogreenclient.datamodel;

import gogreenclient.config.AppConfig;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = AppConfig.class)
public class FoodEmissionModelTest {

    private FoodEmissionModel model;

    @Test
    public void getFoodEmissions() {
    }

    @Test
    public void compareFood() {
        model = new FoodEmissionModel();
        String use = new String("nuts");
        String res = model.compareFood(use, use, 1, 1);
        assertEquals("There is no difference in CO2 between both meals.", res);
    }

    @Test
    public void updateUserCareer() {
    }

    @Test
    public void getCareer() {
    }
}