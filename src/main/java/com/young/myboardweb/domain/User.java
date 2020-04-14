package com.young.myboardweb.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "boardList")
@Entity
public class User {

    @Id
    @Column(name = "USER_ID")
    private String id;

    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Enabled enabled;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Board> boardList = new ArrayList<>();
}
