package se.iths.springlab.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.iths.springlab.dtos.CountryDto;
import se.iths.springlab.entities.Country;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, String > {
    Country findAllByCountryName(String countryName);
    Country findAllByCapitol(String capitol);
    List<Country> findAllByPopulationMillions(double populationMillions);
}
