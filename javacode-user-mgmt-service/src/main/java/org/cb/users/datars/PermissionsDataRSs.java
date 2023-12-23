package org.cb.users.datars;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.base.data.rs.BaseDataRs;
import org.cb.users.rs.PermissionsRs;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PermissionsDataRSs extends BaseDataRs {

    private List<PermissionsRs> permission;

    public PermissionsDataRSs(String message, List<PermissionsRs> permission) {
        super(message);
        this.permission = permission;
    }
}

