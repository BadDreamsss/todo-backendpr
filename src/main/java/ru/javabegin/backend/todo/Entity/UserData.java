package ru.javabegin.backend.todo.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user_data", schema = "todolist", catalog = "postgres")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserData {

    @Basic
    @Column(name = "email", nullable = false, length = -1)
    private String email;

    @Basic
    @Column(name = "userpassword", nullable = false, length = -1)
    private String userpassword;

    @Basic
    @Column(name = "username", nullable = false, length = -1)
    private String username;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;


    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private Set<RoleData> roles;

    //    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Task> tasks;
//
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Category> categories;
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private List<Priority> priorities;
//
//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, optional = false)
//    private Activity activity; // активность пользователя (активация и любые другие)
//
//    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, optional = false)
//    private Stat stat; // общая статистика пользователя по всем задачам

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return Objects.equals(id, userData.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return username;
    }
}
