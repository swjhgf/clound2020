package com.hgf.springcloud.service.impl;

import com.hgf.springcloud.dao.PaymentDao;
import com.hgf.springcloud.entities.Payment;
import com.hgf.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author shkstart
 * @creat 2021-08-04-15:59
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;
    @Override
    public int creat(Payment payment) {
        return paymentDao.creat(payment);
    }

    @Override
    public Payment getpaymentByid(Long id) {
        return paymentDao.getpaymentByid(id);
    }
}
