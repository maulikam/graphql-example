package com.example.graphqlexample.resolver;

import com.example.graphqlexample.model.Author;
import com.example.graphqlexample.model.Book;
import com.example.graphqlexample.repository.AuthorReopository;
import com.example.graphqlexample.repository.BookRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Optional;

@Component
public class Mutation implements GraphQLMutationResolver {

	private final AuthorReopository authorReopository;

	private final BookRepository bookRepository;

	@Autowired
	public Mutation(AuthorReopository authorReopository, BookRepository bookRepository) {
		this.authorReopository = authorReopository;
        this.bookRepository = bookRepository;
	}

	/**
	 * type Mutation {
	 *     createAuthor(name: String!): Author
	 *     createBook(title: String!, authorId: ID!): Book
	 * }
	 */

	public Author createAuthor(String name) {
        Author author = new Author();
		author.setName(name);
        return authorReopository.save(author);
    }

	public Book createBook(String title, Long authorId) {
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(authorReopository.findById(authorId).orElseThrow(()->new IllegalArgumentException("Author Not Found")));
		return bookRepository.save(book);
	}

}
