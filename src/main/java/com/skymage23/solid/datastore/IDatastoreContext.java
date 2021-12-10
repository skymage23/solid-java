package com.skymage23.solid.datastore;
import java.util.ArrayList;

public interface IDatastoreContext {
  public IDatastoreContext getParent();
  public ArrayList<String> getChildren();
  public String getName();
}