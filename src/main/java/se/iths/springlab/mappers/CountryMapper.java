package se.iths.springlab.mappers;

import org.springframework.stereotype.Component;
import se.iths.springlab.dtos.CountryDto;
import se.iths.springlab.entities.Country;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CountryMapper {

    public CountryMapper() {
    }

    public CountryDto mapp(Country country){
        return new CountryDto(country.getCountryCode(), country.getCountryName(), country.getCapitol(), country.getPopulationMillions());
    }

    public Country mapp(CountryDto countryDto){
        return new Country(countryDto.getCountryCode(), countryDto.getCountryName(), countryDto.getCapitol(), countryDto.getPopulationMillions());
    }

    public Optional<CountryDto> mapp(Optional<Country> optionalCountry){

        if(optionalCountry.isEmpty())
            return Optional.empty();
        return Optional.of(mapp(optionalCountry.get()));

    }

    public List<CountryDto> mapp(List<Country> all){

        return all
                .stream()
                .map(this::mapp)
                .collect(Collectors.toList());




    }



}
