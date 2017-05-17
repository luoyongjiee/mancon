package com.cp.market.mc.dao.iface;

import com.cp.market.mc.dao.model.UserInfoModel;
import com.cp.market.mc.dao.model.UserInfoModel;
import com.cp.market.mc.dao.model.UserInfoModelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInfoModelMapper {
    int countByExample(UserInfoModelExample example);

    int deleteByExample(UserInfoModelExample example);

    int insert(UserInfoModel record);

    int insertSelective(UserInfoModel record);

    List<UserInfoModel> selectByExample(UserInfoModelExample example);

    int updateByExampleSelective(@Param("record") UserInfoModel record, @Param("example") UserInfoModelExample example);

    int updateByExample(@Param("record") UserInfoModel record, @Param("example") UserInfoModelExample example);
}