package org.cb.base.rq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseRq implements Serializable {

    private Integer id;

    private String createdBy;

    private String createdOn;

    private String updatedBy;

    private String updatedOn;

}
