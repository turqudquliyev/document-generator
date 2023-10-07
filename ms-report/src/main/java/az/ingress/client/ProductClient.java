package az.ingress.client;

import az.ingress.model.client.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "product-service", url = "${client.urls.ms-product}")
public interface ProductClient {
    @GetMapping
    List<Product> getAllProducts();
}