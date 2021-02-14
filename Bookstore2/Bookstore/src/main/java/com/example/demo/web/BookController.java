package com.example.demo.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.Book;
import com.example.demo.model.BookRepository;
import com.example.demo.service.BookService;

@Controller
public class BookController {

	@Autowired
	BookService service;

	@Autowired
	private BookRepository repository;

	@RequestMapping(value = { "/", "/booklist" })
	public String BookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	@RequestMapping(value = { "/", "/add" })
	public String addbook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
}
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savebook(Book book) {
		repository.save(book);
		return "redirect:booklist";
}
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deletebook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
}
	@RequestMapping(value = "/edit/{id}")
	public String editbook(Model model, @PathVariable("id") Optional<Long> id) {
		Book book = service.getBookById(id.get());
		model.addAttribute("book", book);
		return "editbook";
}
	@RequestMapping(value = "/savebook", method = RequestMethod.POST)
	public String createOrUpdateBook(Book book) {
		System.out.println("createOrUpdateBook ");
		service.createOrUpdateBook(book);
		return "redirect:/booklist";
	}

}