package br.com.store.controller;

import br.com.store.dto.ProductDTO;
import br.com.store.model.Product;
import br.com.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    private Page<Product> list(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "20") int size) {
        return this.productService.list(page, size);
    }

    @GetMapping("/{id}")
    private Product findById(@PathVariable Long id) {
        return this.productService.findById(id);
    }

    @PostMapping
    private Product save(@RequestBody ProductDTO obj) {
        return this.productService.save(obj);
    }

    @PutMapping("/{id}")
    private Product update(@PathVariable Long id, @RequestBody ProductDTO obj) {
        return this.productService.update(id, obj);
    }

    @PatchMapping("/{id}")
    private Product changePrice(@PathVariable Long id, @RequestBody ProductDTO obj) {
        return this.productService.changePrice(id, obj);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity delete(@PathVariable Long id) {
        this.productService.delete(id);

        return new ResponseEntity<>("Product successfully deleted!", HttpStatus.OK);
    }
}
