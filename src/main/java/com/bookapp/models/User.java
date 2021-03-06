package com.bookapp.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bookapp.models.values.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
    @Id
    private String id;

    @Column(unique = true)
    private String facebookUserId;

    @NotNull
    private String name;

    @Column(unique = true)
    @Size(max = 254, min = 6, message = "User email's length must be in between {min} and {max}")
    private String email;

    private boolean isLocked;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = true)
    private Date premiumExpireDate;

    @ManyToMany(targetEntity = Book.class, mappedBy = "users")
    private Set<Book> favoriteBooks;

    @OneToMany(targetEntity = Review.class, mappedBy = "user")
    private Set<Review> reviews;
    
    @ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

    @PrePersist
    private void generateId() {
        id = UUID.randomUUID().toString().replace("-", "");
        role = role == null ? Role.FREE_USER : role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.name()));
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final User user = (User) obj;
        return user.getId().equals(this.getId());
    }
}
