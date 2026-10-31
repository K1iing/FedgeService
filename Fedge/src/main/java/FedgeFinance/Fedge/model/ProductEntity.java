package FedgeFinance.Fedge.model;

import FedgeFinance.Fedge.model.dto.ProductDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public ProductEntity(ProductDTO dto) {
        this.name = dto.getName();
        this.price = dto.getPrice();
        this.quantity = dto.getQuantity();
    }
}
