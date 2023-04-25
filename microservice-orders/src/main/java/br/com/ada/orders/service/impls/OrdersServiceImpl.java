package br.com.ada.orders.service.impls;

import br.com.ada.orders.model.dto.OrdersDTORequest;
import br.com.ada.orders.model.dto.OrdersDTOResponse;
import br.com.ada.orders.model.entity.OrderEntity;
import br.com.ada.orders.model.entity.ProductEntity;
import br.com.ada.orders.model.mapper.OrdersMapper;
import br.com.ada.orders.repository.OrdersRepository;
import br.com.ada.orders.repository.ProductRepository;
import br.com.ada.orders.service.interfaces.OrdersService;
import br.com.ada.orders.sms.SendNewOrderToDelivey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersServiceImpl implements OrdersService {

    private final OrdersMapper ordersMapper = new OrdersMapper();
    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductOrderServiceImpl productOrderService;

    @Autowired
    private SendNewOrderToDelivey sendNewOrderToDelivey;

    @Override
    @Transactional
    public OrdersDTOResponse saveOrder(OrdersDTORequest orderRequest) {
        log.info("- OrdersServiceImpl --> Entering order: {} in database" ,orderRequest);
        OrderEntity entity = ordersMapper.dtoToEntity(orderRequest);
        OrderEntity newOrder = ordersRepository.save(entity);
        orderRequest.getProductsRequestDTOList().forEach(productsRequest -> {
            Optional<ProductEntity> optional = productRepository.findById(productsRequest.getId());
            if(optional.isPresent()){
                productOrderService.addProductOrder(optional.get(), newOrder, productsRequest.getQuantity());
                optional.get();
            }
            //todo: chamar api de produtos
        });
        //todo: chamar api de storage para fazer descrecimo do estoque
        OrderEntity savedOrder = ordersRepository.save(ordersMapper.dtoToEntity(orderRequest));
        OrdersDTOResponse dtoResponse = ordersMapper.entityToResponseDTO(savedOrder);
        sendNewOrderToDelivey.sendOrder(dtoResponse);
        return dtoResponse;
    }


    @Cacheable(value = "orders")
    @Override
    public List<OrdersDTOResponse> getAllOrders() {
        log.info("- OrdersServiceImpl --> Initialized getAllOrders...");
        return ordersMapper.listOrdersResponse(ordersRepository.findAll());
    }

    @Override
    public OrdersDTOResponse getOrderById(Long id) {
        log.info("- OrdersServiceImpl --> Get order by id: {} in database", id);
        Optional<OrderEntity> entityOptional = ordersRepository.findById(id);
        entityOptional.orElseThrow(() -> new EntityNotFoundException("Order not found for id: " + id));

        return ordersMapper.entityToResponseDTO(entityOptional.get());
    }

    @Override
    public OrdersDTOResponse updateOrder(Long id, OrdersDTORequest ordersDTORequest) {
        log.info("- OrdersServiceImpl --> Updating order: {} in database", ordersDTORequest);
        log.info("- OrdersServiceImpl --> Updating order by id: {} in database", id);

        Optional<OrderEntity> optionalOrder = ordersRepository.findById(id);
        optionalOrder.orElseThrow(() -> new EntityNotFoundException("Order not found."));

        OrderEntity entity = ordersMapper.dtoToEntity(ordersDTORequest);
        entity.setId(id);

        ordersRepository.save(entity);
        return ordersMapper.entityToResponseDTO(entity);
    }

    @Override
    public void deleteOrderById(Long id) {
        Optional<OrderEntity> entityById = ordersRepository.findById(id);
        entityById.orElseThrow(() -> new EntityNotFoundException("Order not found."));
        log.info("Deleting order by id: {}", id);
        ordersRepository.deleteById(id);
    }
}
