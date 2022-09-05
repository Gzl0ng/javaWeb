package com.gzl0ng.service;

import com.gzl0ng.pojo.Fruits;

import java.util.List;

public interface FruitService {
    //获取指定页面的库存列表信息
    List<Fruits> getFruitList(String keyword , Integer pageNo);
    //添加库存记录信息
    void addFruit(Fruits fruit);
    //根据id查看指定库存记录
    Fruits getFruitByFid(Integer fid);
    //删除特定库存记录
    void delFruit(Integer fid);
    //获取总页数
    Integer getPageCount(String keyword);
    //修改特定库存记录
    void updateFruit(Fruits fruit);
}
