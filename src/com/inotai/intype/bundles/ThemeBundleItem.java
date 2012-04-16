package com.inotai.intype.bundles;

import com.inotai.jasmine.value.Value;

/**
 * Created by IntelliJ IDEA.
 * User: Cohen
 * Date: 3/22/12
 * Time: 4:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ThemeBundleItem extends BundleItem {
    public ThemeBundleItem( String uuid, Value value ) {
        super( uuid, value );
    }

    public String getPath() {
        return String.format("themes/%s.%s.%s", this.getFileName(), this.getUuid(), "itTheme");
    }
}
