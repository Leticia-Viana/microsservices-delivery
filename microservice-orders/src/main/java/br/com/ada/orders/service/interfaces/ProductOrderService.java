package br.com.ada.orders.service.interfaces;

import br.com.ada.orders.model.entity.OrderEntity;
import br.com.ada.orders.model.entity.ProductEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProductOrderService {

    public void addProductOrder(ProductEntity productEntity, OrderEntity orderEntity, Long quantity);
}
