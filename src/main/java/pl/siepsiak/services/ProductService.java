package pl.siepsiak.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.siepsiak.model.Product;
import pl.siepsiak.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listAll(){
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    public void save(Product product){
        productRepository.save(product);
    }

    public Product get(Long id){
        return productRepository.findById(id).get();
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public boolean productExist(Product product){
       return productRepository.existsById(product.getId());
    }

}
