package se.iths.springlab.controllers;

import se.iths.springlab.dtos.CountryDto;
import se.iths.springlab.dtos.CountryPopulation;
import se.iths.springlab.services.Service;

import java.util.List;
import java.util.Optional;

public class TestService implements Service {

    @Override
    public List<CountryDto> listAllCountries() {
        return null;
    }

    @Override
    public Optional<CountryDto> getOne(String countryCode) {

        if(countryCode.equals("AA"))
            return Optional.of(new CountryDto("AA", "Test", "Test", 1));

        return Optional.empty();
    }

    @Override
    public CountryDto createCountry(CountryDto country) {
        return null;
    }

    @Override
    public void delete(String cc) {

    }

    @Override
    public CountryDto replace(String cc, CountryDto countryDto) {
        return null;
    }

    @Override
    public CountryDto update(String cc, CountryPopulation countryPopulation) {
        return null;
    }

    @Override
    public CountryDto findByName(String name) {
        return null;
    }

    @Override
    public CountryDto findByCapitol(String capitol) {
        return null;
    }

    @Override
    public List<CountryDto> findByPopulation(double population) {
        return null;
    }
}
