package ru.javabegin.backend.todo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

@Entity
@Table(name = "stat", schema = "todolist", catalog = "postgres")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Stat {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "completed_total", nullable = true)
    private Long completedTotal;

    @Basic
    @Column(name = "uncompleted_total", nullable = true)
    private Long uncompletedTotal;

    @Basic
    @Column(name = "user_id", insertable=false, updatable=false)
    private Long userId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserData user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stat stat = (Stat) o;
        return Objects.equals(id, stat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
