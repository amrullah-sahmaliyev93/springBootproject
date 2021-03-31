package az.course.service.impl;

import az.course.dao.PaymentDao;
import az.course.model.Payment;
import az.course.service.PaymentService;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private PaymentDao paymentDao;
    public PaymentServiceImpl(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    @Override
    public List<Payment> getPaymentList() throws Exception {
        return paymentDao.getPaymentList();
    }
}
