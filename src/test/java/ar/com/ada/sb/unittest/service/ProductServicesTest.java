package ar.com.ada.sb.unittest.service;

import ar.com.ada.sb.junittest.model.dto.ProductDTO;
import ar.com.ada.sb.junittest.model.entity.Product;
import ar.com.ada.sb.junittest.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.sb.junittest.model.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServicesTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CycleAvoidingMappingContext context;

    @InjectMocks
    private ProductServices productServices;

    @Test
    public void whenFindAllThenReturnProductList() {
        //GIVEN
        Product productMock = new Product()
                .setId(1L)
                .setName("P1")
                .setDescription("P1 Desc")
                .setPrice(new BigInteger("10"));

        Product productMock2 = new Product()
                .setId(2L)
                .setName("P2")
                .setDescription("P2 Desc")
                .setPrice(new BigInteger("20"));

        // Lista de entrega fake
        List<Product> productListMock = Arrays.asList(productMock, productMock2);

        when(productRepository.findAll()).thenReturn(productListMock);

        //WHEN
        List<ProductDTO> productDTOList = productServices.findAll();

        //THEN
        assertThat(productDTOList.size()).isEqualTo(2);
        assertThat(productDTOList.get(0).getName()).isEqualTo("P1");
        assertThat(productDTOList.get(0).getDescription()).isEqualTo("P1 Desc");
    }

    @Test
    public void whenSaveReturnProductDtoSaved() {
        //GIVEN
        Product productMock = new Product()
                .setId(1L)
                .setName("P1")
                .setDescription("Description 1")
                .setPrice(new BigInteger("1000"));
        when(productRepository.save(any(Product.class))).thenReturn(productMock);
        ProductDTO dto = new ProductDTO();

        //WHEN
        ProductDTO dtoSaved = productServices.save(dto);

        //THEN
        assertThat(dtoSaved.getId()).isEqualTo(1);
    }

}