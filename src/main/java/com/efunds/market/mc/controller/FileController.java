package com.efunds.market.mc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by luoqi on 2016/4/10.
 */
@Controller()
public class FileController {

    @RequestMapping("file/upload")
    @ResponseBody
    public Object upload(String msg,@RequestParam(value = "excel", required = false) MultipartFile excel){
        try {
            Thread.sleep(6000);
        }catch (Exception e){

        }
        return msg;
    }

}
