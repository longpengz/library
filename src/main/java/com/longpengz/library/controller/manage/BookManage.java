package com.longpengz.library.controller.manage;

import com.longpengz.dataprocessing.bean.pojo.PageResult;
import com.longpengz.dataprocessing.bean.pojo.SeachForm;
import com.longpengz.library.modle.constants.RestPrefixConstant;
import com.longpengz.library.modle.entity.Book;
import com.longpengz.library.service.BookService;
import com.longpengz.restful.bean.API;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"管理后台-图书管理"})
@RestController
@RequestMapping(RestPrefixConstant.MANAGE_BOOK_MANAGEMENT_PREFIX)
@RequiredArgsConstructor
public class BookManage {

    private final BookService bookService;

    @GetMapping("books")
    @ApiOperation("获取图书列表")
    public API<PageResult<Book>> getBooks(SeachForm seachForm){
        return API.ok(bookService.getBooks(seachForm));
    }

    @GetMapping("book")
    @ApiOperation("获取图书详情")
    public API<Book> getBook(@RequestParam Integer bookId){
        return API.ok(bookService.getBook(bookId));
    }

    @PostMapping("book")
    @ApiOperation("添加编辑图书")
    public API<Book> insertBook(@RequestBody Book book){
        return API.ok(bookService.insertBook(book));
    }

    @DeleteMapping
    @ApiOperation("删除图书")
    public API<String> deleteBooks(@RequestParam String ids){
        bookService.deleteBooks(ids);
        return API.ok("成功");
    }
}
