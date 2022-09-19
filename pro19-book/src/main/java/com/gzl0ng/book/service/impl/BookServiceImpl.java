package com.gzl0ng.book.service.impl;

import com.gzl0ng.book.dao.BookDAO;
import com.gzl0ng.book.pojo.Book;
import com.gzl0ng.book.service.BookService;

import java.util.List;

/**
 * Author: guozhenglong
 * Date:2022/9/19 14:46
 */
public class BookServiceImpl implements BookService {
    private BookDAO bookDAO;

    @Override
    public List<Book> getBookList() {
        return bookDAO.getBookList();
    }
}
