package az.ingress.service;

import az.ingress.dao.entity.ProductEntity;
import az.ingress.dao.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ProductService {
    ProductRepository productRepository;

    public void saveAll(List<ProductEntity> products) {
        productRepository.saveAll(products);
    }

    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }
}