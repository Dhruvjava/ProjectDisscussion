package org.cb.users.rq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.base.rq.BaseRq;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersRq extends BaseRq {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String mobile;

    private String username;

    private String address;

    private boolean enabled;

    private boolean locked;

    private List<UsersRolesRq> roles;

    private boolean provision;

}
