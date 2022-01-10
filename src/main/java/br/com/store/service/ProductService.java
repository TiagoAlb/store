package br.com.store.service;

import br.com.store.dto.ProductDTO;
import br.com.store.exception.ResourceNotFoundException;
import br.com.store.model.Product;
import br.com.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(ProductDTO obj) {
        return this.productRepository.save(Product.create(obj));
    }

    public Product update(Long id, ProductDTO obj) {
        Product product = findById(id);
        return this.productRepository.save(Product.create(obj, product));
    }

    public Product changePrice(Long id, ProductDTO obj) {
        Product product = findById(id);
        product.setPrice(obj.getPrice());

        return this.productRepository.save(product);
    }

    public Product findById(Long id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found."));
    }

    public Page<Product> list(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.unsorted());

        return this.productRepository.findAll(pageRequest);
    }

    public void delete(Long id) {
        findById(id);
        this.productRepository.deleteById(id);
    }
}
