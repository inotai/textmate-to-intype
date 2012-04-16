package com.inotai.intype.bundles;

import com.inotai.util.Inflector;
import com.inotai.jasmine.JasminePrettyFormatter;
import com.inotai.jasmine.JasmineWriter;
import com.inotai.jasmine.value.DictionaryValue;
import com.inotai.jasmine.value.Value;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Cohen
 * Date: 12/22/11
 * Time: 2:01 AM
 * To change this template use File | Settings | File Templates.
 */
abstract public class Entry {
    protected Value value;
    protected String uuid;

    protected Entry( String uuid, Value value ) {
        this.uuid = uuid.toUpperCase();
        this.value = value;
    }

    public String getUuid() {
        return this.uuid;
    }

    public Value getValue() {
        return this.value;
    }

    public String getTitle() {
        return ((DictionaryValue)this.value).get("title").getAsString();
    }

    public String getFileName() {
        String fileName = Inflector.titleToFileName(this.getTitle());
        if( fileName.length() == 0 ) {
            return "_";
        }
        return fileName;
    }

    abstract public String getPath();

    public void writeFile(String path) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        // Write to string
        StringBuilder builder = new StringBuilder();
        JasmineWriter writer = new JasmineWriter(new JasminePrettyFormatter());
        writer.write( this.value, builder );
        
        // Write to file
        FileOutputStream fos = new FileOutputStream(path);
        OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-16LE");
        out.write( "\uFEFF" );
        out.write( builder.toString() );
        out.close();
    }
}
