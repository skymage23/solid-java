package com.skymage23.solid.datastore;

// A Recursive Context is a Context capable of
// having other Contexts as children (like a
// subdirectory).

//A Non-recursive Context is unable to have a
//Context as a child, only DatastoreObjects.
public enum DatastoreContextType {
    RECURSIVE,
    NONRECURSIVE
}