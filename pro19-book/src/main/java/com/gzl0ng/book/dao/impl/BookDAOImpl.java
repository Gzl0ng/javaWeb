package com.gzl0ng.book.dao.impl;

import com.gzl0ng.book.dao.BookDAO;
import com.gzl0ng.book.pojo.Book;
import com.gzl0ng.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * Author: guozhenglong
 * Date:2022/9/19 14:43
 */
public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {
    @Override
    public List<Book> getBookList() {
        return executeQuery("select * from t_book");
    }

    @Override
    public Book getBook(Integer id) {
        return load("select * from t_book where id = ?",id);
    }
}
