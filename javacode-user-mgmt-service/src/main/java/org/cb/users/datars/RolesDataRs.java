package org.cb.users.datars;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.base.data.rs.BaseDataRs;
import org.cb.users.rs.RolesRs;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RolesDataRs extends BaseDataRs {

    private RolesRs roles;

    public RolesDataRs(String message, RolesRs roles) {
        super(message);
        this.roles = roles;
    }
}
