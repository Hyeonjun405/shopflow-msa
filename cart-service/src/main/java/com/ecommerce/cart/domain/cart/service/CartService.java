package com.ecommerce.cart.domain.cart.service;

import com.ecommerce.cart.domain.cart.dto.CartInfo;
import com.ecommerce.cart.domain.cart.dto.command.AddCartItemCommand;
import com.ecommerce.cart.domain.cart.dto.command.UpdateCartItemCommand;
import com.ecommerce.cart.domain.cart.entity.Cart;
import com.ecommerce.cart.domain.cart.entity.CartItem;
import com.ecommerce.cart.domain.cart.repository.CartItemRepository;
import com.ecommerce.cart.domain.cart.repository.CartRepository;
import com.ecommerce.cart.global.exception.DomainException;
import com.ecommerce.cart.global.exception.DomainExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Transactional
    public void addItem(Long userId, AddCartItemCommand command) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(Cart.create(userId)));


        Long productId = command.getProductId();

        cartItemRepository.findByCartAndProductId(cart, productId)
                .ifPresentOrElse(
                        item -> item.updateQuantity(item.getQuantity() + command.getQuantity()),
                        () -> cartItemRepository.save(CartItem.create(cart, productId, command.getQuantity()))
                );
    }

    @Transactional(readOnly = true)
    public CartInfo getCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> cartRepository.save(Cart.create(userId)));
        return CartInfo.from(cart);
    }

    @Transactional
    public void updateItemQuantity(Long userId, Long cartItemId, UpdateCartItemCommand command) {
        Cart cart = getCartByUser(userId);
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new DomainException(DomainExceptionCode.NOT_FOUND_CART_ITEM));
        validateCartOwner(item, cart);
        item.updateQuantity(command.getQuantity());
    }

    @Transactional
    public void removeItem(Long userId, Long cartItemId) {
        Cart cart = getCartByUser(userId);
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new DomainException(DomainExceptionCode.NOT_FOUND_CART_ITEM));
        validateCartOwner(item, cart);
        cartItemRepository.delete(item);
    }

    @Transactional
    public void clearCart(Long userId) {
        cartRepository.findByUserId(userId).ifPresent(Cart::clear);
    }

    private Cart getCartByUser(Long userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new DomainException(DomainExceptionCode.NOT_FOUND_CART));
    }

    private void validateCartOwner(CartItem item, Cart cart) {
        if (!item.getCart().getId().equals(cart.getId())) {
            throw new DomainException(DomainExceptionCode.UNAUTHORIZED);
        }
    }
}