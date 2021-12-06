package com.skymage23.solid.datastore;

import java.net.URI;
import java.util.ArrayList;

public interface IDatastoreNavigator {
    public IDatastoreObject goTo(URI uri);
    public ArrayList<String> search(URI uri);
    public ArrayList<String> listChildren(IDatastoreObject obj);
}