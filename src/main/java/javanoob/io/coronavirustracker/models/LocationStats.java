package javanoob.io.coronavirustracker.models;

public class LocationStats {

  private String state;

  private String country;

  private int totalCases;

  private int dailyChange;

  public int getDailyChange() {
    return dailyChange;
  }

  public void setDailyChange(int dailyChange) {
    this.dailyChange = dailyChange;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Override
  public String toString() {
    return "LocationStats [country=" + country + ", state=" + state + ", totalCases=" + totalCases + "]";
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public int getTotalCases() {
    return totalCases;
  }

  public void setTotalCases(int totalCases) {
    this.totalCases = totalCases;
  }

}
