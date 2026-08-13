package com.ecommerce.order.domain.order.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_items")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

    @Builder
    private OrderItem(Order order, Long productId, int quantity, int price) {
        this.order = order;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public static OrderItem create(Order order, Long productId, int quantity, int price) {
        return OrderItem.builder()
                .order(order)
                .productId(productId)
                .quantity(quantity)
                .price(price)
                .build();
    }
}