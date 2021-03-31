package az.course.dao;

import az.course.model.Payment;

import java.util.List;

public interface PaymentDao {
    List<Payment>getPaymentList ()throws  Exception;
}
