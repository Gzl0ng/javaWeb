package myssm.basedao.dao;

import com.gzl0ng.pojo.Fruits;


import java.util.List;

public interface FruitsDAO {
    //获取所有的库存列表信息
    List<Fruits> getFruitList(Integer pageNo);

    //根据fid获取特定的水果库存信息
    Fruits getFruitByFid(Integer fid);

    //修改指定的库存记录
    void updateFruit(Fruits Fruits);

    //根据fid删除指定的库存记录
    void delFruit(Integer fid);

    //添加新库存记录
    void addFruit(Fruits Fruits);

    //查询库存总记录条数
    int getFruitCount();
}
