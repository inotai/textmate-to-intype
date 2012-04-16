package com.inotai.intype.bundles;

import com.inotai.intype.bundles.BundleItem;
import com.inotai.jasmine.value.Value;

/**
 * Created by IntelliJ IDEA.
 * User: Cohen
 * Date: 12/21/11
 * Time: 8:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class LanguageBundleItem extends BundleItem {
    public LanguageBundleItem( String uuid, Value value ) {
        super( uuid, value );
    }

    public String getPath() {
        return String.format("grammars/%s.%s.%s", this.getFileName(), this.getUuid(), "itGrammar");
    }
}
