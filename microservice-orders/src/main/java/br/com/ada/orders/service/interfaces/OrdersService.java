package br.com.ada.orders.service.interfaces;

import br.com.ada.orders.model.dto.OrdersDTORequest;
import br.com.ada.orders.model.dto.OrdersDTOResponse;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface OrdersService {

    public OrdersDTOResponse saveOrder(OrdersDTORequest order);

    public List<OrdersDTOResponse> getAllOrders();

    public OrdersDTOResponse getOrderById(Long id);

    public OrdersDTOResponse updateOrder(Long id, OrdersDTORequest ordersDTORequest);

    public void deleteOrderById(Long id);
}
