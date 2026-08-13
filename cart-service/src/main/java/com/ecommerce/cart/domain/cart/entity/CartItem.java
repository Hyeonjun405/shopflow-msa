package com.ecommerce.cart.domain.cart.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    private long productId;

    @Column(nullable = false)
    private int quantity;

    @Builder
    private CartItem(Cart cart, Long productId, int quantity) {
        this.cart = cart;
        this.productId = productId;
        this.quantity = quantity;
    }

    public static CartItem create(Cart cart, Long productId, int quantity) {
        return CartItem.builder()
                .cart(cart)
                .productId(productId)
                .quantity(quantity)
                .build();
    }

    public void updateQuantity(int quantity) {
        this.quantity = quantity;
    }
}