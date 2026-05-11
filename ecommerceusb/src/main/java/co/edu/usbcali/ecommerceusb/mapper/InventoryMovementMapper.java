package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.CreateInventoryMovementRequest;
import co.edu.usbcali.ecommerceusb.dto.InventoryMovementResponse;
import co.edu.usbcali.ecommerceusb.model.InventoryMovement;
import co.edu.usbcali.ecommerceusb.model.Order;
import co.edu.usbcali.ecommerceusb.model.Product;

import java.time.OffsetDateTime;
import java.util.List;

public class InventoryMovementMapper {

    public static InventoryMovementResponse modelToInventoryMovementResponse(InventoryMovement inventoryMovement) {
        return InventoryMovementResponse.builder()
                .id(inventoryMovement.getId())
                .productId(inventoryMovement.getProduct() != null ? inventoryMovement.getProduct().getId() : null)
                .orderId(inventoryMovement.getOrder() != null ? inventoryMovement.getOrder().getId() : null)
                .type(inventoryMovement.getType() != null ? inventoryMovement.getType().name() : null)
                .qty(inventoryMovement.getQty())
                .createdAt(inventoryMovement.getCreatedAt())
                .build();
    }

    public static List<InventoryMovementResponse> modelToInventoryMovementResponseList(List<InventoryMovement> list) {
        return list.stream()
                .map(InventoryMovementMapper::modelToInventoryMovementResponse)
                .toList();
    }

    public static InventoryMovement createInventoryMovementRequestToInventoryMovement(
            CreateInventoryMovementRequest request,
            Product product,
            Order order
    ) {
        return InventoryMovement.builder()
                .product(product)
                .order(order)
                .type(InventoryMovement.MovementType.valueOf(request.getType()))
                .qty(request.getQty())
                .createdAt(OffsetDateTime.now())
                .build();
    }
}