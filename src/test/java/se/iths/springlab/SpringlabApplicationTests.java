package se.iths.springlab;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import se.iths.springlab.dtos.CountryDto;

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
        var result = testClient.getForEntity("http://localhost:" + port + "/countries/", CountryDto[].class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().length).isGreaterThan(0);

    }

    @Test
    void contextLoadsGetOne() {



        CountryDto countryDto= new CountryDto("CC","testGetOne","testGetOne",1);
        var result = testClient.getForEntity("http://localhost:" + port + "/countries/" +countryDto.getCountryCode(),CountryDto.class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().getCapitol()).isEqualTo("testGetOne");

    }

    @Test
    void contextLoadsPost() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept","application/xml");
        CountryDto countryDto= new CountryDto("CC","testPut","testPut",1);
        var result=  testClient.postForEntity("http://localhost:"+port+"/countries", countryDto, CountryDto.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody().getPopulationMillions()).isEqualTo(1);

        //verify with a get request for person with id
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Accept","application/xml");
//        SongDto songDto= new SongDto(1L,"t",2,"a");
//        var res=  testClient.postForEntity("http://localhost:"+port+"/",songDto, SongDto.class);
//        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    void contextLoadsDelete() {
        testClient.delete("http://localhost:" + port + "/countries/", "cc");


//        @Test
//        void deleteFromVarableTest() {​​
//
//            testClient.delete("http://localhost:"+port+"/songs/2");
//            var res=  testClient.getForEntity("http://localhost:"+port+"/songs/2",SongDto.class);
//            assertThat(res.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }



    @Test
    void contextLoadsPut() {
        CountryDto countryDto= new CountryDto("CC","testPut","testPut",1);

        testClient.put("http://localhost:" + port + "/countries/" +countryDto.getCountryCode(),CountryDto.class);


//        @Test
//        void put() {​​
//            SongDto songDto= new SongDto(1L,"t",2,"a");
//            testClient.put("http://localhost:"+port+"/songs/put/1",songDto);
//            var res=  testClient.getForEntity("http://localhost:"+port+"/songs/1",SongDto.class);
//            assertThat(res.getBody().getTitle()).isEqualTo("t");
//            assertThat(res.getBody().getArtist()).isEqualTo("a");


        }

    @Test
    void contextLoadsPatch() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept","application/xml");
        CountryDto countryDto= new CountryDto("CC","testPut","testPut",1);
        var result = testClient.patchForObject("http://localhost:" + port + "/countries/",countryDto, CountryDto.class, "CC");
        //testClient.exchange("http://localhost:" + port + "/countries/", HttpMethod.PATCH, headers, countryDto,"CC");


        //ResponseEntity<CountryDto> responseEntity = testClient.getForEntity("http://localhost:" + port + "/countries/",CountryDto.class, "CC");


//        testClient.exchange("http://localhost:" + port + "/countries/", HttpMethod.GET, new HttpEntity<>(headers), CountryDto[].class);

    }


}
