package com.skymage23.solid.datastore;

import com.skymage23.solid.exceptions.datastore.SolidDatastoreArgumentException;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileSystemDatastore implements IDatastore {

    @Override
    public IDatastoreObject open(Mode mode, URI uri) {
        //Is the host null? Localhost? Does it have a port number?
        if (uri.getPort() == -1){
            throw new SolidDatastoreArgumentException("URI for local file access cannot have a port.");
        }

        String host = uri.getHost();
        if (( host != null) && (host != "localhost")){
            throw new SolidDatastoreArgumentException("URI host for local file access must be \"localhost\"");
        }

        File __file = new File();

        return null;
    }


    private class FileObject implements IDatastoreObject {

        private URI uri;
        private Mode mode;

        private FileObject(){

        };

        @Override
        public void write(String str) {

        }

        @Override
        public void write(ArrayList<String> lines) {

        }

        @Override
        public void write(Stream stream) {

        }

        @Override
        public Stream readIntoStream() {
            return null;
        }

        @Override
        public void readline() {

        }

        @Override
        public ArrayList<String> readAllLine() {
            return null;
        }

        @Override
        public String readRaw() {
            return null;
        }

        @Override
        public void delete() {

        }

        @Override
        public void close() throws Exception {

        }
    }

}
