package com.skymage23.solid.datastore;

import java.net.URI;
import java.util.ArrayList;

public interface IDatastoreNavigator {
    public IDatastoreObject open(URI uri); //Attempt to open DatastoreObject.
    public ArrayList<URI> search(URI uri); //Return list of possible paths.
    public ArrayList<String> listChildrenOfCurrentContext(IDatastoreObject obj);
    public void setContext(IDatastoreContext new_context); //I click on a directory in a GUI
    public void setContext(URI path_to_context); //I put the path to a directory in the address bar.
    public boolean isContext(URI path);
    public boolean isDatastoreObject(URI path);
}