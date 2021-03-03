package se.iths.springlab.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se.iths.springlab.dtos.CountryDto;
import se.iths.springlab.dtos.CountryPopulation;
import se.iths.springlab.services.Service;

import java.util.List;

@RestController
public class CountryController {

    Logger log = LoggerFactory.getLogger(CountryController.class);


    private Service service;


    public CountryController(Service service) {
        this.service = service;
    }

    @GetMapping("/countries")
    public List<CountryDto> listAll(){

        return service.listAllCountries();

    }

    @GetMapping("/countries/{cc}")
    public CountryDto listOne(@PathVariable String cc){
        log.info("For your information we got a request for country with country code="+cc);
        return service.getOne(cc)
                .orElseThrow(()-> getStatusException(cc));

    }

    private ResponseStatusException getStatusException(String cc) {
        log.error("Wrong country code, " + cc);
        return new ResponseStatusException(HttpStatus.NOT_FOUND,
         "Country code " + cc + " not found.");
    }

    @PostMapping("/countries")
    @ResponseStatus(HttpStatus.CREATED)
    public CountryDto create(@RequestBody CountryDto country){
        log.info("Call to create with country " + country);
        return service.createCountry(country);

    }


    @DeleteMapping("/countries/{cc}")
    public void delete(@PathVariable String cc){

        service.delete(cc);
    }

    @PutMapping("/countries/{cc}")
    public CountryDto replace(@RequestBody CountryDto countryDto, @PathVariable String cc){

        return service.replace(cc, countryDto);


    }

    @PatchMapping("/countries/{cc}")
    public CountryDto update (@RequestBody CountryPopulation countryPopulation, @PathVariable String cc){

        return service.update(cc, countryPopulation);
    }

    @GetMapping("/countries/search")
    @ResponseBody
    public CountryDto getParameters(@RequestParam String countryCode) {
        var result = service.getOne(countryCode);
        return result.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Country code " + countryCode + " Not Found"));
    }




}
