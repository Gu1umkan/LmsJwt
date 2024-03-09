package peaksoft.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    @SequenceGenerator(name = "group_seq", sequenceName = "group_gen", allocationSize = 1)
    private Long id;
    private String groupName;
    private String imageLink;
    private String description;

    //**************************************************************
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    private List<Course> courses = new ArrayList<>();

    //**************************************************************
    @OneToMany(mappedBy = "group",cascade = {CascadeType.REMOVE,CascadeType.REFRESH})
    private List<Student> students = new ArrayList<>();

    //**************************************************************
}