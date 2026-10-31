package FedgeFinance.Fedge.service;

import FedgeFinance.Fedge.model.OrderServiceEntity;
import FedgeFinance.Fedge.model.ProductEntity;
import FedgeFinance.Fedge.model.dto.OrderServiceDTO;
import FedgeFinance.Fedge.model.enums.PaymentStatusENUM;
import FedgeFinance.Fedge.repository.OrderServiceRepository;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceService {
    private final OrderServiceRepository orderServiceRepository;
    private final ProductService productService;
    private final SqsTemplate sqsTemplate;


    public OrderServiceService(OrderServiceRepository orderServiceRepository, ProductService productService, SqsTemplate sqsTemplate) {
        this.orderServiceRepository = orderServiceRepository;
        this.productService = productService;
        this.sqsTemplate = sqsTemplate;
    }

    public OrderServiceDTO getOrder(Long id) throws Exception {
        OrderServiceEntity orderService = orderServiceRepository.findById(id).orElseThrow(() -> new Exception("Ordem não encontrada"));

        return new OrderServiceDTO(orderService);
    }

    public List<OrderServiceDTO> getAllOrders() throws Exception {
        return orderServiceRepository.findAll().stream().map(OrderServiceDTO::new).toList();
    }


    public OrderServiceDTO createOrder(OrderServiceDTO dto) throws Exception {
        ProductEntity product = productService.getProductById(dto.getProductId());

        if (product.getQuantity() < dto.getQuantity()) {
            throw new Exception("Quantidade insuficiente de produto");
        }

        product.setQuantity(product.getQuantity() - dto.getQuantity());
        productService.updateProduct(product);

        OrderServiceEntity orderService = new OrderServiceEntity();

        orderService.setProductId(product.getId());
        orderService.setQuantity(dto.getQuantity());
        orderService.setPaymentStatus(String.valueOf(PaymentStatusENUM.PENDING));
        orderService.setPaymentType(String.valueOf(dto.getPaymentType()));
        orderService.setOrderTime(LocalDateTime.now());

        OrderServiceEntity order = orderServiceRepository.save(orderService);

        sqsTemplate.send("myQueue", order.toString());

        return new OrderServiceDTO(order);

    }

    public OrderServiceDTO updateOrder(OrderServiceDTO dto, Long id) throws Exception {
        OrderServiceEntity orderService = orderServiceRepository.findById(id).orElseThrow(() -> new Exception("Ordem não encontrada"));

        ProductEntity product = productService.getProductById(dto.getProductId());

        product.setQuantity(product.getQuantity() - dto.getQuantity());
        productService.updateProduct(product);

        orderService.setProductId(product.getId());
        orderService.setQuantity(dto.getQuantity() + orderService.getQuantity());
        orderService.setOrderTime(LocalDateTime.now());

        OrderServiceEntity order = orderServiceRepository.save(orderService);

        sqsTemplate.send("myQueue", order.toString());

        return new OrderServiceDTO(order);
    }
}
