package br.com.task.domain;

import br.com.task.domain.enumtype.StatusType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @Column(columnDefinition = "VARCHAR", nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "VARCHAR", nullable = false)
    private StatusType status;

    @Column(columnDefinition = "BOOLEAN", nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    private Date createdIn;

    @Column
    private Date updatedIn;

    @Column
    private Date finishedIn;

    @Column
    private Date removedIn;
}
