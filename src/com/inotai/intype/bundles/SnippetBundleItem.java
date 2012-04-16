package com.inotai.intype.bundles;

import com.inotai.jasmine.value.Value;

/**
 * Created by IntelliJ IDEA.
 * User: Cohen
 * Date: 3/22/12
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class SnippetBundleItem extends BundleItem {
    public SnippetBundleItem( String uuid, Value value ) {
        super( uuid, value );
    }

    public String getPath() {
        return String.format("snippets/%s.%s.%s", this.getFileName(), this.getUuid(), "itSnippet");
    }
}
