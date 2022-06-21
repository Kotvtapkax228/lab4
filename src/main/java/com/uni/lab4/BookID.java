package com.uni.lab4;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Embeddable
public class BookID implements Serializable {
    private Integer room;
    private Integer stellage;
    private Integer shelve;

}
