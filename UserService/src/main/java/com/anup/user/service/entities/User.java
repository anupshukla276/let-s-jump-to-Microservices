package com.anup.user.service.entities;

import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="micro_users")
public class User {

    @Id
    @Column(name="Id")
    private String userId;
    @Column(name="NAME",length = 20)
    private String name;
    @Column(name="EMAIL")
    private String email;
    @Column(name="ABOUT")
    private String about;
    //

    @Transient
    private List<Rating>ratings =new ArrayList<>();
}
