package com.ecommerce.Payment.domain.payment.service;


import com.ecommerce.Payment.domain.payment.dto.PaymentGatewayResult;
import com.ecommerce.Payment.domain.payment.dto.PaymentInfo;
import com.ecommerce.Payment.domain.payment.dto.command.PayCommand;
import com.ecommerce.Payment.domain.payment.entity.Payment;
import com.ecommerce.Payment.domain.payment.gateway.PaymentGateway;
import com.ecommerce.Payment.domain.payment.gateway.PaymentGatewayRouter;
import com.ecommerce.Payment.domain.payment.repository.PaymentRepository;

import com.ecommerce.Payment.global.exception.DomainException;
import com.ecommerce.Payment.global.exception.DomainExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentGatewayRouter paymentGatewayRouter;

    @Transactional
    public void pay(Long userId, PayCommand command) {

        validatePayable(command.getOrderId(), userId);

        PaymentGateway gateway = paymentGatewayRouter.getGateway(command.getPaymentType());

        //TODO : 최종 결제금액 확인필요
        int totalPrice = 0;
        PaymentGatewayResult result = gateway.pay(totalPrice);

        if (!result.isSuccess()) {
            throw new DomainException(DomainExceptionCode.PAYMENT_FAILED);
        }

        Payment payment = Payment.create(command.getOrderId(), userId, totalPrice, command.getPaymentType(), result.getTransactionId());
        paymentRepository.save(payment);

        // TODO : 페이먼트 성공해서 return 하면 Order서비스에서 설정
        //order.updateStatus(OrderStatus.PAID);
    }

    @Transactional
    public void cancel(Long userId, Long paymentId) {
        Payment payment = findPaymentById(paymentId);

        if (!payment.getUserId().equals(userId)) {
            throw new DomainException(DomainExceptionCode.UNAUTHORIZED);
        }

        PaymentGateway gateway = paymentGatewayRouter.getGateway(payment.getPaymentType());

        try {
            gateway.cancel(payment.getTransactionId());
        } catch (Exception e) {
            throw new DomainException(DomainExceptionCode.PAYMENT_CANCEL_FAILED);
        }

        payment.cancel();
        // TODO: 페이먼트 실패 리턴하면 오더 서비스에서 처리
        //payment.getOrder().updateStatus(OrderStatus.CANCELLED);
    }

    @Transactional(readOnly = true)
    public List<PaymentInfo> getMyPayments(Long userId) {

        return paymentRepository.findByUserId(userId).stream()
                .map(PaymentInfo::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public PaymentInfo getPayment(Long userId, Long paymentId) {
        Payment payment = findPaymentById(paymentId);
        if (!payment.getUserId().equals(userId)) {
            throw new DomainException(DomainExceptionCode.UNAUTHORIZED);
        }
        return PaymentInfo.from(payment);
    }




    private Payment findPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId)
                .orElseThrow(() -> new DomainException(DomainExceptionCode.NOT_FOUND_PAYMENT));
    }

    //TODO : 검증 작업 필요
    private void validatePayable(Long orderId, Long userId) {
//        if (!order.getUser().getId().equals(userId)) {
//            throw new DomainException(DomainExceptionCode.UNAUTHORIZED);
//        }
//        if (order.getStatus() != OrderStatus.PENDING) {
//            throw new DomainException(DomainExceptionCode.CANNOT_PAY_ORDER);
//        }
        if (paymentRepository.findByOrderId(orderId).isPresent()) {
            throw new DomainException(DomainExceptionCode.ALREADY_PAID);
        }
    }
}