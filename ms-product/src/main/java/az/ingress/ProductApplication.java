package az.ingress;

import az.ingress.dao.entity.ProductEntity;
import az.ingress.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;

import static lombok.AccessLevel.PRIVATE;

@SpringBootApplication
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class ProductApplication implements CommandLineRunner {
    ProductService productService;

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @Override
    @SneakyThrows
    public void run(String... args) throws Exception {
        LinkedList<ProductEntity> products = new LinkedList<>();
        for (long i = 1; i <= 50000; i++) {
            ProductEntity product = ProductEntity.builder()
                    .name("Product" + i)
                    .stock((int) (i * 100))
                    .build();
            products.add(product);
        }
        productService.saveAll(products);
    }
}