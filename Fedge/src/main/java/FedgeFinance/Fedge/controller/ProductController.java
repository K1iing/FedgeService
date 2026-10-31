package FedgeFinance.Fedge.controller;

import FedgeFinance.Fedge.model.dto.ProductDTO;
import FedgeFinance.Fedge.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ProductDTO dto) {
        productService.createProduct(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
