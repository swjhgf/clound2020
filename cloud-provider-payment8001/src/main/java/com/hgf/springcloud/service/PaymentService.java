package com.hgf.springcloud.service;

import com.hgf.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author shkstart
 * @creat 2021-08-04-15:57
 */
public interface PaymentService {
    public int creat(Payment payment);
    public Payment getpaymentByid(@Param("id")Long id);
}
