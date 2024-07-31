package com.mongospring.MongoSpring.Repository;

import com.mongospring.MongoSpring.Model.Books;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BooksRepo extends MongoRepository<Books, Integer> {
}
