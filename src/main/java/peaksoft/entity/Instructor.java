package peaksoft.entity;


import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.Role;
import peaksoft.enums.Specialization;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_seq")
    @SequenceGenerator(name ="instructor_seq",sequenceName = "instructor_gen",allocationSize = 1)
    private Long id;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Specialization specialization;
    //***************************************************
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    private List<Company> companies = new ArrayList<>();
    @OneToMany(mappedBy = "instructor",cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    private List<Course> courses = new ArrayList<>();
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private User user;
}