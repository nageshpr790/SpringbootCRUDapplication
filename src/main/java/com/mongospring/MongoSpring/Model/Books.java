package com.mongospring.MongoSpring.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexOptions;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books {

    @Id
    @Generated
    private int id;
    @Indexed(unique = true)
    private String book_name;
    private  String isbn;
    private int total_copies;
    private int booked_copies;

}
