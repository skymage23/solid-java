package com.skymage23.solid.datastore;

import java.net.URI;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileSystemDataStore implements IDatastore{

    @Override
    public IDatastoreObject open(Mode mode, URI uri) {
        return null;
    }

    private class FileObject implements IDatastoreObject {

        private URI uri;
        private Mode mode;

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
