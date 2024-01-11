package org.cb.helper;

import jakarta.mail.internet.InternetAddress;
import lombok.extern.slf4j.Slf4j;
import org.cb.rq.EmailRq;
import org.cb.rs.EmailNameRs;
import org.cb.util.Utils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EmailHelper {

    private EmailHelper() {
    }

    public static EmailRq removeDuplicates(EmailRq rq) {
        if (log.isDebugEnabled()) {
            log.debug("Executing removeDuplicates(EmailRq) ->");
        }
        try {
            if (null == rq) {
                log.error("EmailRq is NULL");
                return null;
            }
            List<String> emails = new ArrayList<String>();
            if (null != rq.getToEmailAddr()) {
                String email = rq.getToEmailAddr().getEmail();
                if (Utils.isEmpty(email) || emails.contains(email)) {
                    log.error("TO : Email is EMPTY/DUPLICATED");
                } else {
                    emails.add(email);
                }
            }
            if (Utils.isNotEmpty(rq.getToEmailAddrs())) {
                List<EmailNameRs> tolist = new ArrayList<EmailNameRs>();
                for (EmailNameRs vm : rq.getToEmailAddrs()) {
                    String email = vm.getEmail();
                    if (Utils.isEmpty(email) || emails.contains(email)) {
                        log.error("TO : Email is EMPTY/DUPLICATED");
                        continue;
                    }
                    tolist.add(vm);
                    emails.add(email);
                }
                rq.setToEmailAddrs(tolist);
            }
            if (Utils.isNotEmpty(rq.getCcEmailAddrs())) {
                List<EmailNameRs> cclist = new ArrayList<EmailNameRs>();
                for (EmailNameRs vm : rq.getCcEmailAddrs()) {
                    String email = vm.getEmail();
                    if (Utils.isEmpty(email) || emails.contains(email)) {
                        log.error("CC : Email is EMPTY/DUPLICATED");
                        continue;
                    }
                    cclist.add(vm);
                    emails.add(email);
                }
                rq.setCcEmailAddrs(cclist);
            }
            if (Utils.isNotEmpty(rq.getBccEmailAddrs())) {
                List<EmailNameRs> bcclist = new ArrayList<EmailNameRs>();
                for (EmailNameRs vm : rq.getBccEmailAddrs()) {
                    String email = vm.getEmail();
                    if (Utils.isEmpty(email) || emails.contains(email)) {
                        log.error("BCC : Email is EMPTY/DUPLICATED");
                        continue;
                    }
                    bcclist.add(vm);
                    emails.add(email);
                }
                rq.setBccEmailAddrs(bcclist);
            }
        } catch (Exception e) {
            log.error("Exception in removeDuplicates(EmailRq) - " + e);
        }
        return rq;
    }

    public static InternetAddress[] prepareInternetAddress(List<EmailNameRs> EmailNameRss) {

        if (log.isDebugEnabled()) {
            log.debug("Executing prepareInternetAddress(EmailNameRss) ->");
        }

        try {
            if (Utils.isEmpty(EmailNameRss)) {
                log.info("EmailNameRss ARE EMPTY --->");
                return null;
            }
            List<InternetAddress> internetAddrs = new ArrayList<InternetAddress>();
            EmailNameRss.stream().filter(x -> (x != null && Utils.isNotEmpty(x.getEmail())))
                    .forEach(x -> {
                        try {
                            if (Utils.isEmpty(x.getName())) {
                                internetAddrs.add(new InternetAddress(x.getEmail(),
                                        x.getEmail()));
                            } else {
                                internetAddrs.add(new InternetAddress(x.getEmail(),
                                        x.getName()));
                            }
                        } catch (Exception e) {
                            log.error("Exception in prepareInternetAddress(EmailNameRss) --> forEach --> "
                                    + e);
                            return;
                        }
                    });

            if (Utils.isEmpty(internetAddrs)) {
                log.error("InternetAddrs ARE EMPTY --->");
                return null;
            }
            return internetAddrs.toArray(new InternetAddress[internetAddrs.size()]);
        } catch (Exception e) {
            log.error("Exception in prepareInternetAddress(EmailNameRss) - " + e);
            return null;
        }
    }

    public static void logSentMailDetails(EmailRq rqVM) {

        try {
            if (null == rqVM) {
                log.error("EmailRq is NULL");
                return;
            }
            log.info("SENT MAIL DETAILS - START");
            log.info("SUBJECT - " + rqVM.getSubject());
            if ((null != rqVM.getToEmailAddr()) || (Utils.isNotEmpty(rqVM.getToEmailAddrs()))) {
                StringBuffer toEmailAddrs = new StringBuffer();
                if (null != rqVM.getToEmailAddr()) {
                    toEmailAddrs.append(rqVM.getToEmailAddr().getName());
                    toEmailAddrs.append("<");
                    toEmailAddrs.append(rqVM.getToEmailAddr().getEmail());
                    toEmailAddrs.append(">");
                    toEmailAddrs.append(";");
                }
                if (Utils.isNotEmpty(rqVM.getToEmailAddrs())) {
                    for (EmailNameRs EmailNameRs : rqVM.getToEmailAddrs()) {
                        toEmailAddrs.append(EmailNameRs.getName());
                        toEmailAddrs.append("<");
                        toEmailAddrs.append(EmailNameRs.getEmail());
                        toEmailAddrs.append(">");
                        toEmailAddrs.append(";");
                    }
                }
                if (toEmailAddrs.length() > 0) {
                    log.info("TO - " + toEmailAddrs);
                }
            }
            if (Utils.isNotEmpty(rqVM.getCcEmailAddrs())) {
                StringBuffer ccEmailAddrs = new StringBuffer();
                for (EmailNameRs EmailNameRs : rqVM.getToEmailAddrs()) {
                    ccEmailAddrs.append(EmailNameRs.getName());
                    ccEmailAddrs.append("<");
                    ccEmailAddrs.append(EmailNameRs.getEmail());
                    ccEmailAddrs.append(">");
                    ccEmailAddrs.append(";");
                }
                if (ccEmailAddrs.length() > 0) {
                    log.info("CC - " + ccEmailAddrs);
                }
            }
            if (Utils.isNotEmpty(rqVM.getBccEmailAddrs())) {
                StringBuffer bccEmailAddrs = new StringBuffer();
                for (EmailNameRs EmailNameRs : rqVM.getBccEmailAddrs()) {
                    bccEmailAddrs.append(EmailNameRs.getName());
                    bccEmailAddrs.append("<");
                    bccEmailAddrs.append(EmailNameRs.getEmail());
                    bccEmailAddrs.append(">");
                    bccEmailAddrs.append(";");
                }
                if (bccEmailAddrs.length() > 0) {
                    log.info("BCC - " + bccEmailAddrs);
                }
            }
            log.info("SENT MAIL DETAILS - END");
        } catch (Exception e) {
            log.error("Exception in logSentMailDetails(EmailRq) - " + e);
        }
    }
}
