package peaksoft.entity;


import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.Role;
import peaksoft.enums.StudyFormat;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq",sequenceName = "student_gen",allocationSize = 1)
    private Long id;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
    private boolean isBlock = true;
    @ManyToOne(cascade = CascadeType.DETACH)
    private Group group;
    @OneToOne(cascade ={ CascadeType.PERSIST,CascadeType.REMOVE})
    private User user;
}
