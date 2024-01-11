package org.cb.rq;

import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.cb.base.rs.BaseRq;
import org.cb.rs.EmailNameRs;

@Getter
@Setter
@NoArgsConstructor
public class EmailRq extends BaseRq {

    private static final long serialVersionUID = -2255276665946007648L;

    private String subject;

    private String messageText;

    private EmailNameRs toEmailAddr;

    private List<EmailNameRs> toEmailAddrs = Collections.<EmailNameRs>emptyList();

    private List<EmailNameRs> ccEmailAddrs = Collections.<EmailNameRs>emptyList();

    private List<EmailNameRs> bccEmailAddrs = Collections.<EmailNameRs>emptyList();

    private List<AttachmentRq> attachments = Collections.<AttachmentRq>emptyList();

    private String messageId;

}
