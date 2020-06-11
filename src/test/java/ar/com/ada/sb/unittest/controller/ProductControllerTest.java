package ar.com.ada.sb.unittest.controller;

import ar.com.ada.sb.junittest.model.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void shouldBeCreatedANewProduct() {
        //GIVEN

        // URL
        String url = "http://localhost:" + port + "/products";

        // RequestBody
        ProductDTO newProduct = new ProductDTO()
                .setName("P1")
                .setDescription("P1 Desc")
                .setPrice(new BigInteger("10"));

//        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ProductDTO> request = new HttpEntity<>(newProduct);

        //WHEN
        ResponseEntity<ProductDTO> response = testRestTemplate.exchange(
                url,
                HttpMethod.POST,
                request,
                new ParameterizedTypeReference<ProductDTO>() {}
        );

        //THEN

        // FORMA A: expected vs actual
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1, response.getBody().getId());

        // FORMA B: by method isEqualTo
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getId()).isEqualTo(1);
    }

}