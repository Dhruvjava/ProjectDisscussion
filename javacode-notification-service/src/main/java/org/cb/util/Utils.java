package org.cb.util;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class Utils {

    private static final Pattern EMAIL_PATTERN_REGIX = Pattern.compile("^([A-Za-z0-9_.-]+)@([\\da-z\\.-]+)\\.([a-z]{2,})$");

    private static final Pattern MOBILE_PATTERN_REGIX = Pattern.compile("^(\\+\\d{1,3}( )?)?\\d{10}$");

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

    public static boolean isEmpty(Object[] objs) {
        return (((null == objs) || (0 == objs.length)) ? true : false);
    }

    public static boolean isNotEmpty(Object[] objs) {
        return !(isEmpty(objs));
    }

//    public static ErrorRs populateErrorRs(String codes, Messages message) {
//        if (log.isDebugEnabled()) {
//            log.debug("Executing populateErrorRs(List<String>) -> ");
//        }
//        try {
//            if (isEmpty(codes)) {
//                return null;
//            }
//            ErrorRs error = new ErrorRs();
//            error.setCode(codes);
//            error.setMessage(message.getErrorProperty(codes));
//            return error;
//        } catch (Exception e) {
//            log.error("Exception in populateErrorRs(List<String>) -> {0}", e);
//            return null;
//        }
//    }

    public static boolean isValidEmail(final String email) {
        if (log.isDebugEnabled()) {
            log.debug("Executing isValidEmail(final String email) ->");
        }
        try {
            Matcher matcher = EMAIL_PATTERN_REGIX.matcher(email);
            return matcher.matches();
        } catch (Exception e) {
            log.error("Exception in isValidEmail(final String email) -> {}", e);
            throw e;
        }
    }

    public static boolean isValidMobile(final String email) {
        if (log.isDebugEnabled()) {
            log.debug("Executing isValidMobile(final String email) ->");
        }
        try {
            Matcher matcher = MOBILE_PATTERN_REGIX.matcher(email);
            return matcher.matches();
        } catch (Exception e) {
            log.error("Exception in isValidMobile(final String email) -> {}", e);
            throw e;
        }
    }

}
