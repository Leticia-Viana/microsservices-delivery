package br.com.ada.orders.controller;

import br.com.ada.orders.model.Orders;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequiredArgsConstructor
@Slf4j
@Api(value = "Orders API")
public class OrdersController {


    @PostMapping
    public ResponseEntity<Orders> insertOrder(@RequestBody final Orders orderRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(Orders.builder()
                        .requestDate(Date.from(Instant.now()))
                .build());
    }

    @ApiOperation(value = "Return all orders in database")
    @GetMapping("/allOrders")
    public List<Orders> allOrders(){
        log.info("Initialized process...");

        Orders order1 = Orders.builder()
                .orderId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE)
                .requestDate(Date.from(Instant.now()))
                .clientName("Diego")
                .deliveryAddress("Rua Jaracatiá")
                .build();

        Orders order2 = Orders.builder()
                .orderId(UUID.randomUUID().getMostSignificantBits())
                .requestDate(Date.from(Instant.now()))
                .clientName("Luiz Cantrela")
                .deliveryAddress("São Carlos")
                .build();

        List<Orders> listOrders = new ArrayList<>();
        listOrders.add(order1);
        listOrders.add(order2);

        return listOrders;
    }
}
