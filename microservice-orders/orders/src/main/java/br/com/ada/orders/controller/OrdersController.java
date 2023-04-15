package br.com.ada.orders.controller;

import br.com.ada.orders.model.dto.OrdersDTORequest;
import br.com.ada.orders.model.dto.OrdersDTOResponse;
import br.com.ada.orders.model.entity.OrdersEntity;
import br.com.ada.orders.service.interfaces.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@Slf4j
@Api(value = "Orders API")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;
    @PostMapping("/insertorder")
    public ResponseEntity<OrdersDTOResponse> insertOrder(@RequestBody final OrdersDTORequest orderRequest){
        log.info("- OrdersController --> Initialized insertOrder...");
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.saveOrder(orderRequest));
    }

    @ApiOperation(value = "Return all orders in database")
    @GetMapping("/allOrders")
    public ResponseEntity<List<OrdersDTOResponse>> allOrders(){
        log.info("- OrdersController --> Initialized allOrders...");
        return ResponseEntity.ok(ordersService.getAllOrders());
    }

    @GetMapping
    public ResponseEntity<OrdersDTOResponse> getOrderById(@PathVariable("id") Long id){
        log.info("- OrdersController --> Initialized getOrderById...");

        return ResponseEntity.ok(ordersService.getOrderById(id));
    }

    @PatchMapping("{id}")
    public ResponseEntity<OrdersDTOResponse> updateOrder(@PathVariable("id") Long id, @RequestBody OrdersDTORequest ordersDTORequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.updateOrder(id, ordersDTORequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<OrdersDTOResponse> deleteOrder(@PathVariable("id") Long id){
        ordersService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }
}
