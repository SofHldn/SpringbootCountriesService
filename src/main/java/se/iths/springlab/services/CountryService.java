package se.iths.springlab.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import se.iths.springlab.dtos.CountryDto;
import se.iths.springlab.dtos.CountryPopulation;
import se.iths.springlab.entities.Country;
import se.iths.springlab.mappers.CountryMapper;
import se.iths.springlab.repositories.CountryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService implements se.iths.springlab.services.Service {


    private final CountryMapper countryMapper;
    private CountryRepository countryRepository;

    public CountryService(CountryMapper countryMapper, CountryRepository countryRepository) {
        this.countryMapper = countryMapper;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<CountryDto> listAllCountries() {

        return countryMapper.mapp(countryRepository.findAll());

    }

    @Override
    public Optional<CountryDto> getOne(String countryCode){

        return countryMapper.mapp(countryRepository.findById(countryCode));


    }


    @Override
    public CountryDto createCountry(CountryDto country) {

        if (country.getCountryName().isEmpty())
            throw new RuntimeException();

        return countryMapper.mapp(countryRepository.save(countryMapper.mapp(country)));


    }

    @Override
    public void delete(String cc) {

        countryRepository.deleteById(cc);

    }


    @Override
    public CountryDto replace(String cc, CountryDto countryDto) {

        Optional<Country> country  = countryRepository.findById(cc);
        if(country.isPresent()){
            Country updatedCountry = country.get();
            updatedCountry.setCountryName(countryDto.getCountryName());
            updatedCountry.setCapitol(countryDto.getCapitol());
            updatedCountry.setPopulationMillions(countryDto.getPopulationMillions());
            updatedCountry.setSongId(countryDto.getSongId());
            return countryMapper.mapp(countryRepository.save(updatedCountry));
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Country code " + cc + " not found.");
         }

    }

    @Override
    public CountryDto update(String cc, CountryPopulation countryPopulation) {

        Optional<Country> country  = countryRepository.findById(cc);

        if(country.isPresent()){
            Country updatedCountry = country.get();

            if(countryPopulation.populationMillions != 0)
                updatedCountry.setPopulationMillions(countryPopulation.populationMillions);
            return countryMapper.mapp(countryRepository.save(updatedCountry));

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Country code " + cc + " not found.");
        }


    }

    @Override
    public CountryDto findByName(String name) {

        return countryMapper.mapp(countryRepository.findAllByCountryName(name));

    }

    @Override
    public CountryDto findByCapitol(String capitol) {
        return countryMapper.mapp(countryRepository.findAllByCapitol(capitol));
    }

    @Override
    public List<CountryDto> findByPopulation(double population) {
        return countryMapper.mapp(countryRepository.findAllByPopulationMillions(population));
    }
}
