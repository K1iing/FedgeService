package FedgeFinance.Fedge.repository;

import FedgeFinance.Fedge.model.OrderServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderServiceRepository extends JpaRepository<OrderServiceEntity, Long> {
}
