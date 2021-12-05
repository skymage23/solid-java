package com.skymage23.solid.datastore;

import java.lang.AutoCloseable;
import java.util.ArrayList;
import java.util.stream.Stream;

public interface IDatastoreObject extends AutoCloseable {
    //Create/Update
    public void write(String str);
    public void write(ArrayList<String> lines);
    public void write(Stream stream);

    //Read:
    public Stream readIntoStream();
    public void readline();
    public ArrayList<String> readAllLine();
    public String readRaw();

    //Delete:
    public void delete();
}