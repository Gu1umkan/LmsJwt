package peaksoft.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_seq")
    @SequenceGenerator(name = "lesson_seq", sequenceName = "lesson_gen", allocationSize = 1)
    private Long id;
    private String lessonName;
    //**************************************************************
    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Course course;
    @OneToMany(mappedBy = "lesson",cascade = {CascadeType.REMOVE,CascadeType.REFRESH})
    private List<Task> tasks = new ArrayList<>();
}
