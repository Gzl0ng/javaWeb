package myssm.basedao.dao.impl;

import com.gzl0ng.pojo.Fruits;

import myssm.basedao.dao.BaseDAO;
import myssm.basedao.dao.FruitsDAO;

import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruits> implements FruitsDAO {
    @Override
    public List<Fruits> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }

    @Override
    public Fruits getFruitByFid(Integer fid) {
        return super.load("select * from t_fruit where fid = ? " , fid);
    }

    @Override
    public void updateFruit(Fruits fruit) {
        String sql = "update t_fruit set fname = ? , price = ? , fcount = ? , remark = ? where fid = ? " ;
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark(),fruit.getFid());
    }

    @Override
    public void delFruit(Integer fid) {
        super.executeUpdate("delete from t_fruit where fid = ? " , fid) ;
    }

    @Override
    public void addFruit(Fruits fruit) {
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        super.executeUpdate(sql,fruit.getFname(),fruit.getPrice(),fruit.getFcount(),fruit.getRemark());
    }
}
