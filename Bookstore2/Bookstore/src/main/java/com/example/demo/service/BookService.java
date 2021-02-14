package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.model.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository repository;

	public Book getBookById(Long id) {
		System.out.println("getBookId");
		Optional<Book> book = repository.findById(id);

		if (book.isPresent()) {
			return book.get();
		}
		return null;
	}

	public Book createOrUpdateBook(Book book) {
		System.out.println("createOrUpdateEmployee");

		if (book.getId() == null) {
			book = repository.save(book);

			return book;
		} else {

			Optional<Book> bookitem = repository.findById(book.getId());

			if (bookitem.isPresent()) {
				Book newEntity = bookitem.get();
				newEntity.setAuthor(book.getAuthor());
				newEntity.setTitle(book.getTitle());
				newEntity.setYear(book.getYear());
				newEntity.setIsbn(book.getIsbn());

				newEntity = repository.save(newEntity);

				return newEntity;

			} else {
				book = repository.save(book);

				return book;

			}
		}
	}
}
