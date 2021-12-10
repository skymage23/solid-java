package com.skymage23.solid.datastore;
import java.net.URI;

public class DatastoreContextChildInfo {
    public URI path;
    public DatastoreContextChildType childType;

    public DatastoreContextChildInfo(URI _path, DatastoreContextChildType _childType){
        this.path = _path;
        this.childType = _childType;
    }

    @Override
    public String toString(){
        return String.format("Path: %s\n, Context Type: %s",
                path.toString(), childType.toString());
    }
}
