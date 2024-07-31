package com.mongospring.MongoSpring.controller;

import com.mongospring.MongoSpring.Model.Books;
import com.mongospring.MongoSpring.Model.Student;
import com.mongospring.MongoSpring.Repository.BooksRepo;
import com.mongospring.MongoSpring.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;
import java.util.*;
import java.util.stream.*;

@RestController
public class StudentController {
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private BooksRepo booksRepo;


    @PostMapping("/students")
    public void addStudent(@RequestBody Student student){
        List<Integer> entered_books = student.getBooksList();
        System.out.println(entered_books);
        if(entered_books!=null) {
            // can also use bookscontroller put api here
            for (int i : entered_books) {
                System.out.println(i);
                Books book = booksRepo.findById(i).orElse(null);
                if(book != null) {
                    book.setBooked_copies(book.getBooked_copies() - 1);
                    booksRepo.save(book);
                }
            }
        }
        studentRepo.save(student);
    }

    @GetMapping("/getstudents")
    public List<Student> fetchStudent(){
        return studentRepo.findAll();
    }

    @GetMapping("/getstudents/{id}")
    public Optional<Student> fetchStudentbyid(@PathVariable Integer id ){
        return Optional.ofNullable(studentRepo.findById(id).orElse(null));
    }

    // not checking for list update in books
    @PutMapping("/updatestudents")
    public void updateStudent(@RequestBody Student student){
        //fetch the object using id
        Student student1 = studentRepo.findById(student.getId()).orElse(null);

        List<Integer> before_list = student1.getBooksList();
        List<Integer> after_list = student.getBooksList();

        // to check if the list is null then
        before_list = (before_list == null)? new ArrayList<Integer>(): before_list;
        after_list = (after_list == null)? new ArrayList<Integer>(): after_list;


        System.out.println(before_list);
        System.out.println(after_list);

        Set<Integer> added = before_list.stream().collect(Collectors.toSet());
        Set<Integer> remove = before_list.stream().collect(Collectors.toSet());

        Set<Integer> set1 = before_list.stream().collect(Collectors.toSet());
        Set<Integer> set2 = after_list.stream().collect(Collectors.toSet());

        added.addAll(set2);
        remove.addAll(set2);

        added.removeAll(set1);
        remove.removeAll(set2);

        // check if null
        if(student1 != null){
            student1.setName(student.getName());
            student1.setAge(student.getAge());
            student1.setStd(student.getStd());

            System.out.println(added);
            if(added!=null) {
                // can also use bookscontroller put api here
                for (int i : added) {
                    System.out.println(i);
                    Books book = booksRepo.findById(i).orElse(null);
                    if(book != null) {
                        book.setBooked_copies(book.getBooked_copies() - 1);
                        booksRepo.save(book);
                    }
                }
            }


//
//
//            if(remove.size()!=0) {
//                // can also use bookscontroller put api here
//                for (int i : remove) {
//                    Books book = booksRepo.findById(i).orElse(null);
//                    if(book != null) {
//                        book.setBooked_copies(book.getBooked_copies() - 1);
//                        booksRepo.save(book);
//                    }
//                }
//            }

//            student1.setBooksList(student.getBooksList());
            studentRepo.save(student1);
        }
        // update with the requested data

    }

    @DeleteMapping("/deletestudents/{id}")
    public void deleteStudent(@PathVariable Integer id){
        studentRepo.deleteById(id);
    }


}
