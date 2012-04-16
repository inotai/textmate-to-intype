package com.inotai.convertor.textmate;

import com.inotai.jasmine.value.*;
import com.inotai.intype.bundles.*;

/**
 * Created by IntelliJ IDEA.
 * User: Cohen
 * Date: 12/21/11
 * Time: 7:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class SnippetConvertor extends AbstractConvertor {
    public SnippetConvertor() {
        
    }

    public Entry convert( Value tmValue ) throws InvalidConversionException {
        if( tmValue.getType() != ValueType.DICTIONARY )
            throw new IllegalArgumentException("tmValue is not a dictionary");

        DictionaryValue tmSnippet = (DictionaryValue)tmValue;
        if( tmSnippet.has("uuid") == false )
            throw new InvalidConversionException("uuid is missing");

        DictionaryValue itSnippet = new DictionaryValue();
        cloneValue( tmSnippet, "name", itSnippet, "title", ValueType.STRING );
        cloneValue( tmSnippet, "tabTrigger", itSnippet, "tab_trigger", ValueType.STRING );
        if( tmSnippet.has("keyEquivalent") ) {
            String keyEquivalent = tmSnippet.getAsString("keyEquivalent");
            itSnippet.add("shortcut", new StringValue(Util.convertMacShortcut(keyEquivalent)));
        }
        cloneValue( tmSnippet, "scope", itSnippet, "scope", ValueType.STRING );
        cloneValue( tmSnippet, "content", itSnippet, "content", ValueType.STRING );

        return new SnippetBundleItem(tmSnippet.get("uuid").getAsString(), itSnippet);
    }
}
