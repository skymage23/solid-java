package com.skymage23.solid.datastore;

import java.net.URI;

public interface IDatastore {
    public IDatastoreObject open(Mode mode, URI uri);
    public IDatastoreNavigator getNavigator();
    public DatastoreContextType getType();
}