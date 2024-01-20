package org.cb.rq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.base.rs.BaseRq;
import org.cb.rs.EmailNameRs;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationsRq extends BaseRq {

    private Integer id;

    private EmailNameRs toEmail;

    private String username;

    private String subject;

}
