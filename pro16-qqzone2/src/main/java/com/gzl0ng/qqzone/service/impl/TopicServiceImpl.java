package com.gzl0ng.qqzone.service.impl;

import com.gzl0ng.qqzone.dao.TopicDAO;
import com.gzl0ng.qqzone.pojo.Topic;
import com.gzl0ng.qqzone.pojo.UserBasic;
import com.gzl0ng.qqzone.service.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO = null ;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }
}
