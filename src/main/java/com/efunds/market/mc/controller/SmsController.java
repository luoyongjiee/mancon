package com.efunds.market.mc.controller;

import com.efunds.market.mc.infrastructure.error.exception.LogicException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * created by root 2016/3/26
 * 功能：
 */
@Controller
@RequestMapping(value = "/sms")
public class SmsController {

    @RequestMapping(value = "/getSms")
    @ResponseBody
    public Map<String,Object> getSms(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("1","Jack");
        map.put("2","Jack");
        map.put("3","Jack");


        map.put("3","Jack");
        return map;
    }


    @RequestMapping(value = "/index")
    public Object index(){


        return "index";
    }


}
