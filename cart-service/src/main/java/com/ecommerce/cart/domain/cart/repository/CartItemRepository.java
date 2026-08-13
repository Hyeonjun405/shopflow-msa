package com.ecommerce.cart.domain.cart.repository;


import com.ecommerce.cart.domain.cart.entity.Cart;
import com.ecommerce.cart.domain.cart.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartAndProductId(Cart cart, Long productId);
}
