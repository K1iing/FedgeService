package FedgeFinance.Fedge.model;

import FedgeFinance.Fedge.model.dto.OrderServiceDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderServiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID productId;
    private Integer quantity;
    private LocalDateTime orderTime;
    private String paymentStatus;
    private String paymentType;

    public OrderServiceEntity(OrderServiceDTO dto) {
        this.id = dto.getId();
        this.quantity = dto.getQuantity();
        this.orderTime = dto.getOrderTime();
        this.paymentStatus = dto.getPaymentStatus();
        this.paymentType = String.valueOf(dto.getPaymentType());
    }

    @Override
    public String toString() {
        return "OrderServiceEntity{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", orderTime=" + orderTime +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", paymentType='" + paymentType + '\'' +
                '}';
    }
}
