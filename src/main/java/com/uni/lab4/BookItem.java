package com.uni.lab4;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name = "books")
public class BookItem extends RepresentationModel<BookItem> implements Serializable {
    @EmbeddedId
    private BookID id;
    private String title;
    private String author;
    private String genre;
    private Integer year;


}
