package test;

import com.skymage23.solid.SolidClient;
import com.skymage23.solid.datastore.IDatastore;
import com.skymage23.solid.datastore.IDatastoreNavigator;
import com.skymage23.solid.datastore.FileSystemDatastore;
import java.net.URI;

public final class Main {
    SolidClient client;
    public static void main(String[] args){
        //How to instantiate the main Datastore object?
        //First, we create a Solid object.
        try {
            URI solidURI = URI.create("localhost:3000");
            Main main = new Main(solidURI);
            main.runLoop();
        } catch (java.lang.Exception except){
            System.err.println("Uh oh. Something went wrong.");
            System.err.printf("Exception: %s\nMessage: %s",
                    except.getClass().getName(),
                    except.getMessage()
            );
        }
    }

    public Main(URI uri)
    throws java.lang.IllegalAccessException,
           java.lang.InstantiationException,
           java.lang.NoSuchMethodException,
           java.lang.reflect.InvocationTargetException
    {
        SolidClient sClient = SolidClient.create(
                URI.create("localhost:3000"),
                FileSystemDatastore.class
        );
    }

    public void showStartupPrelude(){
        System.out.println("Welcome to the Solid-Java CLI program:");
        System.out.println("This program implements the Solid protocol");
        System.out.println("to reliably store your data in a distributed data store.");
        System.out.println("The Solid protocol is being developed at MIT, with");
        System.out.println("the project being lead by Tim Berners-Lee,");
        System.out.println("the creator of the HTTP protocol that runs");
        System.out.println("the Internet.");
        System.out.print("\n\nThis particular program is mainly meant to test the");
        System.out.println("Solid-Java Java Library, a JAR file that");
        System.out.println("provides the actual Solid implementation");
        System.out.println("You can find more information about the Solid-Java");
        System.out.println("library, including the source code, here");
        System.out.print("https://github.com/skymage23/solid-java.git\n\n");
        System.out.println("In spite of this, you can expect this program to");
        System.out.println("be a fully functioning client of the Solid protocol");

        System.out.print("\n\n");
    }

    public void showMainMenu(){
        System.out.println("Please select an option:");
        System.out.println("1.) Select a file to upload.");
        System.out.println("2.) Provide the path for a file to upload.");
        System.out.println("3.) Quit this program.");
    }

    public int acceptMainInput(){
        try {
            System.out.print("Your input: ");
            int retval = System.in.read();
            System.out.print("\n\n");
            return retval;
        } catch (java.io.IOException except){
            System.err.printf("Exception: Name %s,\nMessage: %s\n",
                    except.getClass().getName(),
                    except.getMessage()
            );
        }
    }

    public void runLoop(){
        this.showStartupPrelude();
        while (true) {
            this.showMainMenu();
            int input = this.acceptMainInput();
            switch (input) {
                case 1:
                    this.fileSelectLoop();
                    break;
                case 2:
                    this.uploadFromPath();
                    //Provide a path:
                    break;
                case 3:
                    this.quit();
                    break;
                default:
                    System.out.println("Invalid input");
                }
            }
    }

    public void fileSelectLoop(){
        IDatastoreNavigator navi = this.client.getDatastore().getNavigator();
    }

    public void uploadFromPath(){
        IDatastore store = this.client.getDatastore();

    }

    public void quit()
    throws java.io.IOException
    {
        System.out.print("Are you sure you want to quit (Y|n)?: ");
        char input = (char)System.in.read();
        if (input == 'Y'){
            System.out.print("\n\nGoodbye.\n\n");
            System.exit(0);
        }
        System.out.print("\n\n");
    }
}
