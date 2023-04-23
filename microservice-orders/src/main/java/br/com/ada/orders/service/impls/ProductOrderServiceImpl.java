package br.com.ada.orders.service.impls;

import br.com.ada.orders.model.entity.OrderEntity;
import br.com.ada.orders.model.entity.ProductEntity;
import br.com.ada.orders.model.entity.ProductOrderEntity;
import br.com.ada.orders.repository.ProductOrderRepository;
import br.com.ada.orders.service.interfaces.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;
    @Override
    public void addProductOrder(ProductEntity productEntity, OrderEntity orderEntity, Long quantity) {
        ProductOrderEntity productOerderEntity = ProductOrderEntity.builder().orderEntity(orderEntity).productEntity(productEntity).id(quantity).build();
        productOrderRepository.save(productOerderEntity);
    }
}
