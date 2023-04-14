package br.com.ada.orders.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
public class Orders {

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date requestDate;

    private String clientName;

    private String deliveryAddress;

    private Long orderId;
}
