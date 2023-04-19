package br.com.ada.orders.service.impls;

import br.com.ada.orders.model.dto.OrdersDTORequest;
import br.com.ada.orders.model.dto.OrdersDTOResponse;
import br.com.ada.orders.model.entity.OrdersEntity;
import br.com.ada.orders.model.mapper.OrdersMapper;
import br.com.ada.orders.repository.OrdersRepository;
import br.com.ada.orders.service.interfaces.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersServiceImpl implements OrdersService {

    private final OrdersMapper ordersMapper = new OrdersMapper();
    @Autowired
    private OrdersRepository ordersRepository;

    @Override
    public OrdersDTOResponse saveOrder(OrdersDTORequest orderRequest) {
        log.info("- OrdersServiceImpl --> Entering order: {} in database" ,orderRequest);
        ordersRepository.save(ordersMapper.dtoToEntity(orderRequest));
        return ordersMapper.requestDtoToResponse(orderRequest);
    }

    @Override
    public List<OrdersDTOResponse> getAllOrders() {
        log.info("- OrdersServiceImpl --> Initialized getAllOrders...");
        return ordersMapper.listOrdersResponse(ordersRepository.findAll());
    }

    @Override
    public OrdersDTOResponse getOrderById(Long id) {
        Optional<OrdersEntity> entityOptional = ordersRepository.findById(id);
        entityOptional.orElseThrow(() -> new EntityNotFoundException("Order not found for id: " + id));

        return ordersMapper.dtoResponseToEntity(entityOptional.get());
    }

    @Override
    public OrdersDTOResponse updateOrder(Long id, OrdersDTORequest ordersDTORequest) {
        log.info("- OrdersServiceImpl --> Updating order: {} in database", ordersDTORequest);
        log.info("- OrdersServiceImpl --> Updating order by id: {} in database", id);

        Optional<OrdersEntity> optionalOrder = ordersRepository.findById(id);
        optionalOrder.orElseThrow(() -> new EntityNotFoundException("Order not found."));

        OrdersEntity entity = ordersMapper.dtoToEntity(ordersDTORequest);
        entity.setOrderId(id);

        ordersRepository.save(entity);
        return ordersMapper.dtoResponseToEntity(entity);
    }

    @Override
    public void deleteOrderById(Long id) {
        Optional<OrdersEntity> entityById = ordersRepository.findById(id);
        entityById.orElseThrow(() -> new EntityNotFoundException("Order not found."));
        log.info("Deleting order by id: {}", id);
        ordersRepository.deleteById(id);
    }
}
