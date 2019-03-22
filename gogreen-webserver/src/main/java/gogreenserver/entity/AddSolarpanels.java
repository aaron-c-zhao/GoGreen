package gogreenserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "add_solarpanels")
public class AddSolarpanels {

    // Define Fields
    @Id
    @Column(name = "username")
    private String userName;

    @Column(name = "m2")
    private Float area;

    @Column(name = "date", nullable = true)
    private LocalDate date;

    @Column(name = "panel_id", nullable = true)
    private  int panelId;

    @Column(name = "produced_kwh", nullable = true)
    private Float producedKwh;

    // Define Getters/Setters for JACKSON

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPanelId() {
        return panelId;
    }

    public void setPanelId(int panelId) {
        this.panelId = panelId;
    }

    public Float getProducedKwh() {
        return producedKwh;
    }

    public void setProducedKwh(Float producedKwh) {
        this.producedKwh = producedKwh;
    }

    // Define toString

    @Override
    public String toString() {
        return "AddSolarpanels{" +
            "userName='" + userName + '\'' +
            ", area=" + area +
            ", date=" + date +
            ", panelId=" + panelId +
            ", producedKwh=" + producedKwh +
            '}';
    }
}
