package ar.com.ada.sb.unittest.service;

import ar.com.ada.sb.unittest.model.dto.ProductDTO;
import ar.com.ada.sb.unittest.model.entity.Product;
import ar.com.ada.sb.unittest.model.mapper.CycleAvoidingMappingContext;
import ar.com.ada.sb.unittest.model.mapper.ProductMapper;
import ar.com.ada.sb.unittest.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productServices")
public class ProductServices implements Services<ProductDTO> {

    @Autowired
    @Qualifier("productRepository")
    private ProductRepository productRepository;

    @Autowired
    @Qualifier("cycleAvoidingMappingContext")
    private CycleAvoidingMappingContext context;

    private ProductMapper productMapper = ProductMapper.MAPPER;

    @Override
    public List<ProductDTO> findAll() {
        // aqui se aplica la entrega fake en base al mock definido
        List<Product> productList = productRepository.findAll();

        List<ProductDTO> productDTOList = productMapper.toDto(productList, context);
        return productDTOList;
    }

    @Override
    public ProductDTO save(ProductDTO dto) {
        Product productToSave = productMapper.toEntity(dto, context);

        // aqui se aplica la entrega fake en base al mock definido
        Product productSaved = productRepository.save(productToSave);

        ProductDTO productDTOSaved = productMapper.toDto(productSaved, context);
        return productDTOSaved;
    }

    @Override
    public void delete(Long id) {

    }
}