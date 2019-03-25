package gogreenserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "achievements")
public class Achievements {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "achieve_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "achievement")
    private String achievement;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    @Override
    public String toString() {
        return "Achievements{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", achievement=" + achievement +
            '}';
    }
}
