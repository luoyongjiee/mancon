package com.efunds.market.mc.controller;


import com.efunds.market.mc.common.util.JsonUtil;
import com.efunds.market.mc.dao.iface.UserInfoModelMapper;
import com.efunds.market.mc.dao.model.UserInfoModel;
import com.efunds.market.mc.dao.model.UserInfoModelExample;
import com.efunds.market.mc.vo.UserInfo;
import com.efunds.market.mc.vo.UserInfoCondition;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luoqi on 2016/4/9.
 */
@Controller
public class UserController {

    @Autowired
    private UserInfoModelMapper userInfoModelMapper;

    @RequestMapping("user/getUserInfo")
    @ResponseBody
    public Object getUserInfo(UserInfoCondition userInfoCondition){


        PageHelper.offsetPage(Integer.parseInt(userInfoCondition.getiDisplayStart()),Integer.parseInt(userInfoCondition.getiDisplayLength()),true);

        UserInfoModelExample example = new UserInfoModelExample();
        UserInfoModelExample.Criteria criteria = example.createCriteria();

        if("0".equals(userInfoCondition.getiSortCol_0())){
            example.setOrderByClause(userInfoCondition.getmDataProp_0()+"  "+userInfoCondition.getsSortDir_0());
        }


        List<UserInfoModel> userInfoModels = userInfoModelMapper.selectByExample(example);
        List<UserInfo> userInfos = new ArrayList<UserInfo>();


        for(UserInfoModel model:userInfoModels){
            UserInfo userInfo = new UserInfo();
            userInfo.setUserName(model.getUsername());
            userInfo.setFullName(model.getFullname());
            userInfo.setPoints(model.getPoints());
            userInfo.setNotes(model.getNotes());
            userInfo.setOperation("<a href>删除<a>");
            userInfos.add(userInfo);

        }

        int count = userInfoModelMapper.countByExample(null);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("sEcho",userInfoCondition.getsEcho());
        map.put("iTotalRecords",count);
        map.put("iTotalDisplayRecords",count);
        map.put("aaData",userInfos);

        return map;
    }
}
