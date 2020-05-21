package com.lvg.bs.service;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lvg.bs.dao.BookDAO;
import com.lvg.bs.dto.Book;
@Service
public class BookService 
{
    @Autowired BookDAO bookDao;
    public void setBookDao(BookDAO bookDao) { this.bookDao=bookDao;}
    
    @Transactional(readOnly=true)
    public Optional<Book> getBookByBookTitle(String bookTitle)
    {
    	return bookDao.findByBookTitle(bookTitle);
    }
    
    @Transactional
    public Book insertBook(Book book)
    {
        return bookDao.save(book);	
    }
    
    @Transactional
    public void deleteBook(int bookId)
    {
    	bookDao.deleteById(bookId);
    }
}
