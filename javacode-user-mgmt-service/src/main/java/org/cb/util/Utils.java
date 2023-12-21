package org.cb.util;

import lombok.extern.slf4j.Slf4j;
import org.cb.Messages;
import org.cb.base.rs.ErrorRs;

import java.util.List;

@Slf4j
public class Utils {

    public static boolean isEmpty(final String value){
        if (log.isDebugEnabled()){
            log.debug("Executing isEmpty(String value) ->");
        }
        try {
            return null == value || value.trim().isEmpty();
        }catch (Exception e){
            log.error("Exception in isEmpty(String value) -> {}", e);
            throw e;
        }
    }

    public static boolean isEmpty(List list){
        if (log.isDebugEnabled()){
            log.debug("Executing isEmpty(String value) ->");
        }
        try {
            return null == list || list.isEmpty();
        }catch (Exception e){
            log.error("Exception in isEmpty(String value) -> {}", e);
            throw e;
        }
    }

    public static boolean isNotEmpty(String string){
        if (log.isDebugEnabled()){
            log.debug("Executing isNotEmpty(String value) ->");
        }
        try {
            return !isEmpty(string);
        }catch (Exception e){
            log.error("Exception in isNotEmpty(String value) -> {}", e);
            throw e;
        }
    }

    public static boolean isNotEmpty(List list){
        if (log.isDebugEnabled()){
            log.debug("Executing isNotEmpty(String value) ->");
        }
        try {
            return !isEmpty(list);
        }catch (Exception e){
            log.error("Exception in isNotEmpty(String value) -> {}", e);
            throw e;
        }
    }

    public static ErrorRs populateErrorRs(String codes, Messages message) {
        if (log.isDebugEnabled()) {
            log.debug("Executing populateErrorRs(List<String>) -> ");
        }
        try {
            if (isEmpty(codes)) {
                return null;
            }
            ErrorRs error = new ErrorRs();
            error.setCode(codes);
            error.setMessage(message.getErrorProperty(codes));
            return error;
        } catch (Exception e) {
            log.error("Exception in populateErrorRs(List<String>) -> {0}", e);
            return null;
        }
    }

}
