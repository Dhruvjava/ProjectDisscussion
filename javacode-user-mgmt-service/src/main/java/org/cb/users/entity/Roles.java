package org.cb.users.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.base.entity.BaseBO;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Roles extends BaseBO {

    private String code;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roles", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<RoleToPermission> permissions;

}
