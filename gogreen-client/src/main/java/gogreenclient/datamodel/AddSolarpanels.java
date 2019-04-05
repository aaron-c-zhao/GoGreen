package gogreenclient.datamodel;

import java.time.LocalDate;

public class AddSolarpanels  {

    private String userName;
    private Float area;
    private LocalDate date;
    private int panelId;
    private Float producedKwh;


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

    @Override
    public String toString() {
        return "AddSolarpanels{"
                + "userName='" + userName + '\''
                + ", area=" + area
                + ", date=" + date
                + ", panelId=" + panelId
                + ", producedKwh=" + producedKwh
                + '}';
    }
}
