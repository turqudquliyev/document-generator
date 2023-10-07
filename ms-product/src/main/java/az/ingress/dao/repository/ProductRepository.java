package az.ingress.dao.repository;

import az.ingress.dao.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    List<ProductEntity> findAll();
}