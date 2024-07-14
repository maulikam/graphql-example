package com.example.graphqlexample.resolver;

import com.example.graphqlexample.model.Author;
import com.example.graphqlexample.model.Book;
import com.example.graphqlexample.model.Reader;
import com.example.graphqlexample.repository.AuthorReopository;
import com.example.graphqlexample.repository.BookRepository;
import com.example.graphqlexample.repository.ReaderRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

	private final AuthorReopository authorRepository;
	private final BookRepository    bookRepository;
	private final ReaderRepository readerRepository;


	@Autowired
	public Query(AuthorReopository authorRepository, BookRepository bookRepository, ReaderRepository readerRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.readerRepository = readerRepository;
	}

	// getAuthors: [Author]
	public List<Author> getAuthors() {
		return authorRepository.findAll();
	}

	// // getBooks: [Book]
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

	// getAuthorById(id: ID!): Author
	public Author getAuthorById(Long id) {
		return authorRepository.findById(id).orElse(null);
	}


	// getBookById(id: ID!): Book
	public Book getBookById(Long id) {
		return bookRepository.findById(id).orElse(null);
	}

	/**
	 * getReaders: [Reader]
	 *     getBooksByReaderId(readerId: ID!): [Book]
	 *     getReadersByBookId(bookId: ID!): [Reader]
	 */

	public List<Reader> getReaders() {
		return readerRepository.findAll();
	}

	public List<Book> getBooksByReaderId(Long readerId) {
        return readerRepository.findById(readerId).map(Reader::getBooks).orElse(null);
    }
 


}
