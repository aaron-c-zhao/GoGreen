package gogreenclient.datamodel;

import java.util.Map;


public class Achievements {

    // Define Fields
    private String userName;

    private Float level;

    private Boolean treeHugger;

    private Boolean anarchoPrimitivist;

    private Boolean celebi;

    private Boolean stopCheating;

    private Boolean sunAbsorber;

    private Boolean powerPlant;

    private Boolean threeYearsAgo;

    private Boolean timeTraveler;

    private Boolean vegetarian;

    private Boolean vegan;

    private Boolean photosynthesizer;

    private Boolean pleaseEat;

    private Boolean dutch;

    private Boolean niceLegs;

    private Boolean teleporter;

    private Boolean neverSkipLegDay;

    private Boolean fatWallet;

    private Boolean retirementFund;

    private Boolean justBuySomething;

    private Boolean nokwg29;

    private Map<String, Boolean> nameSpace;

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

    public Boolean getThreeYearsAgo() {
        return threeYearsAgo;
    }

    public void setThreeYearsAgo(Boolean threeYearsAgo) {
        this.threeYearsAgo = threeYearsAgo;
    }

    public Boolean getTimeTraveler() {
        return timeTraveler;
    }

    public void setTimeTraveler(Boolean timeTraveler) {
        this.timeTraveler = timeTraveler;
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
            + ", three_years_ago=" + threeYearsAgo
            + ", time_traveler=" + timeTraveler
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
