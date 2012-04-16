package com.inotai.convertor.textmate;

import com.inotai.intype.bundles.Entry;
import com.inotai.jasmine.value.DictionaryValue;
import com.inotai.jasmine.value.RegExpValue;
import com.inotai.jasmine.value.Value;
import com.inotai.jasmine.value.ValueType;

abstract class AbstractConvertor
{
    abstract public Entry convert( Value tmValue ) throws InvalidConversionException;

    void cloneValue( DictionaryValue tmDict, String tmKey, DictionaryValue itDict, String itKey, ValueType itType ) throws InvalidConversionException {
        Value tmValue = tmDict.get(tmKey);
        Value itValue = null;
        if( tmValue != null ) {
            if( tmValue.getType() != itType ) {
                switch( itType ) {
                case REGEXP:
                    if( tmValue.getType() == ValueType.STRING ) {
                        String strValue = tmValue.getAsString();
                        String replacedValue = strValue.replace("\\/","/");
                        itValue = new RegExpValue( replacedValue, "" );
                    } else {
                        throw new InvalidConversionException("Unable to convert value to RegExp.");
                    }
                    break;
                default:
                    throw new InvalidConversionException("Conversion not implemented.");
                }
            } else {
                itValue = (Value)tmValue.clone();
            }
        }

        if( itValue != null ) {
            itDict.add(itKey, itValue);
        }
    }
}