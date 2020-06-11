package ar.com.ada.sb.unittest.model.dto;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.util.Date;

public class ProductsDto {

    private Long id;

    @NotBlank(message = "")
    private String name;

    @NotBlank(message = "")
    private String description;

    @NotBlank(message = "")
    private BigInteger price;

    private Date createAt;

    private Date updateAt;

    public ProductsDto setName(String name) {
        this.name = name;
        return this;
    }

    public ProductsDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductsDto setPrice(BigInteger price) {
        this.price = price;
        return this;
    }
}