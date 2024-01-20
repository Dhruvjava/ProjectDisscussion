package org.cb;

import org.cb.rq.NotificationsRq;
import org.cb.rs.EmailNameRs;
import org.cb.service.INotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    INotificationsService service;

    @Override
    public void run(String... args) throws Exception {
        NotificationsRq rq = new NotificationsRq();
        rq.setUsername("Dhruv k");
        rq.setSubject("Email Verify");
        rq.setId(1);
        rq.setToEmail(new EmailNameRs("dhruv.rbs.java@gmail.com", "Java Code"));
        service.sendNotifications(rq);
    }
}
