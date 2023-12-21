package org.cb.users.rs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PermissionsRs {

    private Integer id;

    private String code;

    private String name;

    private String createdBy;

    private String createdOn;

    private String updatedBy;

    private String updatedOn;

}
