package com.inotai.convertor.textmate;

/**
 * Created by IntelliJ IDEA.
 * User: Cohen
 * Date: 3/23/12
 * Time: 11:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class Util {
    static public String convertMacShortcut( String macShortcut ) {
        boolean isCmd = false;
        boolean isCtrl = false;
        boolean isAlt = false;
        boolean isShift = false;
        String key = null;

        // http://my.opera.com/daniel/blog/2012/02/27/unicode-symbols-for-the-mac-keyboard-special-keys
        for( int i = 0; i < macShortcut.length(); i++ ) {
            switch( macShortcut.charAt(i) ) {
                case '^':
                    isCtrl = true;
                    break;
                case '⌥':
                    isAlt = true;
                    break;
                case '⇧':
                    isShift = true;
                    break;
                case '⌘':
                    isCmd = true;
                    break;
                case '\u21E5': // ⇥
                    key = "Tab";
                    break;
                case '\u23CE': // ⏎
                case '\u2324': // ⌤
                    key = "Enter";
                    break;
                case '\u2326': // ⌦
                    key = "Del";
                    break;
                case '\u232B': // ⌫
                    key = "Backspace";
                    break;
                case '\u238B': // ⎋
                    key = "Esc";
                    break;
                case '\u21DE': // ⇞
                    key = "Page-Up";
                    break;
                case '\u21DF': // ⇟
                    key = "Page-Down";
                    break;
                case '\u2196': // ↖
                    key = "Home";
                    break;
                case '\u2198': // ↘
                    key = "End";
                    break;
                case '\u2190': // ←
                    key = "Left";
                    break;
                case '\u2191': // ↑
                    key = "Up";
                    break;
                case '\u2192': // →
                    key = "Right";
                    break;
                case '\u2193': // ↓
                    key = "Down";
                    break;
                case '\u2327': // ⌧
                    key = "Clear";
                    break;
                default:
                    key = Character.toString(macShortcut.charAt(i)).toUpperCase();
            }
        }

        if( key == null )
            return "";

        String ret = "";
        if( isCtrl || isCmd ) {
            ret += "Ctrl";
        }
        if( isAlt ) {
            if( ret.length() > 0 )
                ret += '+';
            ret += "Alt";
        }
        if( isShift ) {
            if( ret.length() > 0 )
                ret += '+';
            ret += "Shift";
        }

        if( ret.length() > 0 )
            ret += '+';
        ret += key;

        return ret;
    }
}
