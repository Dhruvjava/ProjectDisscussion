package org.cb.users.rq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.base.rq.BaseRq;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsersRolesRq extends BaseRq {

    private String code;

    private String name;

}
