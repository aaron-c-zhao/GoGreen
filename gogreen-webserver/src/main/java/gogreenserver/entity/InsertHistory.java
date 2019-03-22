package gogreenserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "insert_history")
public class InsertHistory {

    @Id
    @Column(name = "username")
    private String userName;

    @Column(name = "activity_name")
    private String activityName;

    @Column(name = "activity_price", nullable = true)
    private Float activityPrice;

    @Column(name = "alternate_activity", nullable = true)
    private String alternateActivity;

    @Column(name = "alternate_activity_price", nullable = true)
    private Float alternateActivityPrice;

    @Column(name = "activity_is_localproduce", nullable = true)
    private Boolean activityIsLocalproduce;

    @Column(name = "alternate_activity_is_localproduce", nullable = true)
    private Boolean alternateActivityIsLocalproduce;

    @Column(name = "transport_distance_km", nullable = true)
    private Float transportDistanceKm;

    @Column(name = "energy_activity_duration_minutes", nullable = true)
    private Float energyActivityDurationMinutes;

    @Column(name = "energy_activity_temperature_area_m2", nullable = true)
    private Float energyActivityTempAreaM2;

    @Column(name = "energy_activity_temperature_degrees_decreased", nullable = true)
    private Float energyActivityTempDegreesDecreased;

    // Define Getters/Setters for JACKSON


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Float getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(Float activityPrice) {
        this.activityPrice = activityPrice;
    }

    public String getAlternateActivity() {
        return alternateActivity;
    }

    public void setAlternateActivity(String alternateActivity) {
        this.alternateActivity = alternateActivity;
    }

    public Float getAlternateActivityPrice() {
        return alternateActivityPrice;
    }

    public void setAlternateActivityPrice(Float alternateActivityPrice) {
        this.alternateActivityPrice = alternateActivityPrice;
    }

    public Boolean getActivityIsLocalproduce() {
        return activityIsLocalproduce;
    }

    public void setActivityIsLocalproduce(Boolean activityIsLocalproduce) {
        this.activityIsLocalproduce = activityIsLocalproduce;
    }

    public Boolean getAlternateActivityIsLocalproduce() {
        return alternateActivityIsLocalproduce;
    }

    public void setAlternateActivityIsLocalproduce(Boolean alternateActivityIsLocalproduce) {
        this.alternateActivityIsLocalproduce = alternateActivityIsLocalproduce;
    }

    public Float getTransportDistanceKm() {
        return transportDistanceKm;
    }

    public void setTransportDistanceKm(Float transportDistanceKm) {
        this.transportDistanceKm = transportDistanceKm;
    }

    public Float getEnergyActivityDurationMinutes() {
        return energyActivityDurationMinutes;
    }

    public void setEnergyActivityDurationMinutes(Float energyActivityDurationMinutes) {
        this.energyActivityDurationMinutes = energyActivityDurationMinutes;
    }

    public Float getEnergyActivityTempAreaM2() {
        return energyActivityTempAreaM2;
    }

    public void setEnergyActivityTempAreaM2(Float energyActivityTempAreaM2) {
        this.energyActivityTempAreaM2 = energyActivityTempAreaM2;
    }

    public Float getEnergyActivityTempDegreesDecreased() {
        return energyActivityTempDegreesDecreased;
    }

    public void setEnergyActivityTempDegreesDecreased(Float energyActivityTempDegreesDecreased) {
        this.energyActivityTempDegreesDecreased = energyActivityTempDegreesDecreased;
    }

    // Define toString


    @Override
    public String toString() {
        return "InsertHistory{"
            + "userName='" + userName + '\''
            + ", activityName='" + activityName + '\''
            + ", activityPrice=" + activityPrice
            + ", alternateActivity='" + alternateActivity + '\''
            + ", alternateActivityPrice=" + alternateActivityPrice
            + ", activityIsLocalproduce=" + activityIsLocalproduce
            + ", alternateActivityIsLocalproduce=" + alternateActivityIsLocalproduce
            + ", transportDistanceKm=" + transportDistanceKm
            + ", energyActivityDurationMinutes=" + energyActivityDurationMinutes
            + ", energyActivityTempAreaM2=" + energyActivityTempAreaM2
            + ", energyActivityTempDegreesDecreased=" + energyActivityTempDegreesDecreased
            + '}';
    }
}
