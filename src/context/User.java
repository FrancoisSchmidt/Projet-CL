package context;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.nio.Buffer;

public class User {
    private String name;
    private BufferedReader socIn;
    private PrintStream socOut;

    public User(String Name, BufferedReader SocIn, PrintStream SocOut){
        this.socIn = SocIn;
        this.socOut = SocOut;
        this.name = Name;
    }

    public String getName(){
        return this.name;
    }

    public BufferedReader getSocIn(){
        return this.socIn;
    }

    public PrintStream getSocOut(){
        return this.socOut;
    }
}
