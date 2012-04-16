package com.inotai.util;

/**
 * Created by IntelliJ IDEA.
 * User: Cohen
 * Date: 12/22/11
 * Time: 2:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class Inflector {
    static public String titleToFileName( String title ) {
        String ret = title
                .replaceAll( "[^a-z^A-Z^0-9]+", "_" )
                .toLowerCase();
        return ret;
    }
}
