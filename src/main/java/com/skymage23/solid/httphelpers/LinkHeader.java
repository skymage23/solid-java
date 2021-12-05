package com.skymage23.solid.httphelpers;

import java.lang.StringBuilder;

public class LinkHeader {
    String uri;
    String rel;

    public LinkHeader (String uri, String rel){
        this.uri = uri;
        this.rel = rel;
    }

    @Override
    public String toString(){
        return String.format("<%s>; rel=\"%s\"", uri, rel);
    }
}
