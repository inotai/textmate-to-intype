package com.inotai.convertor.textmate;

/**
 * Created by IntelliJ IDEA.
 * User: Cohen
 * Date: 12/22/11
 * Time: 2:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class InvalidConversionException extends Exception {
    InvalidConversionException(String message) {
        super(message);
    }
}
