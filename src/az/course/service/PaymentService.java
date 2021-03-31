package az.course.service;

import az.course.model.Payment;

import java.util.List;

public interface PaymentService {
    List<Payment> getPaymentList ()throws  Exception;

}
