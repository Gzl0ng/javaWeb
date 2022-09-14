package com.gzl0ng.qqzone.service;

import com.gzl0ng.qqzone.pojo.Topic;
import com.gzl0ng.qqzone.pojo.UserBasic;
import com.gzl0ng.qqzone.pojo.Topic;
import com.gzl0ng.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicService {
    //查询特定用户的日志列表
    List<Topic> getTopicList(UserBasic userBasic) ;
}
