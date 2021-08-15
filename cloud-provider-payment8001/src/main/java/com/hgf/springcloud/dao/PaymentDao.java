package com.hgf.springcloud.dao;

import com.hgf.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author shkstart
 * @creat 2021-08-04-15:07
 */
@Mapper
public interface PaymentDao {
    public int creat(Payment payment);
    public Payment getpaymentByid(@Param("id")Long id);
}
