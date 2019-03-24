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
    private Boolean treeHugger;

    @Column(name = "anarcho_primitivist")
    private Boolean anarchoPrimitivist;

    @Column(name = "celebi")
    private Boolean celebi;

    @Column(name = "stop_cheating")
    private Boolean stopCheating;

    @Column(name = "sun_absorber")
    private Boolean sunAbsorber;

    @Column(name = "power_plant")
    private Boolean powerPlant;

    @Column(name = "five_billion_years")
    private Boolean fiveBillionYears;

    @Column(name = "thank_uv_much")
    private Boolean thankUvMuch;

    @Column(name = "vegetarian")
    private Boolean vegetarian;

    @Column(name = "vegan")
    private Boolean vegan;

    @Column(name = "photosynthesizer")
    private Boolean photosynthesizer;

    @Column(name = "please_eat")
    private Boolean pleaseEat;

    @Column(name = "dutch")
    private Boolean dutch;

    @Column(name = "nice_legs")
    private Boolean niceLegs;

    @Column(name = "teleporter")
    private Boolean teleporter;

    @Column(name = "never_skip_leg_day")
    private Boolean neverSkipLegDay;

    @Column(name = "fat_wallet")
    private Boolean fatWallet;

    @Column(name = "retirement_fund")
    private Boolean retirementFund;

    @Column(name = "just_buy_something")
    private Boolean justBuySomething;

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

    public Boolean getTreeHugger() {
        return treeHugger;
    }

    public void setTreeHugger(Boolean treeHugger) {
        this.treeHugger = treeHugger;
    }

    public Boolean getAnarchoPrimitivist() {
        return anarchoPrimitivist;
    }

    public void setAnarchoPrimitivist(Boolean anarchoPrimitivist) {
        this.anarchoPrimitivist = anarchoPrimitivist;
    }

    public Boolean getCelebi() {
        return celebi;
    }

    public void setCelebi(Boolean celebi) {
        this.celebi = celebi;
    }

    public Boolean getStopCheating() {
        return stopCheating;
    }

    public void setStopCheating(Boolean stopCheating) {
        this.stopCheating = stopCheating;
    }

    public Boolean getSunAbsorber() {
        return sunAbsorber;
    }

    public void setSunAbsorber(Boolean sunAbsorber) {
        this.sunAbsorber = sunAbsorber;
    }

    public Boolean getPowerPlant() {
        return powerPlant;
    }

    public void setPowerPlant(Boolean powerPlant) {
        this.powerPlant = powerPlant;
    }

    public Boolean getFiveBillionYears() {
        return fiveBillionYears;
    }

    public void setFiveBillionYears(Boolean fiveBillionYears) {
        this.fiveBillionYears = fiveBillionYears;
    }

    public Boolean getThankUvMuch() {
        return thankUvMuch;
    }

    public void setThankUvMuch(Boolean thankUvMuch) {
        this.thankUvMuch = thankUvMuch;
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

    public Boolean getPleaseEat() {
        return pleaseEat;
    }

    public void setPleaseEat(Boolean pleaseEat) {
        this.pleaseEat = pleaseEat;
    }

    public Boolean getDutch() {
        return dutch;
    }

    public void setDutch(Boolean dutch) {
        this.dutch = dutch;
    }

    public Boolean getNiceLegs() {
        return niceLegs;
    }

    public void setNiceLegs(Boolean niceLegs) {
        this.niceLegs = niceLegs;
    }

    public Boolean getTeleporter() {
        return teleporter;
    }

    public void setTeleporter(Boolean teleporter) {
        this.teleporter = teleporter;
    }

    public Boolean getNeverSkipLegDay() {
        return neverSkipLegDay;
    }

    public void setNeverSkipLegDay(Boolean neverSkipLegDay) {
        this.neverSkipLegDay = neverSkipLegDay;
    }

    public Boolean getFatWallet() {
        return fatWallet;
    }

    public void setFatWallet(Boolean fatWallet) {
        this.fatWallet = fatWallet;
    }

    public Boolean getRetirementFund() {
        return retirementFund;
    }

    public void setRetirementFund(Boolean retirementFund) {
        this.retirementFund = retirementFund;
    }

    public Boolean getJustBuySomething() {
        return justBuySomething;
    }

    public void setJustBuySomething(Boolean justBuySomething) {
        this.justBuySomething = justBuySomething;
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
        return "Achievements{"
            + "userName='" + userName + '\''
            + ", level=" + level
            + ", tree_hugger=" + treeHugger
            + ", anarcho_primitivist=" + anarchoPrimitivist
            + ", celebi=" + celebi
            + ", stop_cheating=" + stopCheating
            + ", sun_absorber=" + sunAbsorber
            + ", power_plant=" + powerPlant
            + ", three_years_ago=" + fiveBillionYears
            + ", time_traveler=" + thankUvMuch
            + ", vegetarian=" + vegetarian
            + ", vegan=" + vegan
            + ", photosynthesizer=" + photosynthesizer
            + ", please_eat=" + pleaseEat
            + ", dutch=" + dutch
            + ", nice_legs=" + niceLegs
            + ", teleporter=" + teleporter
            + ", never_skip_leg_day=" + neverSkipLegDay
            + ", fat_wallet=" + fatWallet
            + ", retirement_fund=" + retirementFund
            + ", just_buy_something=" + justBuySomething
            + ", nokwg29=" + nokwg29
            + '}';
    }
}
