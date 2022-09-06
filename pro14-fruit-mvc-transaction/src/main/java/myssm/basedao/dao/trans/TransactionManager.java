package myssm.basedao.dao.trans;

import myssm.basedao.dao.ConnUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Author: guozhenglong
 * Date:2022/9/6 15:54
 */
public class TransactionManager {


    //开启事务
    public static void beginTrans() throws SQLException {
        ConnUtil.getConn().setAutoCommit(false);
    };

    //提交事务
    public static void commit() throws SQLException {
        Connection conn = ConnUtil.getConn();
        conn.commit();
        ConnUtil.closeConn();
    }

    //回滚事务
    public static void rollback() throws SQLException {
        Connection conn =ConnUtil.getConn();
        conn.rollback();
        ConnUtil.closeConn();
    };
}
