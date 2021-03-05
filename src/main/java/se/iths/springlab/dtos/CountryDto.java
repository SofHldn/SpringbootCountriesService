package se.iths.springlab.dtos;

public class CountryDto {

    private String countryCode;
    private String countryName;
    private String capitol;
    private double populationMillions;
    private Long songId;


    public CountryDto(String countryCode, String countryName, String capitol, double populationMillions, Long songId) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.capitol = capitol;
        this.populationMillions = populationMillions;
        this.songId = songId;

    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCapitol() {
        return capitol;
    }

    public void setCapitol(String capitol) {
        this.capitol = capitol;
    }

    public double getPopulationMillions() {
        return populationMillions;
    }

    public void setPopulationMillions(double populationMillions) {
        this.populationMillions = populationMillions;
    }

    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    @Override
    public String toString() {
        return "CountryDto{" +
                "countryCode='" + countryCode + '\'' +
                ", countryName='" + countryName + '\'' +
                ", capitol='" + capitol + '\'' +
                ", populationMillions=" + populationMillions +
                ", songId=" + songId +
                '}';
    }


}
