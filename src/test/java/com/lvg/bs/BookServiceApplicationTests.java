package com.lvg.bs;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.lvg.bs.dto.Book;
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class BookServiceApplicationTests 
{
	@Autowired
	TestRestTemplate testRestTemplate;
	public void setTestRestTemplate(TestRestTemplate testRestTemplate)
	{
		this.testRestTemplate=testRestTemplate;
	}
	
	@LocalServerPort
	int localServerPort;
	
	@Test
	public void testGetBookByTitle_Positive() throws Exception
	{
		 String url="http://localhost:"+localServerPort+"getBookByTitle/C++Prog";
		 ResponseEntity<Book> book = testRestTemplate.getForEntity(url,Book.class);
		 Assertions.assertEquals(200, book.getStatusCodeValue());
	}
	
	@Test
	public void testGetBookByTitle_Negative() throws Exception
	{
		 String url="http://localhost:"+localServerPort+"getBookByTitle/xyz";
		 ResponseEntity<Book> book = testRestTemplate.getForEntity(url,Book.class);
		 Assertions.assertEquals(404, book.getStatusCodeValue());
	}
	
	@Test
	public void testAddBookDetails_Positive() throws Exception
	{
		 String url="http://localhost:"+localServerPort+"addBook";
		 Book book = new Book(111,"Angular8","xyz",756);
		 ResponseEntity<String> response = testRestTemplate.postForEntity(url,book,String.class);
		 Assertions.assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	public void testAddBookDetails_Negative() throws Exception
	{
		 String url="http://localhost:"+localServerPort+"addBook";
		 Book book = null;
		 ResponseEntity<String> response = testRestTemplate.postForEntity(url,book,String.class);
		 Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCodeValue());
	}
	
	@Test
	public void testDeleteBookDetails_Positive() throws Exception
	{
		 String url="http://localhost:"+localServerPort+"deleteBook/103";
		 HttpEntity<Integer> ent = new HttpEntity<>(103);
		  ResponseEntity<String> response = testRestTemplate.exchange(url,HttpMethod.DELETE,null,String.class);
		 Assertions.assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	public void testDeleteBookDetails_Negative() throws Exception
	{
		 String url="http://localhost:"+localServerPort+"deleteBook/103";
		 HttpEntity<Integer> ent = new HttpEntity<>(103);
		  ResponseEntity<String> response = testRestTemplate.exchange(url,HttpMethod.DELETE,null,String.class);
		 Assertions.assertEquals(404, response.getStatusCodeValue());
	}
}









