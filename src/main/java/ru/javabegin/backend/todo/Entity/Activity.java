package ru.javabegin.backend.todo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

@Entity
@Table(name = "activity", schema = "todolist", catalog = "postgres")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Column(updatable = false)
    private String uuid;

    @Basic
    @Convert(converter = org.hibernate.type.NumericBooleanConverter.class)
    private Boolean activated;

    @Basic
    @Column(name = "user_id", insertable=false, updatable=false)
    private Long userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserData user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(id, activity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
