package com.lvg.bs.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lvg.bs.dto.Book;
import com.lvg.bs.service.BookService;
@EnableEurekaClient
@RestController
public class BookController 
{
    @Autowired BookService bookService;
    public void setBookService(BookService bookService)
    {
    	this.bookService=bookService;
    }
    
    @GetMapping(value="/getBookByTitle/{bookTitle}",produces="application/json")
    public ResponseEntity<Optional<Book>> getBookByTitle(@PathVariable String bookTitle)
    {
    	Optional<Book> book = bookService.getBookByBookTitle(bookTitle);
    	if(book.isPresent()) 
    	     return  new ResponseEntity<Optional<Book>>(book,HttpStatus.OK);
    	return new ResponseEntity<Optional<Book>>(book,HttpStatus.NOT_FOUND);
    }
    
    @PostMapping(value="/addBook",consumes="application/json")
    public ResponseEntity<String> addBookDetails(@RequestBody Book book)
    {   
    	try
    	{
    	    bookService.insertBook(book);
    		return new ResponseEntity<String>("Book Details Added",HttpStatus.OK);
    	}
    	catch(Exception ex)
    	{
    	    return new ResponseEntity<String>("Book Details Cannot be Added",HttpStatus.BAD_REQUEST);
    	}    
    }
    
    @DeleteMapping(value="/deleteBook/{bookId}")
    public ResponseEntity<String> deleteBookDetails(@PathVariable int bookId)
    {   
    	try
    	{
    	    bookService.deleteBook(bookId);
    		return new ResponseEntity<String>("Book Details Deleted",HttpStatus.OK);
    	}
    	catch(Exception ex)
    	{
    	    return new ResponseEntity<String>("Book Details Cannot be Deleted",HttpStatus.NOT_FOUND);
    	}    
    }
}
