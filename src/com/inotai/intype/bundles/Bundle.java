package com.inotai.intype.bundles;

import com.inotai.jasmine.value.Value;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Cohen
 * Date: 12/22/11
 * Time: 1:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class Bundle extends Entry {
    private Repository repository;
    private ArrayList<BundleItem> items;

    public Bundle( String uuid, Value value ) {
        super(uuid, value);
        this.items = new ArrayList<BundleItem>();
    }

    public void setRepository( Repository repository ) {
        this.repository = repository;
    }

    public Repository getRepository() {
        return this.repository;
    }

    public String getPath() {
        return String.format("%s.%s.%s", this.getFileName(), this.getUuid(), "itBundle" );
    }

    public void addItem( BundleItem item ) {
        this.items.add( item );
        item.setBundle(this);
    }

    public void writeFile(String path) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        // Create bundle directory
        File bundleDir = new File(path);
        bundleDir.mkdirs();

        // Save info file
        String infoPath = path + String.format("/info.%s.itInfo", this.getUuid());
        super.writeFile(infoPath);

        // Write child items
        for( BundleItem item : this.items ) {
            File itemFile = new File(path + "/" + item.getPath());
            itemFile.getParentFile().mkdirs();
            item.writeFile(itemFile.getPath());
        }
    }
}
