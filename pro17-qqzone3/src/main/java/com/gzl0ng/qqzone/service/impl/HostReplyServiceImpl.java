package com.gzl0ng.qqzone.service.impl;

import com.gzl0ng.qqzone.dao.HostReplyDAO;
import com.gzl0ng.qqzone.pojo.HostReply;
import com.gzl0ng.qqzone.service.HostReplyService;

public class HostReplyServiceImpl implements HostReplyService {

    private HostReplyDAO hostReplyDAO ;

    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return hostReplyDAO.getHostReplyByReplyId(replyId);
    }

    @Override
    public void delHostReply(Integer id) {
        hostReplyDAO.delHostReply(id);
    }
}
