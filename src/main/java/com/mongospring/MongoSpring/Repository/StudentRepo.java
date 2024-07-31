package com.mongospring.MongoSpring.Repository;

import com.mongospring.MongoSpring.Model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Student, Integer> {

}
