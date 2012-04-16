package com.inotai.convertor.textmate;

import com.inotai.intype.bundles.Bundle;
import com.inotai.intype.bundles.Entry;
import com.inotai.intype.bundles.LanguageBundleItem;
import com.inotai.jasmine.value.*;

import javax.swing.text.html.ListView;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Cohen
 * Date: 12/21/11
 * Time: 4:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class LanguageConvertor extends AbstractConvertor {

    public LanguageConvertor() {

    }

    /**
     * Converts language value from TextMate to Intype.
     * @param tmLanguage
     * @return
     */
    public Entry convert( Value tmValue ) throws InvalidConversionException {
        if( tmValue.getType() != ValueType.DICTIONARY )
            throw new IllegalArgumentException("tmValue is not a dictionary");
        
        DictionaryValue tmLanguage = (DictionaryValue)tmValue;

        DictionaryValue itLanguage = new DictionaryValue();
        cloneValue(tmLanguage, "name", itLanguage, "title", ValueType.STRING);
        cloneValue(tmLanguage, "scopeName", itLanguage, "name", ValueType.STRING);
        cloneValue(tmLanguage, "fileTypes", itLanguage, "file_types", ValueType.LIST);

        itLanguage.add("patterns", this.convertPatterns((ListValue) tmLanguage.get("patterns")));
        if(tmLanguage.has("repository")) {
            itLanguage.add("repository", this.convertRepository((DictionaryValue) tmLanguage.get("repository")));
        }

        return new LanguageBundleItem(tmLanguage.get("uuid").getAsString(), itLanguage);
    }

    public ListValue convertPatterns( ListValue tmPatterns ) throws InvalidConversionException {
        ListValue itPatterns = new ListValue();
        for( int i = 0; i < tmPatterns.size(); i++ ) {
            itPatterns.add( this.convertPattern( (DictionaryValue)tmPatterns.get(i) ) );
        }
        return itPatterns;
    }

    public DictionaryValue convertPattern( DictionaryValue tmPattern ) throws InvalidConversionException  {
        DictionaryValue itPattern = new DictionaryValue();

        cloneValue(tmPattern, "name", itPattern, "name", ValueType.STRING);

        cloneValue( tmPattern, "match", itPattern, "match", ValueType.REGEXP );
        cloneValue( tmPattern, "captures", itPattern, "captures", ValueType.DICTIONARY );
        cloneValue( tmPattern, "begin", itPattern, "begin", ValueType.REGEXP );
        cloneValue( tmPattern, "end", itPattern, "end", ValueType.REGEXP );
        cloneValue( tmPattern, "beginCaptures", itPattern, "begin_captures", ValueType.DICTIONARY );
        cloneValue( tmPattern, "endCaptures", itPattern, "end_captures", ValueType.DICTIONARY );
        cloneValue( tmPattern, "include", itPattern, "include", ValueType.STRING );

        if( tmPattern.has("patterns") ) {
            itPattern.add("patterns", this.convertPatterns((ListValue) tmPattern.get("patterns")));
        }
        return itPattern;
    }

    public DictionaryValue convertRepository( DictionaryValue tmRepository ) throws InvalidConversionException {
        DictionaryValue itRepository = new DictionaryValue();
        for( Iterator it = tmRepository.iterator(); it.hasNext(); ) {
            Map.Entry<String, Value> pair = (Map.Entry<String, Value>)it.next();
            itRepository.add( pair.getKey(), this.convertPattern((DictionaryValue)pair.getValue()) );
        }
        return itRepository;
    }


}
