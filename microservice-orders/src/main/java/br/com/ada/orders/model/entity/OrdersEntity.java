package br.com.ada.orders.model.entity;

import br.com.ada.orders.model.dto.OrdersDTORequest;
import br.com.ada.orders.model.dto.OrdersDTOResponse;
import br.com.ada.orders.model.dto.ProductsRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Orders")
public class OrdersEntity {

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date requestDate;

    private String clientName;

    private String deliveryAddress;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

//    private List<ProductsRequestDTO> listProducts;
}
