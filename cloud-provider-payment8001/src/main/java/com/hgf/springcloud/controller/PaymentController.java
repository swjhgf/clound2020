package com.hgf.springcloud.controller;

import com.hgf.springcloud.entities.CommonResult;
import com.hgf.springcloud.entities.Payment;
import com.hgf.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author shkstart
 * @creat 2021-08-04-16:49
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;
    @PostMapping("/payment/creat")
    public CommonResult creat(@RequestBody Payment payment){
        int creat = paymentService.creat(payment);
        log.info("插入结果 嘻嘻",creat);
        if (creat>0){
            return new CommonResult(200,"插入数据库成功,serverPot"+serverPort,creat);
        }else {
            return new CommonResult(400,"插入数据库失败",null);
        }
    }
    @GetMapping ("/payment/get/{id}")
    public CommonResult getpaymentByid(@PathVariable("id") Long id){
        Payment payment = paymentService.getpaymentByid(id);
        log.info("查询结果",payment);
//        log.info("查询结果"+payment+"哈哈哈");

//        log.info("查询结果"+payment+"哈哈哈");
//        log.info("查询结果"+payment+"哈哈哈对呀");

        if (payment !=null){
            return new CommonResult(200,"查询成功,serverPot"+serverPort,payment);
        }else {
            return new CommonResult(400,"没有对应记录，查询id，"+id,null);
        }
    }
    @GetMapping("/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("*****elment"+service);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeOut()
    {
//        System.out.println("*****paymentFeignTimeOut from port: "+serverPort);
        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(3); } catch (InterruptedException e) { e.printStackTrace(); }
        return serverPort;
    }


}
