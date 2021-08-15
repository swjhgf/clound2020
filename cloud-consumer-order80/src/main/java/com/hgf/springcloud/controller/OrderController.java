package com.hgf.springcloud.controller;

import com.hgf.springcloud.entities.CommonResult;
import com.hgf.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author shkstart
 * @creat 2021-08-05-13:31
 */
@RestController
@Slf4j
public class OrderController {
//    public static final String PAYMENT_URL="http://localhost:8001";
      public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";
    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/consumer/payment/creat")
    public CommonResult<Payment> creat(Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/creat",payment,CommonResult.class);
    }
    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
    @GetMapping("/consumer/payment/createntity")
    public CommonResult<Payment> createntity(Payment payment){
        ResponseEntity<CommonResult> postForEntity = restTemplate.postForEntity(PAYMENT_URL + "/payment/creat", payment, CommonResult.class);
        if (postForEntity.getStatusCode().is2xxSuccessful()){
            log.info("postForEntity.getStatusCode()",postForEntity.getStatusCode());
            return postForEntity.getBody();
        }else {
            return new CommonResult<>(444,"操作失败");
        }
    }
    @GetMapping("/consumer/payment/getentity/{id}")
    public CommonResult<Payment> getentity(@PathVariable("id") Long id){
        ResponseEntity<CommonResult> postForEntity = restTemplate.getForEntity(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
        if (postForEntity.getStatusCode().is2xxSuccessful()){
            log.info("postForEntity.getStatusCode()",postForEntity.getStatusCode());
            return postForEntity.getBody();
        }else {
            return new CommonResult<>(444,"操作失败");
        }
    }
}
