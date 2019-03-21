package gogreenserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "achievements")
public class Achievements {

    // Define Fields
    @Id
    @Column(name = "username")
    private String userName;

    @Column(name = "level")
    private Float level;

    @Column(name = "tree_hugger")
    private Boolean tree_hugger;

    @Column(name = "anarcho_primitivist")
    private Boolean anarcho_primitivist;

    @Column(name = "celebi")
    private Boolean celebi;

    @Column(name = "stop_cheating")
    private Boolean stop_cheating;

    @Column(name = "sun_absorber")
    private Boolean sun_absorber;

    @Column(name = "power_plant")
    private Boolean power_plant;

    @Column(name = "3_years_ago")
    private Boolean three_years_ago;

    @Column(name = "time_traveler")
    private Boolean time_traveler;

    @Column(name = "vegetarian")
    private Boolean vegetarian;

    @Column(name = "vegan")
    private Boolean vegan;

    @Column(name = "photosynthesizer")
    private Boolean photosynthesizer;

    @Column(name = "please_eat")
    private Boolean please_eat;

    @Column(name = "dutch")
    private Boolean dutch;

    @Column(name = "nice_legs")
    private Boolean nice_legs;

    @Column(name = "teleporter")
    private Boolean teleporter;

    @Column(name = "never_skip_leg_day")
    private Boolean never_skip_leg_day;

    @Column(name = "fat_wallet")
    private Boolean fat_wallet;

    @Column(name = "retirement_fund")
    private Boolean retirement_fund;

    @Column(name = "just_buy_something")
    private Boolean just_buy_something;

    @Column(name = "nokwg29")
    private Boolean nokwg29;

    // Define Getters/Setters for JACKSON conversion

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Float getLevel() {
        return level;
    }

    public void setLevel(Float level) {
        this.level = level;
    }

    public Boolean getTree_hugger() {
        return tree_hugger;
    }

    public void setTree_hugger(Boolean tree_hugger) {
        this.tree_hugger = tree_hugger;
    }

    public Boolean getAnarcho_primitivist() {
        return anarcho_primitivist;
    }

    public void setAnarcho_primitivist(Boolean anarcho_primitivist) {
        this.anarcho_primitivist = anarcho_primitivist;
    }

    public Boolean getCelebi() {
        return celebi;
    }

    public void setCelebi(Boolean celebi) {
        this.celebi = celebi;
    }

    public Boolean getStop_cheating() {
        return stop_cheating;
    }

    public void setStop_cheating(Boolean stop_cheating) {
        this.stop_cheating = stop_cheating;
    }

    public Boolean getSun_absorber() {
        return sun_absorber;
    }

    public void setSun_absorber(Boolean sun_absorber) {
        this.sun_absorber = sun_absorber;
    }

    public Boolean getPower_plant() {
        return power_plant;
    }

    public void setPower_plant(Boolean power_plant) {
        this.power_plant = power_plant;
    }

    public Boolean getThree_years_ago() {
        return three_years_ago;
    }

    public void setThree_years_ago(Boolean three_years_ago) {
        this.three_years_ago = three_years_ago;
    }

    public Boolean getTime_traveler() {
        return time_traveler;
    }

    public void setTime_traveler(Boolean time_traveler) {
        this.time_traveler = time_traveler;
    }

    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public Boolean getVegan() {
        return vegan;
    }

    public void setVegan(Boolean vegan) {
        this.vegan = vegan;
    }

    public Boolean getPhotosynthesizer() {
        return photosynthesizer;
    }

    public void setPhotosynthesizer(Boolean photosynthesizer) {
        this.photosynthesizer = photosynthesizer;
    }

    public Boolean getPlease_eat() {
        return please_eat;
    }

    public void setPlease_eat(Boolean please_eat) {
        this.please_eat = please_eat;
    }

    public Boolean getDutch() {
        return dutch;
    }

    public void setDutch(Boolean dutch) {
        this.dutch = dutch;
    }

    public Boolean getNice_legs() {
        return nice_legs;
    }

    public void setNice_legs(Boolean nice_legs) {
        this.nice_legs = nice_legs;
    }

    public Boolean getTeleporter() {
        return teleporter;
    }

    public void setTeleporter(Boolean teleporter) {
        this.teleporter = teleporter;
    }

    public Boolean getNever_skip_leg_day() {
        return never_skip_leg_day;
    }

    public void setNever_skip_leg_day(Boolean never_skip_leg_day) {
        this.never_skip_leg_day = never_skip_leg_day;
    }

    public Boolean getFat_wallet() {
        return fat_wallet;
    }

    public void setFat_wallet(Boolean fat_wallet) {
        this.fat_wallet = fat_wallet;
    }

    public Boolean getRetirement_fund() {
        return retirement_fund;
    }

    public void setRetirement_fund(Boolean retirement_fund) {
        this.retirement_fund = retirement_fund;
    }

    public Boolean getJust_buy_something() {
        return just_buy_something;
    }

    public void setJust_buy_something(Boolean just_buy_something) {
        this.just_buy_something = just_buy_something;
    }

    public Boolean getNokwg29() {
        return nokwg29;
    }

    public void setNokwg29(Boolean nokwg29) {
        this.nokwg29 = nokwg29;
    }

    // Define toString
    @Override
    public String toString() {
        return "Achievements{" +
            "userName='" + userName + '\'' +
            ", level=" + level +
            ", tree_hugger=" + tree_hugger +
            ", anarcho_primitivist=" + anarcho_primitivist +
            ", celebi=" + celebi +
            ", stop_cheating=" + stop_cheating +
            ", sun_absorber=" + sun_absorber +
            ", power_plant=" + power_plant +
            ", three_years_ago=" + three_years_ago +
            ", time_traveler=" + time_traveler +
            ", vegetarian=" + vegetarian +
            ", vegan=" + vegan +
            ", photosynthesizer=" + photosynthesizer +
            ", please_eat=" + please_eat +
            ", dutch=" + dutch +
            ", nice_legs=" + nice_legs +
            ", teleporter=" + teleporter +
            ", never_skip_leg_day=" + never_skip_leg_day +
            ", fat_wallet=" + fat_wallet +
            ", retirement_fund=" + retirement_fund +
            ", just_buy_something=" + just_buy_something +
            ", nokwg29=" + nokwg29 +
            '}';
    }
}
