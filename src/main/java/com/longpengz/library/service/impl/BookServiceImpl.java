package com.longpengz.library.service.impl;

import com.longpengz.dataprocessing.bean.pojo.PageResult;
import com.longpengz.dataprocessing.bean.pojo.SeachForm;
import com.longpengz.dataprocessing.bean.pojo.SpecificationUtil;
import com.longpengz.library.datasource.repository.BookRepository;
import com.longpengz.library.modle.entity.Book;
import com.longpengz.library.service.BookService;
import com.longpengz.restful.bean.APIError;
import com.longpengz.utils.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public PageResult<Book> getBooks(SeachForm seachForm) {
        Page<Book> books = bookRepository.findAll(SpecificationUtil.<Book>filter(seachForm)
                .eq("presenceStatus", 1).build(), seachForm.pageRequest());
        return PageResult.jpaOf(books);
    }

    @Override
    public Book getBook(Integer bookId) {
        Book book = bookRepository.findById(bookId).orElse(null);
        if(ObjectUtils.isEmpty(book)){
            APIError.NOT_FOUND();
        }
        return book;
    }

    @Override
    public Book insertBook(Book book) {
        if(!ObjectUtils.isEmpty(book.getId()) && !book.getId().equals(0)){
            Book bookOld = bookRepository.findById(book.getId()).orElse(null);
            if(ObjectUtils.isEmpty(bookOld)){
                APIError.NOT_FOUND();
            }
            book.setPresenceStatus(bookOld.getPresenceStatus());
        }
        return bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteBooks(String ids) {
        bookRepository.softDelete(StringUtil.toIntArray(ids));

    }

}
