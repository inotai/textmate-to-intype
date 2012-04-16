package com.inotai.convertor.textmate;

import com.inotai.intype.bundles.*;
import com.inotai.jasmine.value.DictionaryValue;
import com.inotai.jasmine.value.Value;
import com.inotai.jasmine.value.ValueType;

/**
 * Created by IntelliJ IDEA.
 * User: Cohen
 * Date: 12/21/11
 * Time: 7:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class BundleConvertor extends AbstractConvertor {
    public BundleConvertor() {
        
    }

    public Entry convert( Value tmValue ) throws InvalidConversionException {
        if( tmValue.getType() != ValueType.DICTIONARY )
            throw new IllegalArgumentException("tmValue is not a dictionary");

        DictionaryValue tmBundle = (DictionaryValue)tmValue;

        DictionaryValue itBundle = new DictionaryValue();
        cloneValue(tmBundle, "name", itBundle, "title", ValueType.STRING);

        return new Bundle(tmBundle.get("uuid").getAsString(), itBundle);
    }
}
