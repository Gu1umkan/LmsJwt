package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.Role;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    @SequenceGenerator(name = "company_seq", sequenceName = "company_gen", allocationSize = 1)
    private Long id;
    private String name;
    private String country;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    //*************************************************
    @ManyToMany(mappedBy = "companies")
    private List<Instructor> instructors = new ArrayList<>();
    //*************************************************
    @OneToMany(mappedBy = "company",cascade = CascadeType.REMOVE)
    private List<Course> courses = new ArrayList<>();
    //*************************************************
    @OneToMany
    private List<User> users;
}