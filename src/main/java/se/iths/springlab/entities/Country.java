package se.iths.springlab.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Countries")
public class Country {

    @Id
    private String countryCode;
    private String countryName;
    private String capitol;
    private double populationMillions;
    @Column(name="songCode")
    private Long songId;

    public Country() {
    }

    public Country(String countryCode, String countryName, String capitol, double populationMillions, Long songId) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.capitol = capitol;
        this.populationMillions = populationMillions;
        this.songId =songId;
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


}
