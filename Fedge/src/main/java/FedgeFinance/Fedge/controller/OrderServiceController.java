package FedgeFinance.Fedge.controller;

import FedgeFinance.Fedge.model.dto.OrderServiceDTO;
import FedgeFinance.Fedge.service.OrderServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orderservice")
public class OrderServiceController {

    private final OrderServiceService orderServiceService;

    public OrderServiceController(OrderServiceService orderServiceService) {
        this.orderServiceService = orderServiceService;
    }

    @GetMapping()
    public OrderServiceDTO getOrderService(@RequestParam(value = "id") Long id) throws Exception {
        return ResponseEntity.ok(orderServiceService.getOrder(id)).getBody();
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderServiceDTO>> getAllOrders() throws Exception {
        return ResponseEntity.ok().body(orderServiceService.getAllOrders());
    }

    @PostMapping()
    public OrderServiceDTO create(@RequestBody OrderServiceDTO dto) throws Exception {
        return ResponseEntity.ok(orderServiceService.createOrder(dto)).getBody();
    }

    @PutMapping("/{id}")
    public OrderServiceDTO update(@RequestBody OrderServiceDTO dto, @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(orderServiceService.updateOrder(dto, id)).getBody();
    }
}
