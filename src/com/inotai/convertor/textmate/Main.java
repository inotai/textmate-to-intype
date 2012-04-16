package com.inotai.convertor.textmate;

import com.inotai.intype.bundles.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: Cohen
 * Date: 12/21/11
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {
        try {
            if(args.length == 0) {
                System.out.println("tm2i.jar <textmate-bundles-directory> <output-directory>");
            } else {
                RepositoryReader repositoryReader = new RepositoryReader(args[0]);
                Repository repository = repositoryReader.read();
                repository.write(args[1]);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
