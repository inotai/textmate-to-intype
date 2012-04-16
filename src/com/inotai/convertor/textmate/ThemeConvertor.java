package com.inotai.convertor.textmate;

import com.inotai.intype.bundles.Entry;
import com.inotai.intype.bundles.SnippetBundleItem;
import com.inotai.intype.bundles.ThemeBundleItem;
import com.inotai.jasmine.value.DictionaryValue;
import com.inotai.jasmine.value.ListValue;
import com.inotai.jasmine.value.Value;
import com.inotai.jasmine.value.ValueType;

/**
 * Created by IntelliJ IDEA.
 * User: Cohen
 * Date: 3/22/12
 * Time: 4:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThemeConvertor extends AbstractConvertor {

    public ThemeConvertor() {

    }

    public Entry convert( Value tmValue ) throws InvalidConversionException {
        if( tmValue.getType() != ValueType.DICTIONARY )
            throw new IllegalArgumentException("tmValue is not a dictionary");

        DictionaryValue tmTheme = (DictionaryValue)tmValue;
        if( tmTheme.has("uuid") == false )
            throw new InvalidConversionException("uuid is missing");

        DictionaryValue itTheme = new DictionaryValue();
        cloneValue( tmTheme, "name", itTheme, "title", ValueType.STRING );

        if( tmTheme.has("settings") ) {
            itTheme.add("patterns", this.convertSettings((ListValue) tmTheme.get("settings")));
        }

        return new ThemeBundleItem(tmTheme.get("uuid").getAsString(), itTheme);
    }

    private ListValue convertSettings( ListValue tmSettings ) throws InvalidConversionException {
        ListValue itSettings = new ListValue();
        for( int i = 0; i < tmSettings.size(); i++ ) {
            if( i == 0 ) {
                itSettings.add( this.convertThemeSettings((DictionaryValue) tmSettings.get(i)) );
            } else {
                itSettings.add( this.convertItemSettings((DictionaryValue) tmSettings.get(i)) );
            }
        }
        return itSettings;
    }

    private DictionaryValue convertThemeSettings( DictionaryValue tmThemeSettings ) throws InvalidConversionException {
        DictionaryValue itThemeSettings = new DictionaryValue();
        cloneValue( tmThemeSettings, "background", itThemeSettings, "background", ValueType.STRING );
        cloneValue( tmThemeSettings, "foreground", itThemeSettings, "foreground", ValueType.STRING );
        cloneValue( tmThemeSettings, "selection", itThemeSettings, "selection", ValueType.STRING );
        cloneValue( tmThemeSettings, "invisibles", itThemeSettings, "invisibles", ValueType.STRING );
        cloneValue( tmThemeSettings, "line_highlight", itThemeSettings, "line_highlight", ValueType.STRING );
        // Reuse line highlight for hotspot
        cloneValue( tmThemeSettings, "hotspot", itThemeSettings, "line_highlight", ValueType.STRING );

        return itThemeSettings;
    }

    private DictionaryValue convertItemSettings( DictionaryValue tmItemSettings ) throws InvalidConversionException {
        DictionaryValue itItemSettings = new DictionaryValue();
        cloneValue( tmItemSettings, "name", itItemSettings, "title", ValueType.STRING );

        return itItemSettings;
    }

}
