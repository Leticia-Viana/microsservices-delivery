package br.com.ada.orders.controller;

import br.com.ada.orders.model.dto.OrdersDTORequest;
import br.com.ada.orders.model.dto.OrdersDTOResponse;
import br.com.ada.orders.service.interfaces.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
@Slf4j
@Api(value = "Orders API")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;
    @PostMapping
    public ResponseEntity<OrdersDTOResponse> insertOrder(@Valid @RequestBody final OrdersDTORequest orderRequest){
        log.info("- OrdersController --> Initialized insertOrder...");
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.saveOrder(orderRequest));
    }

    @ApiOperation(value = "Return all orders in database")
    @GetMapping
    public ResponseEntity<List<OrdersDTOResponse>> allOrders(){
        log.info("- OrdersController --> Initialized allOrders...");
        return ResponseEntity.ok(ordersService.getAllOrders());
    }

    @ApiOperation(value = "Return order by id in database")
    @GetMapping("{id}")
    public ResponseEntity<OrdersDTOResponse> getOrderById(@PathVariable("id") Long id){
        log.info("- OrdersController --> Initialized getOrderById...");
        try {
            return ResponseEntity.ok(ordersService.getOrderById(id));
        }catch (EntityNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "Update order by id in database")
    @PatchMapping("{id}")
    public ResponseEntity<OrdersDTOResponse> updateOrder(@PathVariable("id") Long id, @RequestBody OrdersDTORequest ordersDTORequest){
        log.info("- OrdersController --> Initialized updateOrder...");
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.updateOrder(id, ordersDTORequest));
        }catch (EntityNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @ApiOperation(value = "Delete order by id in database")
    @DeleteMapping("{id}")
    public ResponseEntity<OrdersDTOResponse> deleteOrder(@PathVariable("id") Long id){
        log.info("- OrdersController --> Initialized updateOrder...");
        try {
            ordersService.deleteOrderById(id);
            return ResponseEntity.noContent().build();
        }catch (EntityNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
