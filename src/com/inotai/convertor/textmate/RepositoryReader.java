package com.inotai.convertor.textmate;

import com.dd.plist.NSDictionary;
import com.dd.plist.PropertyListParser;
import com.inotai.intype.bundles.Bundle;
import com.inotai.intype.bundles.BundleItem;
import com.inotai.intype.bundles.Repository;
import com.inotai.jasmine.value.DictionaryValue;
import com.inotai.jasmine.value.StringValue;
import com.inotai.jasmine.value.Value;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * User: Cohen
 * Date: 3/22/12
 * Time: 2:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class RepositoryReader {
    String path;
    String[] dirs;
    Bundle themeBundle;

    RepositoryReader( String path ) {
        this.path = path;
        this.dirs = new String[]{ "Snippets", "Syntaxes" };

        DictionaryValue value = new DictionaryValue();
        value.add("title", new StringValue("Themes"));
        this.themeBundle = new Bundle("B1C5EDC4-93C6-40FA-8327-26D8A48F4D04", value);
    }

    public Repository read() throws InvalidConversionException, Exception {
        Repository repository = new Repository();
        File[] files = new File(this.path).listFiles();
        for(File file : files) {
            if( file.isDirectory() ) {
                Bundle bundle = null;
                if( file.getName().endsWith(".tmBundle") || file.getName().endsWith(".tmbundle") ) {
                    bundle = readBundle(file);
                } else if( file.getName().equals("themes") ) {
                    bundle = readThemes(file);
                }
                if( bundle != null ) {
                    repository.addBundle(bundle);
                }
            }
        }
        return repository;
    }

    private Bundle readBundle( File bundleDir ) throws InvalidConversionException, Exception {
        // Create bundle
        Bundle bundle = convertBundle(bundleDir);

        File[] files = bundleDir.listFiles();
        for(File file : files) {
            if( file.isDirectory() ) {
                readItemDir(bundle, file);
            }
        }

        return bundle;
    }

    private void readItemDir( Bundle bundle, File itemDir ) throws InvalidConversionException, Exception {
        File[] files = itemDir.listFiles();
        for(File file : files) {
            if( file.isFile() ) {
                BundleItem item = convertItem(file);
                if(item != null) {
                    bundle.addItem(item);
                }
            }
        }
    }

    private Value readFile( File file ) throws InvalidConversionException, FileNotFoundException, Exception {
        Value tmValue;
        if(file.canRead()) {
            // Read the TextMate file
            NSDictionary dictionaryValue = (NSDictionary) PropertyListParser.parse(file);
            PropertyListToJasmineValue convertor = new PropertyListToJasmineValue();
            tmValue = convertor.convertNSDictionary(dictionaryValue);
        } else {
            throw new FileNotFoundException("Unable to read from file.");
        }
        return tmValue;
    }

    private Bundle readThemes( File themesDir ) throws InvalidConversionException, Exception {
        Bundle bundle = null;
        File[] files = themesDir.listFiles();
        for(File file : files) {
            if( file.isFile() ) {
                BundleItem item = convertItem(file);
                if(item != null) {
                    if( bundle == null ) {
                        bundle = this.themeBundle;
                    }
                    bundle.addItem(item);
                }
            }
        }
        return bundle;
    }

    // Convertors

    private Bundle convertBundle( File bundleDir ) throws InvalidConversionException, Exception {
        File bundleInfo = new File(bundleDir.getAbsolutePath() + "/info.plist");
        if(bundleInfo.exists()) {
            System.out.println("B "+bundleDir.getName());
            Value tmValue = readFile(bundleInfo);
            return (Bundle)new BundleConvertor().convert(tmValue);
        } else {
            throw new FileNotFoundException(bundleInfo.getPath());
        }
    }

    private BundleItem convertItem( File itemFile ) throws Exception {
        AbstractConvertor convertor = null;
        String itemName = itemFile.getName();
        if( itemName.endsWith(".tmLanguage") ) {
            convertor = new LanguageConvertor();
        } else if( itemName.endsWith(".tmSnippet") ) {
            convertor = new SnippetConvertor();
        } else if( itemName.endsWith(".tmTheme") ) {
            convertor = new ThemeConvertor();
        } else if( itemName.endsWith(".plist") ) {
            String folder_name = itemFile.getParentFile().getName();
            if( folder_name.equals("Syntaxes") ) {
                convertor = new LanguageConvertor();
            } else if( folder_name.equals("Snippets") ) {
                convertor = new SnippetConvertor();
            }
        }

        if( convertor == null ) {
            return null;
        }

        return convertItem( itemFile, convertor );
    }

    private BundleItem convertItem( File itemFile, AbstractConvertor convertor ) throws InvalidConversionException, Exception {

        try {

            Value tmValue = readFile(itemFile);
            return (BundleItem)convertor.convert(tmValue);

        } catch( Exception e ) {
            System.out.println("     x ERROR: " + e.getMessage());
            return null;
        }
    }
}
