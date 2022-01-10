package br.com.store.model;

import br.com.store.dto.ProductDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Arrays;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private OffsetDateTime created;

    public static Product create(ProductDTO obj) {
        return new ModelMapper().map(obj, Product.class);
    }

    public static Product create(ProductDTO dto, Product actual) {
        BeanUtils.copyProperties(dto, actual);
        return actual;
    }
}
