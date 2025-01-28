package com.example.gecekodubackend.core.entities;

import com.example.gecekodubackend.entity.abstracts.Entity;
import com.example.gecekodubackend.entity.concretes.Event;
import com.example.gecekodubackend.entity.concretes.Role;
import com.example.gecekodubackend.entity.concretes.Workshop;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Table(name = "users")
@jakarta.persistence.Entity
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @NotEmpty(message = "Kullanıcı adı boş olamaz!")
    @Max(value = 30, message = "En fazla 30 karakter girebilirsiniz!")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "Kullanıcı soyadı boş olamaz!")
    @Max(value = 30, message = "En fazla 30 karakter girebilirsiniz!")
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "Email adresiniz boş olamaz!")
    @Email(message = "Girdiğiniz adres email formatına uygun değil!")
    @Max(value = 40, message = "En fazla 40 karakter girebilirsiniz!")
    @Column(name = "email")
    private String email;

    @Size(min = 8, max = 30, message = "Girdiğiniz şifre en az 8, en çok 30 haneli olmalıdır!")
    @NotEmpty(message = "Şifre alanı boş olamaz!")
    @Column(name = "password")
    private String password;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "user_workshop",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "workshop_id"))
    private Set<Workshop> workshops;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "user_event",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private Set<Event> events;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @JoinTable(name = "authorities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Role> authorities;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

}
