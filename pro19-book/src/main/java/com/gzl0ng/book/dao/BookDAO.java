package com.gzl0ng.book.dao;

import com.gzl0ng.book.pojo.Book;

import java.util.List;

/**
 * Author: guozhenglong
 * Date:2022/9/19 14:42
 */
public interface BookDAO {
    List<Book> getBookList();

//    List<Book> getBookList(Integer minPrice,Integer maxPrice,Integer pageNo);
}
