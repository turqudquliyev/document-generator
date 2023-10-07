package az.ingress.controller;

import az.ingress.dao.entity.ProductEntity;
import az.ingress.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/internal/v1/products")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class InternalProductController {
    ProductService productService;

    @GetMapping
    public List<ProductEntity> getAll() {
        return productService.getAll();
    }
}