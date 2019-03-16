package gogreenclient.screens;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class co2SavedMailMan {

    private String co2Saved;

    public void setCo2Saved(String co2Saved) {
        this.co2Saved = co2Saved;
    }

    public String getCo2Saved() {
        return co2Saved;
    }
}
