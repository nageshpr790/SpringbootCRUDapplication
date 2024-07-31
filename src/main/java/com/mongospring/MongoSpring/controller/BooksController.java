package com.mongospring.MongoSpring.controller;

import com.mongospring.MongoSpring.Model.Books;
import com.mongospring.MongoSpring.Repository.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BooksController {

    @Autowired
    private BooksRepo booksRepo;

    @PostMapping("/books")
    public void addBooks(@RequestBody Books books){
        booksRepo.save(books);
    }

    @GetMapping("/getbooks")
    public List<Books> fetchBooks(){
        return booksRepo.findAll();
    }

    @GetMapping("/getbooks/{id}")
    public Optional<Books> fetchBooksbyid(@PathVariable Integer id ){
        return Optional.ofNullable(booksRepo.findById(id).orElse(null));
    }

    @PutMapping("/updatebooks")
    public void updateBooks(@RequestBody Books Books){
        //fetch the object using id
        Books Books1 = booksRepo.findById(Books.getId()).orElse(null);

        // check if null
        if(Books1 != null){
            Books1.setBook_name(Books.getBook_name());
            Books1.setIsbn(Books.getIsbn());
            Books1.setTotal_copies(Books.getTotal_copies());
            Books1.setBooked_copies(Books.getBooked_copies());
            booksRepo.save(Books1);

        }
        // update with the requested data

    }

    @DeleteMapping("/deletebooks/{id}")
    public String deleteBooks(@PathVariable Integer id){
       Books book  = booksRepo.findById(id).orElse(null);
        if(book !=null){
            int count = book.getBooked_copies() - book.getTotal_copies();
            if(count == 0) {
                booksRepo.deleteById(id);
                return "Book delted successfully";
            }
        }
        return "Book is been booked by someone";

    }


}
