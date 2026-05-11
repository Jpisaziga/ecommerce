package co.edu.usbcali.ecommerceusb.mapper;

import co.edu.usbcali.ecommerceusb.dto.CartResponse;
import co.edu.usbcali.ecommerceusb.dto.CreateCartRequest;
import co.edu.usbcali.ecommerceusb.model.Cart;
import co.edu.usbcali.ecommerceusb.model.User;

import java.time.OffsetDateTime;
import java.util.List;

public class CartMapper {

    public static CartResponse modelToCartResponse(Cart cart) {
        return CartResponse.builder()
                .id(cart.getId())
                .userId(cart.getUser() != null ? cart.getUser().getId() : null)
                .status(cart.getStatus() != null ? cart.getStatus().name() : null)
                .createdAt(cart.getCreatedAt())
                .updatedAt(cart.getUpdatedAt())
                .build();
    }

    public static List<CartResponse> modelToCartResponseList(List<Cart> list) {
        return list.stream()
                .map(CartMapper::modelToCartResponse)
                .toList();
    }

    public static Cart createCartRequestToCart(
            CreateCartRequest request,
            User user
    ) {
        return Cart.builder()
                .user(user)
                .status(Cart.CartStatus.valueOf(request.getStatus()))
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .build();
    }
}