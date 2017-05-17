package com.cp.market.mc.controller;

import com.cp.market.mc.common.util.ExcelUtil;
import com.cp.market.mc.common.util.ExcelUtil;
import com.cp.market.mc.infrastructure.error.exception.LogicException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @RequestMapping(value = "/upload")
    public Object upload(@RequestParam MultipartFile[] file){

        if(file != null && file.length > 0){
            POIFSFileSystem fs= null;


            try {

                fs = new POIFSFileSystem(file[0].getInputStream());
                HSSFWorkbook workbook = new HSSFWorkbook(fs);
                HSSFSheet sheet = workbook.getSheetAt(0);
                int rowNum = sheet.getPhysicalNumberOfRows();
                for(int i = 0; i < rowNum; i++){

                    System.out.println(ExcelUtil.getText(sheet,rowNum,0)+"="+ExcelUtil.getText(sheet,rowNum,1));
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }



    @RequestMapping(value = "/index")
    public Object index(){


        return "index";
    }


}
