package com.example.demo.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Book;
import com.example.demo.model.BookRepository;
import com.example.demo.model.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	
	@RequestMapping(value = { "/", "/booklist" })
	public String BookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
	
	//RESTful service to get books
	@RequestMapping(value="/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> studentListRest() {
		return (List<Book>) repository.findAll();
	}
	
	//RESTful service to get student by id
	@RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return repository.findById(bookId);
	}

	@RequestMapping(value = "/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:booklist";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long bookId, Model model) {
		model.addAttribute("book", repository.findById(bookId));
		model.addAttribute("categories", crepository.findAll());
		return "editbook";
	}	
}

// TRYING OUT RESTful SERVICES

// __SEARCH__ 

// curl http://localhost:8080/api/books/search/findByAuthor?author=Ernest%20Hemingway

/* % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100  1140    0  1140    0     0  67058      0 --:--:-- --:--:-- --:--:-- 67058{
  "_embedded" : {
    "books" : [ {
      "author" : "Ernest Hemingway",
      "title" : "The Old Man And The Sea",
      "year" : 1952,
      "isbn" : "ISBN43434994394",
      "price" : 0.0,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/books/7"
        },
        "book" : {
          "href" : "http://localhost:8080/api/books/7"
        },
        "category" : {
          "href" : "http://localhost:8080/api/books/7/category"
        }
      }
    }, {
      "author" : "Ernest Hemingway",
      "title" : "For Whom The Bell Tolls",
      "year" : 1940,
      "isbn" : "ISBN434344434394",
      "price" : 0.0,
      "_links" : {
        "self" : {
          "href" : "http://localhost:8080/api/books/8"
        },
        "book" : {
          "href" : "http://localhost:8080/api/books/8"
        },
        "category" : {
          "href" : "http://localhost:8080/api/books/8/category"
        }
      }
    } ]
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/books/search/findByAuthor?author=Ernest%20Hemingway"
    }
  }
}
*/

// $ curl http://localhost:8080/api/books/7

/*  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   406    0   406    0     0   7000      0 --:--:-- --:--:-- --:--:--  7122{
  "author" : "Ernest Hemingway",
  "title" : "The Old Man And The Sea",
  "year" : 1952,
  "isbn" : "ISBN43434994394",
  "price" : 0.0,
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/books/7"
    },
    "book" : {
      "href" : "http://localhost:8080/api/books/7"
    },
    "category" : {
      "href" : "http://localhost:8080/api/books/7/category"
    }
  }
}
*/

// __ADD__
//curl -H "Content-Type: application/json" -X POST -d ' {"author":"Daniel","title":"Miten oppia paremmin?","year":2020, "isbn":"ISBN12322323332","year":2021} ' http://localhost:8080/api/books

//"author" : "Daniel",
/*"title" : "Miten oppia paremmin?",
"year" : 2021,
"isbn" : "ISBN12322323332",
"price" : 0.0,
"_links" : {
"self" : {
"href" : "http://localhost:8080/api/books/9"
},
"book" : {
"href" : "http://localhost:8080/api/books/9"
},
"category" : {
"href" : "http://localhost:8080/api/books/9/category"
}
}
}
*/

// __DELETE __
// curl -X DELETE http://localhost:8080/api/books/7

/* % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0
*/

/*$ curl localhost:8080/api/books
% Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                               Dload  Upload   Total   Spent    Left  Speed
100   786    0   786    0     0  31440      0 --:--:-- --:--:-- --:--:-- 31440{
"_embedded" : {
  "books" : [ {
    "author" : "Ernest Hemingway",
    "title" : "For Whom The Bell Tolls",
    "year" : 1940,
    "isbn" : "ISBN434344434394",
    "price" : 0.0,
    "_links" : {
      "self" : {
        "href" : "http://localhost:8080/api/books/8"
      },
      "book" : {
        "href" : "http://localhost:8080/api/books/8"
      },
      "category" : {
        "href" : "http://localhost:8080/api/books/8/category"
      }
    }
  } ]
},
"_links" : {
  "self" : {
    "href" : "http://localhost:8080/api/books"
  },
  "profile" : {
    "href" : "http://localhost:8080/api/profile/books"
  },
  "search" : {
    "href" : "http://localhost:8080/api/books/search"
  }
}
}
*/

// __UPDATE__
/*curl -X PUT http://localhost:8080/api/books/7 -H
'Content-type:application/json' -d '{"author":
"Daniel Danielson", "title": "Being the tallest man on earth", "year":2009,"isbn":"ISBN2323232332", "gategory":"non-fiction"}'*/

/* $ curl -X PUT http://localhost:8080/api/books/7 -H 'Content-type:application/json' -d '{"author": "Daniel Danielson", "title": "Being the tallest man on earth", "year": 2009, "isbn": "isbn223300233223"}'
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100   529    0   414  100   115   4099   1138 --:--:-- --:--:-- --:--:--  5237{
  "author" : "Daniel Danielson",
  "title" : "Being the tallest man on earth",
  "year" : 2009,
  "isbn" : "isbn223300233223",
  "price" : 0.0,
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/api/books/7"
    },
    "book" : {
      "href" : "http://localhost:8080/api/books/7"
    },
    "category" : {
      "href" : "http://localhost:8080/api/books/7/category"
    }
  }
}
*/








