package peaksoft.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_seq")
    @SequenceGenerator(name = "task_seq", sequenceName = "task_gen", allocationSize = 1)
    private Long id;
    private String taskName;
    private String taskText;
    private ZonedDateTime deadLine;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.REFRESH})
    private Lesson lesson;
}
