package com.gzl0ng.book.controller;

import com.gzl0ng.book.pojo.Book;
import com.gzl0ng.book.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Author: guozhenglong
 * Date:2022/9/19 14:52
 */
public class BookController {
    private BookService bookService;

    public String index(HttpSession session){
        List<Book> bookList = bookService.getBookList();
        session.setAttribute("bookList",bookList);
        return "index";
    }
}
