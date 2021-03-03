package se.iths.springlab.services;

import se.iths.springlab.dtos.CountryDto;
import se.iths.springlab.dtos.CountryPopulation;

import java.util.List;
import java.util.Optional;

public interface Service {
    List<CountryDto> listAllCountries();

    Optional<CountryDto> getOne(String countryCode);

    CountryDto createCountry(CountryDto country);

    void delete(String cc);

    CountryDto replace(String cc, CountryDto countryDto);

    CountryDto update(String cc, CountryPopulation countryPopulation);

    CountryDto findByName(String name);

    CountryDto findByCapitol(String capitol);

    List<CountryDto> findByPopulation(double population);


}
