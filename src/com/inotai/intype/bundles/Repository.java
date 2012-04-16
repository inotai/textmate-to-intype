package com.inotai.intype.bundles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Cohen
 * Date: 12/22/11
 * Time: 2:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class Repository {
    private ArrayList<Bundle> bundles;

    public Repository() {
        this.bundles = new ArrayList<Bundle>();
    }

    public void addBundle( Bundle bundle )
    {
        bundles.add(bundle);
        bundle.setRepository(this);
    }

    public void write( String path ) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        for( Bundle bundle : bundles ) {
            String bundlePath = path + bundle.getPath();
            bundle.writeFile(bundlePath);
        }
    }
}
