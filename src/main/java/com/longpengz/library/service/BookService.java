package com.longpengz.library.service;

import com.longpengz.dataprocessing.bean.pojo.PageResult;
import com.longpengz.dataprocessing.bean.pojo.SeachForm;
import com.longpengz.library.modle.entity.Book;

public interface BookService {

    /**
     * 获取图书列表
     * @author longpengZ
     */
    PageResult<Book> getBooks(SeachForm seachForm);

    /**
     * 获取图书详情
     * @param bookId 图书id
     * @author longpengZ
     */
    Book getBook(Integer bookId);

    /**
     * 添加编辑图书
     * @author longpengZ
     */
    Book insertBook(Book book);

    /**
     * 删除图书
     * @author longpengZ
     */
    void deleteBooks(String ids);
}
