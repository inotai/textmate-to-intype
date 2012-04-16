package com.inotai.intype.bundles;

import com.inotai.jasmine.value.Value;

/**
 * Created by IntelliJ IDEA.
 * User: Cohen
 * Date: 12/21/11
 * Time: 8:01 PM
 * To change this template use File | Settings | File Templates.
 */
abstract public class BundleItem extends Entry {
    protected Bundle bundle;

    protected BundleItem( String uuid, Value value ) {
        super(uuid, value);
        this.bundle = null;
    }

    public void setBundle( Bundle bundle ) {
        this.bundle = bundle;
    }
}
