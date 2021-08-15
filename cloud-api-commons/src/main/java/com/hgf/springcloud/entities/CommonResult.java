package com.hgf.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shkstart
 * @creat 2021-08-04-14:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult <T>{
    private Integer code;
    private String message;
    private  T      data;
    public CommonResult(Integer code,String message){
        this(code,message,null);
    }
}
