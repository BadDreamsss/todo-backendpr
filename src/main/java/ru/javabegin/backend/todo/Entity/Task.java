package ru.javabegin.backend.todo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "task", schema = "todolist", catalog = "postgres")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Task {

    @Basic
    @Column(name = "title", nullable = false, length = -1)
    private String title;

    @Basic
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    private Boolean completed;

    @Basic
    @Column(name = "task_date", nullable = true)
    private Date taskDate;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "category_id", insertable=false, updatable=false)
    private Long categoryId;

    @Basic
    @Column(name = "priority_id", insertable=false, updatable=false)
    private Long priorityId;

    @Basic
    @Column(name = "user_id", insertable=false, updatable=false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserData user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return title;
    }
}
