package com.gzl0ng.qqzone.dao.impl;

import com.gzl0ng.myssm.basedao.BaseDAO;
import com.gzl0ng.qqzone.dao.HostReplyDAO;
import com.gzl0ng.qqzone.pojo.HostReply;

public class HostReplyDAOImpl extends BaseDAO<HostReply> implements HostReplyDAO {
    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return load("select * from t_host_reply where reply = ? " , replyId);
    }

    @Override
    public void delHostReply(Integer id) {
        super.executeUpdate("delete from t_host_reply where id = ? " , id) ;
    }
}
