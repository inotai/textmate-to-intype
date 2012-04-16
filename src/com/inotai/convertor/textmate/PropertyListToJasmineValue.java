package com.inotai.convertor.textmate;

import com.dd.plist.*;
import com.inotai.jasmine.value.DictionaryValue;
import com.inotai.jasmine.value.ListValue;
import com.inotai.jasmine.value.StringValue;
import com.inotai.jasmine.value.Value;

/**
 * Created by IntelliJ IDEA.
 * User: Cohen
 * Date: 12/21/11
 * Time: 3:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class PropertyListToJasmineValue {

    public PropertyListToJasmineValue() {

    }


    /**
     * Converts any NSObject to Value.
     *
     * @param nsObject Object to convert.
     * @return Resulting value.
     */
    public Value convertNSObject( NSObject nsObject ) {
        String className = nsObject.getClass().getName();
        if( className == "com.dd.plist.NSDictionary") {
            return this.convertNSDictionary( (NSDictionary)nsObject );
        } else if( className == "com.dd.plist.NSArray") {
            return this.convertNSArray((NSArray) nsObject);
        } else if( className == "com.dd.plist.NSString" ) {
            return new StringValue( ((NSString)nsObject).toString() );
        } else if( className == "com.dd.plist.NSNumber" ) {
            NSNumber nsNumber = (NSNumber)nsObject;
            switch(nsNumber.type()) {
            case NSNumber.BOOLEAN:
                return Value.createBooleanValue( nsNumber.boolValue() );
            case NSNumber.INTEGER:
                return Value.createIntegerValue( nsNumber.intValue() );
            case NSNumber.REAL:
                return Value.createRealValue( nsNumber.floatValue() );
            }
        }
        
        throw new IllegalArgumentException("Unsupported NSOBject type.");
    }

    /**
     * Converts NSDictionary to DictionaryValue.
     * @param nsDictionary
     * @return
     */
    public DictionaryValue convertNSDictionary( NSDictionary nsDictionary ) {
        DictionaryValue dictValue = new DictionaryValue();
        String[] keys = nsDictionary.allKeys();
        for( String key : keys ) {
            NSObject nsObject = nsDictionary.objectForKey(key);
            dictValue.add( key, this.convertNSObject(nsObject) );
        }
        return dictValue;
    }

    public ListValue convertNSArray( NSArray nsArray ) {
        ListValue listValue = new ListValue();
        for( int i = 0; i < nsArray.count(); i++ ) {
            listValue.add( this.convertNSObject(nsArray.objectAtIndex(i)) );
        }
        return listValue;
    }

}
