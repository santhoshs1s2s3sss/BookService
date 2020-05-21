package com.lvg.bs.service;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.lvg.bs.dto.Book;
@SpringBootTest
public class TestBookService 
{
      @Autowired
      BookService bookService;
      
      
      @Test
      public void testGetBookByTitle_Positive() throws Exception
      {
    	    Optional<Book> book = bookService.getBookByBookTitle("xyz");
    	    Assertions.assertEquals(true,book.isPresent());
      }
      
      @Test
      public void testGetBookByTitle_Negative() throws Exception
      {
    	    Optional<Book> book = bookService.getBookByBookTitle("xyz");
    	    Assertions.assertEquals(false,book.isPresent());
      }
      
      @Test
      public void testInsertBook_Positive() throws Exception
      {
    	  Book book = new Book(111,"REactJS","David",786);
    	  Book b = bookService.insertBook(book);
    	  Assertions.assertEquals(b.getBookTitle(), book.getBookTitle());
      }
}
