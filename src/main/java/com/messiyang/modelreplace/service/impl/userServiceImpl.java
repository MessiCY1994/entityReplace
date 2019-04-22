package com.messiyang.modelreplace.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.messiyang.modelreplace.dao.UserMapper;
import com.messiyang.modelreplace.model.NewUser;
import com.messiyang.modelreplace.model.User;
import com.messiyang.modelreplace.service.userService;
import com.messiyang.modelreplace.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.net.UnknownServiceException;
import java.util.ArrayList;
import java.util.List;

@Service
public class userServiceImpl  implements userService {

    @Autowired
    private UserMapper userMapper;
    private int count = 1;

    @Override
    public List<NewUser> exportUserInfo(String date) {
        List<User> users = getUser(date);
        List<NewUser> userList = new ArrayList<>();
        for(User user :users){
            try {
                List<EntityCommentModel> entityCommentModels = EntityAnnotationUtil.getAnnotationValue(user);
                NewUser userInfo = new NewUser();
                userInfo = (NewUser) EntityAnnotationUtil.setObjectValue(userInfo,entityCommentModels);
                String jsonText = JSON.toJSONString(userInfo, SerializerFeature.WriteMapNullValue);
                String FilePath = FilenameGenerator.getJsonFilename(userInfo.getRealName(),"XXX", DateUtil.StringConverDate("2018-2-2")); //文件名输出
                WriteFile.writeJson(FilePath,jsonText);
                System.out.println("正在导出第"+count+"条数据："+jsonText);
                count++;
               userList.add(userInfo);
            }catch (Exception e){
                System.out.println("正在导出第"+count+"条数据："+e.toString());
                count++;
            }

        }
        return userList;
    }

    public List<User> getUser(String date){
        List<User> user = userMapper.selectAll();
        return user;
    }
}
