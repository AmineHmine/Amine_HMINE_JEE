package me.mapping.manytomany.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String userId;
    @Column(unique = true,length = 20, name = "USER_NAME")
    private String username;
    private String password;

    @ManyToMany(mappedBy = "users",fetch= FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
}
