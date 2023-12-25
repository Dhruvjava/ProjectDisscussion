package org.cb.users.rq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.users.rs.PermissionsRs;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RoleRq {

    private Integer id;

    private String code;

    private String name;

    private List<PermissionsRq> permissions;

}
