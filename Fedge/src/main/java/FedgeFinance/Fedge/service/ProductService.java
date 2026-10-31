package FedgeFinance.Fedge.service;

import FedgeFinance.Fedge.model.ProductEntity;
import FedgeFinance.Fedge.model.dto.ProductDTO;
import FedgeFinance.Fedge.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductEntity getProductById(UUID productId) {
        return productRepository.getReferenceById(productId);
    }

    public void updateProduct(ProductEntity product) {
        productRepository.save(product);
    }

    public void createProduct(ProductDTO dto){
        productRepository.save(new ProductEntity(dto));
    }
}
