package FedgeFinance.Fedge.model.dto;

import FedgeFinance.Fedge.model.OrderServiceEntity;
import FedgeFinance.Fedge.model.enums.PaymentStatusENUM;
import FedgeFinance.Fedge.model.enums.PaymentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderServiceDTO {
    private UUID id;
    private UUID productId;
    private Integer quantity;
    private LocalDateTime orderTime;
    private String paymentStatus;
    private PaymentTypeEnum paymentType;

    public OrderServiceDTO(OrderServiceEntity entity) {
        this.id = entity.getId();
        this.productId = entity.getProductId();
        this.quantity = entity.getQuantity();
        this.orderTime = LocalDateTime.now();
        this.paymentType = PaymentTypeEnum.valueOf(entity.getPaymentType());
        this.paymentStatus = PaymentStatusENUM.PENDING.name();
    }
}
