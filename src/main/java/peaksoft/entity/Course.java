package peaksoft.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    @SequenceGenerator(name = "course_seq",sequenceName = "course_gen",allocationSize = 1)
    private Long id;
    private String courseName;
    private String description;
    private LocalDate dateOfStart;
    //*************************************************
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.DETACH})
    private Company company;
    //*************************************************/
    @ManyToMany(mappedBy = "courses")
    private List<Group> groups = new ArrayList<>();
    //*************************************************
    @OneToMany(mappedBy = "course",cascade = {CascadeType.REMOVE,CascadeType.REFRESH})
    private List<Lesson> lessons = new ArrayList<>();
    //*************************************************
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    private Instructor instructor;
    //*************************************************
}