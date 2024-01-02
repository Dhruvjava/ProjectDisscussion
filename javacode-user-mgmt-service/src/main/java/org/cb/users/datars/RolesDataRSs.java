package org.cb.users.datars;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.base.data.rs.BaseDataRs;
import org.cb.users.rs.RolesRs;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RolesDataRSs extends BaseDataRs {

    private List<RolesRs> roles;

    public RolesDataRSs(String message, List<RolesRs> roles) {
        super(message);
        this.roles = roles;
    }

}
