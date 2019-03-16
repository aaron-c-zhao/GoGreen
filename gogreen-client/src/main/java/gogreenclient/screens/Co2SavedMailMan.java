package gogreenclient.screens;

import gogreenclient.datamodel.User;
import gogreenclient.datamodel.UserCareer;
import org.springframework.stereotype.Component;


public class Co2SavedMailMan {

    private String co2Saved;
    private UserCareer career;

    public Co2SavedMailMan(String co2Saved, UserCareer career) {
        this.co2Saved = co2Saved;
        this.career = career;
    }

    public void setCo2Saved(String co2Saved) {
        this.co2Saved = co2Saved;
    }

    public UserCareer getCareer() {
        return career;
    }

    public void setCareer(UserCareer career) {
        this.career = career;
    }

    public String getCo2Saved() {
        return co2Saved;
    }
}
