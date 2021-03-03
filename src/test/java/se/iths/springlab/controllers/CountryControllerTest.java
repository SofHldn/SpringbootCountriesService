package se.iths.springlab.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CountryControllerTest {

    @Test
    void listAll() {
    }

//    @Test
//    void listOne() {
//    }
    @Test
    void callOneWithValidCcReturnsOneCountry(){
        CountryController countryController = new CountryController(new TestService());
        var country = countryController.listOne("AA");

        assertThat(country.getCountryCode()).isEqualTo("AA");
        assertThat(country.getCountryName()).isEqualTo("Test");
        assertThat(country.getCapitol()).isEqualTo("Test");
        assertThat(country.getPopulationMillions()).isEqualTo(1);

    }

    @Test
    void callOneWithInvalidIdThrowsExceptionWithResponseStatus404(){

        CountryController countryController = new CountryController(new TestService());
        var exception = assertThrows(ResponseStatusException.class, ()->countryController.listOne("BB"));
        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);

    }



    @Test
    void create() {
    }

    @Test
    void delete() {
    }

    @Test
    void replace() {
    }

    @Test
    void update() {
    }
}