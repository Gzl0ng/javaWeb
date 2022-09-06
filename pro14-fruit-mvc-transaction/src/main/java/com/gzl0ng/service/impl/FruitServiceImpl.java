package com.gzl0ng.service.impl;



import com.gzl0ng.service.FruitService;
import com.gzl0ng.pojo.Fruits;
import myssm.basedao.dao.ConnUtil;
import myssm.basedao.dao.FruitsDAO;

import java.util.List;

public class FruitServiceImpl implements FruitService {

    private FruitsDAO fruitDAO = null;

    @Override
    public List<Fruits> getFruitList(String keyword, Integer pageNo) {
        System.out.println("getFruitList = "+ConnUtil.getConn());
        return fruitDAO.getFruitList(keyword,pageNo);
    }

    @Override
    public void addFruit(Fruits fruit) {

        fruitDAO.addFruit(fruit);
        Fruits fruit2 = fruitDAO.getFruitByFid(2);
        fruit2.setFcount(99);
        fruitDAO.updateFruit(fruit2);
    }

    @Override
    public Fruits getFruitByFid(Integer fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public void delFruit(Integer fid) {
        fruitDAO.delFruit(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        System.out.println("getPageCount = " + ConnUtil.getConn());
        int count = fruitDAO.getFruitCount(keyword);
        int pageCount = (count+5-1)/5 ;
        return pageCount;
    }

    @Override
    public void updateFruit(Fruits fruit) {
        fruitDAO.updateFruit(fruit);
    }
}
