package com.mongospring.MongoSpring.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    private Integer id;
    private String name;
    private Integer  age;
    private String std;
    private List<Integer> booksList;


}
