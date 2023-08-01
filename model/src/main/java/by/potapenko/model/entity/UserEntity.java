package by.potapenko.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString(exclude = "roles")
@EqualsAndHashCode(exclude = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 20, nullable = false)
    @NotNull(message = "First name is required.")
    @Size(min = 1, max = 20, message = "First name is required.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "The field must contain only Latin letters")
    private String firstName;

    @Column(name = "middle_name", length = 40, nullable = false)
    @NotNull(message = "Middle name is required.")
    @Size(min = 1, max = 40, message = "Middle name  is required.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "The field must contain only Latin letters")
    private String middleName;

    @Column(name = "sur_name", length = 40, nullable = false)
    @NotNull(message = "Surname is required.")
    @Size(min = 1, max = 40, message = "Surname is required.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "The field must contain only Latin letters")
    private String surName;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    @NotNull(message = "Email is required.")
    @Size(min = 1, message = "Email is required.")
    @Email(message = "Email is not well formatted.")
    private String email;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<RoleEntity> roles = new ArrayList<>();

    public void addRole(List<RoleEntity> roles) {
        this.setRoles(roles);
        roles.forEach(roleEntity -> roleEntity.getUsers().add(this));
    }

    public String getFullName() {
        return firstName.concat(" ")
                .concat(middleName).concat(" ")
                .concat(surName);
    }
}
