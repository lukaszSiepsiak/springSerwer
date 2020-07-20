package pl.siepsiak.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.siepsiak.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsById(Long id);
}
