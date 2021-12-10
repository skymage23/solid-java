package com.skymage23.solid.datastore;

import com.skymage23.solid.exceptions.datastore.SolidDatastoreArgumentException;
import com.skymage23.solid.exceptions.datastore.SolidDatastoreException;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Security;
import java.util.ArrayList;
import java.util.stream.Stream;


//Start enumeration with the current process directory.
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

    @Override
    public IDatastoreNavigator getNavigator(){
        return new FileSystemNavigator();
    }

    @Override
    public DatastoreContextType getType() {
        return DatastoreContextType.RECURSIVE;
    }

    //A Directory. We just call it a context in order to remain
    //consistent regarding the Solid-Java jargon.
    private class FileSystemContext implements IDatastoreContext {
        //Private methods:
        String name;
        FileSystemContext parent;
        ArrayList<DatastoreContextChildInfo> children;

        public FileSystemContext(URI uri, FileSystemContext parent){
            this.parent = parent;
            this.children = deduceChildren(uri);

            String[] exploded_string = uri.getPath().split("/");
            this.name = exploded_string[exploded_string.length - 1];
        }

        //We go out of our way to use URIs here in order to maintain
        //the abstractions needed by Solid-Java.
        private static ArrayList<DatastoreContextChildInfo> deduceChildren(URI directory)
            throws java.lang.SecurityException,
                com.skymage23.solid.exceptions.datastore.SolidDatastoreException
        {
            //Do this for Linux/MacOS. Then, test on Windows.
            File dir = new File(directory.getPath());
            String[] childList = dir.list();
            ArrayList<DatastoreContextChildInfo> retval =
                    new ArrayList<DatastoreContextChildInfo>();
            try {
                for (String str : childList) {
                    URI tmpUri = new URI(String.format("localhost%s", str));
                    if (Files.isDirectory(Paths.get(str))) {
                        retval.add(new DatastoreContextChildInfo(tmpUri,
                                DatastoreContextChildType.CONTEXT
                        ));
                    } else {
                        retval.add(new DatastoreContextChildInfo(tmpUri,
                                DatastoreContextChildType.CHILD));
                    }
                }
            } catch (java.net.URISyntaxException except) {
                throw new SolidDatastoreException("Error parsing URI string.");
            }
            return retval;
        }

        //API methods:
        @Override
        public IDatastoreContext getParent() {
            return parent;
        }

        @Override
        public ArrayList<DatastoreContextChildInfo> getChildren() {
            return children;
        }

        @Override
        public String getName() {
            return name;
        }
    }

    private class FileSystemNavigator implements IDatastoreNavigator{

        private IDatastoreContext current;

        private FileSystemNavigator(){
            String currentDir = System.getProperty("user.dir");
        }

        @Override
        public IDatastoreObject open(URI uri) {
            return null;
        }

        @Override
        public ArrayList<URI> search(URI uri) {
            return null;
        }

        @Override
        public ArrayList<String> listChildrenOfCurrentContext(IDatastoreObject obj) {
            return null;
        }

        @Override
        public void setContext(IDatastoreContext new_context) {

        }

        @Override
        public void setContext(URI path_to_context) {

        }

        @Override
        public boolean isContext(URI path) {
            return false;
        }

        @Override
        public boolean isDatastoreObject(URI path) {
            return false;
        }

        @Override
        public ArrayList<String> listChildren(IDatastoreObject obj) {
            return null;
        }
    }


    private class FileObject implements IDatastoreObject {

        private URI uri;
        private Mode mode;

        private FileObject(){};

        public IDatastoreObject createFromURI(URI uri){
            FileObject retval = new FileObject();
            retval.
        }

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
