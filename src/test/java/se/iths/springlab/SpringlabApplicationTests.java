package se.iths.springlab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import se.iths.springlab.dtos.CountryDto;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.sound.midi.Patch;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringlabApplicationTests {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate testClient;


    @Test
    void contextLoadsGet() {

//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept","application/xml");
//        testClient.exchange("http://localhost:" + port + "/countries/", HttpMethod.GET, new HttpEntity<>(headers), CountryDto[].class);

        initializeDatabase();

        var result = testClient.getForEntity("http://localhost:" + port + "/countries/", CountryDto[].class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().length).isGreaterThan(0);

    }

    @Test
    void contextLoadsGetOne() {
        initializeDatabase();


        var result = testClient.getForEntity("http://localhost:" + port + "/countries/" +"cc",CountryDto.class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getCountryName()).isEqualTo("country");
        assertThat(result.getBody().getCapitol()).isEqualTo("capitol");
        assertThat(result.getBody().getPopulationMillions()).isEqualTo(1);
        assertThat(result.getBody().getSongId()).isEqualTo(1L);



    }

    private void initializeDatabase() {
        CountryDto countryDto = new CountryDto("tt", "test", "test", 1, 1L);
        CountryDto countryDto1 = new CountryDto("cc", "country", "capitol", 1, 1L);
        testClient.postForEntity("http://localhost:" + port + "/countries", countryDto, CountryDto.class);
        testClient.postForEntity("http://localhost:" + port + "/countries", countryDto1, CountryDto.class);
    }

    @Test
    void contextLoadsPost() {

        CountryDto countryDto= new CountryDto("tp","testPost","testPost",1, 1L);
        var result=  testClient.postForEntity("http://localhost:"+port+"/countries", countryDto, CountryDto.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        var resultGet = testClient.getForEntity("http://localhost:" + port + "/countries/" + countryDto.getCountryCode(),CountryDto.class);


        assertThat(result.getBody().getCountryName()).isEqualTo("testPost");
        assertThat(result.getBody().getCapitol()).isEqualTo("testPost");
        assertThat(result.getBody().getPopulationMillions()).isEqualTo(1);
        assertThat(result.getBody().getSongId()).isEqualTo(1L);




    }

    @Test
    void contextLoadsDelete() {


        CountryDto countryDto= new CountryDto("td","testDelete","testDelete",1, 1L);
        testClient.postForEntity("http://localhost:"+port+"/countries", countryDto, CountryDto.class);

        testClient.delete("http://localhost:" + port + "/countries/"+ countryDto.getCountryCode());


        var result =  testClient.getForEntity("http://localhost:" + port + "/countries/" +countryDto.getCountryCode(),CountryDto.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }



    @Test
    void contextLoadsPut() {

        CountryDto countryDto= new CountryDto("tp","testPut","testPut",1, 1L);
        testClient.postForEntity("http://localhost:"+port+"/countries", countryDto, CountryDto.class);


        CountryDto updatedCountryDto= new CountryDto("tp","testPut1","testPut1",2, 2L);
        testClient.put("http://localhost:" + port + "/countries/" +countryDto.getCountryCode(),updatedCountryDto, CountryDto.class);

        var result = testClient.getForEntity("http://localhost:" + port + "/countries/" + countryDto.getCountryCode(),CountryDto.class);


        assertThat(result.getBody().getCountryName()).isEqualTo(updatedCountryDto.getCountryName());
        assertThat(result.getBody().getCapitol()).isEqualTo(updatedCountryDto.getCapitol());
        assertThat(result.getBody().getPopulationMillions()).isEqualTo(updatedCountryDto.getPopulationMillions());
        assertThat(result.getBody().getSongId()).isEqualTo(updatedCountryDto.getSongId());



        }

    @Test
    void contextLoadsPatch() {

        CountryDto countryDto= new CountryDto("tp","testPatch","testPatch",1, 1L);
        testClient.postForEntity("http://localhost:"+port+"/countries", countryDto, CountryDto.class);


        CountryDto updatedCountryDto= new CountryDto("tp","testPatch1","testPatch1",2, 2L);
        testClient.patchForObject("http://localhost:" + port + "/countries/" +countryDto.getCountryCode(),updatedCountryDto, CountryDto.class);

        var result = testClient.getForEntity("http://localhost:" + port + "/countries/" + countryDto.getCountryCode(),CountryDto.class);


        assertThat(result.getBody().getCountryName()).isEqualTo(countryDto.getCountryName());
        assertThat(result.getBody().getCapitol()).isEqualTo(countryDto.getCapitol());
        assertThat(result.getBody().getPopulationMillions()).isEqualTo(updatedCountryDto.getPopulationMillions());
        assertThat(result.getBody().getSongId()).isEqualTo(countryDto.getSongId());



    }


}
