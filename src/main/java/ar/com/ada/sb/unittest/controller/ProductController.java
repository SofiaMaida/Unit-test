package ar.com.ada.sb.unittest.controller;

import ar.com.ada.sb.unittest.model.dto.ProductDTO;
import ar.com.ada.sb.unittest.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    @Qualifier("productServices")
    private ProductServices productServices;

    @PostMapping({ "", "/" })
    public ResponseEntity addNewProduct(@Valid @RequestBody ProductDTO productDTO) throws URISyntaxException {
        ProductDTO productDTOSaved = productServices.save(productDTO);
        return ResponseEntity
                .created(new URI("/products/" + productDTOSaved.getId()))
                .body(productDTOSaved);
    }

}