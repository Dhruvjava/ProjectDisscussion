package org.cb.service;

import freemarker.template.TemplateException;
import org.cb.base.data.rs.BaseDataRs;
import org.cb.rq.NotificationsRq;

import java.io.IOException;

public interface INotificationsService {

    public BaseDataRs sendNotifications(NotificationsRq rq) throws Exception;

}
