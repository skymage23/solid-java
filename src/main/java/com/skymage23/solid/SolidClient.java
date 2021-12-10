package com.skymage23.solid;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Stream;

import com.skymage23.solid.datastore.IDatastore;
import com.skymage23.solid.datastore.IDatastoreContext;
import com.skymage23.solid.datastore.IDatastoreObject;
import com.skymage23.solid.datastore.Mode;
import com.skymage23.solid.exceptions.NotImplementedException;
import com.skymage23.solid.exceptions.SolidException;

public final class SolidClient {
    private static String SolidStorageRef = "http://www.w3.org/ns/pim/space#Storage";
    private static String SolidContainerRef = "http://www.w3.org/ns/ldp#Container";
    private static String SolidBasicContainer = "http://www.w3.org/ns/ldp#BasicContainer";
    private static String SolidResource = "http://www.w3.org/ns/ldp#Resource";
    private static String SolidACLRef = "http://www.w3.org/ns/acl";

    private URI server;
    private IDatastore datastore;

    //Static Methods:
    //Static Factory:
    public static SolidClient create(URI url,
       Class _class
    ) throws java.lang.IllegalAccessException,
             java.lang.InstantiationException,
             java.lang.NoSuchMethodException,
             java.lang.reflect.InvocationTargetException
    {
        if ( ! isServerASolidServer(url)) {
            return null;
        }
        SolidClient retval = new SolidClient();
        retval.datastore = (IDatastore)_class.getDeclaredConstructor().newInstance();
        return retval;
    }

    private static boolean pingTest(URI uri){
        //Do a ping test to make sure we can resolve DNS and hit the server.
        //Making this a separate function because checking whether a server
        //is reachable is wholly separate from checking whether a server is
        //a Solid server.

        //First, do the DNS lookup.
        //Then, do the ping.
        //DNS is more important because Ping may simply be turned off
        //on the server.

        return true;
    }

    //This heuristic looks for HTTP headers that would be
    //expected coming from a Solid server.
    public static boolean isServerASolidServer(URI url){
        //throw new NotImplementedException();
        /*
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Map<String, List<String>> headerMap = response.headers().map();
            List<String> listOfLinks = headerMap.get("Links");

            if (listOfLinks == null){
                return false; //Definitely not a Solid server if missing "Links" headers.   `   ``
            }


            Map<String, String> linksMap = parseLinkHeadersIntoUriRelTypeMap(headerMap.get("Links"));
            //TODO: Finish this function.
        } catch (java.lang.Exception except){
            System.out.println("Uh oh. Something went wrong");
        }
        */
        return true;
    }

    public static Map<String, String> parseLinkHeadersIntoUriRelTypeMap(List<String> listOfLinks){
        Map<String, String> retval = new HashMap<String, String>();

        String url = null;
        boolean urlSet = false;

        String rel = null;
        boolean relSet = false;

        URI tempURI = null;

        for(String a : listOfLinks){
            String[] values = a.split(";");
            for(String b : values){
                b = b.trim();
                char[] tempCharArray = b.toCharArray();
                if ( tempCharArray[0] == '<'){
                    if(urlSet){
                        throw new SolidException("Double URI specification declaration in a Links header");
                    }

                    //Look for '>'
                    int len = tempCharArray.length;
                    int bingoIndex = 1;
                    while ( bingoIndex < len && tempCharArray[bingoIndex] != '>'){
                        bingoIndex++;
                    }
                    if (bingoIndex == len){
                        throw new SolidException("Malformed \"Links\" header in HTTP response.");
                    }

                    //Check that the URL is valid:
                    url = String.copyValueOf(tempCharArray, 1, bingoIndex - 1);

                    try {
                        tempURI = URI.create(url);
                    } catch (java.lang.IllegalArgumentException except) {
                        throw new SolidException("Malformed URI in \"Links\" header.");
                    } catch (java.lang.Exception except){
                        throw new SolidException("Somehow, we got a null pointer here. Better fire up the debugger.");
                    }
                    urlSet = true;
                    if(relSet)
                        break;

                    continue;
                } //end if

                //Now that we have extracted the URL, everything after is of the "<key>=<value>" format.
                //We only look for "ref" (for now).
                String[] keyValueList = b.split("=");
                if (keyValueList.length != 2){
                    throw new SolidException("Malformed Links header: malformed key/value listing.");
                }

                if (keyValueList[0].equals("rel")) {
                    if (relSet) {
                        throw new SolidException("Malformed Links header: multiple \"rel\" declarations.");
                    }
                    rel = keyValueList[1];
                    relSet = true;

                    if(urlSet) {
                        break;
                    }
                }

                continue;
            }

            if (url != null && rel != null) {
                retval.put(url, rel);
            }

            urlSet = false;
            relSet = false;
        }
        return retval;
    }

    //Instance methods:
    //Constructors:
    private SolidClient(){}

    //ACl Opts:
    public void makeACLRequest(){

    }

    //File opts:
    public void listRemoteDataObjects(){

    }

    public void downloadDataObject(){

    }

    public void downloadDataObjectIntoRAM(){

    }

    public void uploadDataObject(Stream d_object_stream){

    }



    public IDatastore getDatastore(){
        return this.datastore;
    }

    public void uploadDataObject(URI uri)
    throws SolidException
    {
        //Open the object.
        IDatastoreObject dObject = this.datastore.open(Mode.READ, uri);
        this.uploadDataObject(dObject.readIntoStream());
    }
}
