package com.lvg.bs.dao;
import com.lvg.bs.dto.Book;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDAO extends JpaRepository<Book,Integer>
{
    public Optional<Book> findByBookTitle(String bookTitle);
}
