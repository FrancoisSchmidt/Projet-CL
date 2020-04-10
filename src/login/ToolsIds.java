package login;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class ToolsIds {

    private String filename = "src/login/ids";

    public void setFilename(String newFilname){
        this.filename = newFilname;
    }

    public String getFilename(){

        return this.filename;
    }

    private HashMap<String,String> dictLoggins = new HashMap<String,String>();

    private void closeLogFile() throws Exception{
        File file = new File(filename);
        FileOutputStream f = new FileOutputStream(file);
        ObjectOutputStream s = new ObjectOutputStream(f);
        s.writeObject(this.dictLoggins);
        s.flush();
        f.close();
    }

    private void openLogFile() throws Exception {

        if(Files.notExists(Paths.get(filename))){
            this.closeLogFile();
        }
        File file = new File(filename);
        FileInputStream f = new FileInputStream(file);
        ObjectInputStream s = new ObjectInputStream(f);
        this.dictLoggins = (HashMap<String, String>) s.readObject();
        s.close();
        f.close();
    }


    public boolean isUser(String userName) throws Exception{
        boolean state=false;
        this.openLogFile();
        if (dictLoggins.get(userName)!=null){
            state=true;
        }
        return state;
    }

    public void addUser(String userName, String passWord) throws Exception{
        this.openLogFile();
        this.dictLoggins.put(userName, passWord);
        this.closeLogFile();
    }

    public boolean checkMDP(String userName, String passWord) throws Exception {
        this.openLogFile();
        boolean state = false;
        String MDP = this.dictLoggins.get(userName);
        if (passWord.equals(MDP)) {
            state = true;
        }
        return state;
    }

    public void deleteUser(String userName, String passWord) throws Exception{
        if (this.checkMDP(userName,passWord)){
            this.openLogFile();
            this.dictLoggins.remove(userName);
            this.closeLogFile();
        }
    }

    public void test() throws Exception{
        System.out.println("Is TestUser a user ?");
        System.out.println(this.isUser("TestUser"));
        System.out.println("\nRegistering TestUser, MDP : TestMDP1");
        this.addUser("TestUser", "TestMDP1");
        System.out.println("\nIs TestUser a user now ?");
        System.out.println(this.isUser("TestUser"));
        System.out.println("\nIs TestUser's passWord TestMDP1 ?");
        System.out.println(this.checkMDP("TestUser", "TestMDP1"));
        System.out.println("\nIs TestUser's passWord TestMDP2 ?");
        System.out.println(this.checkMDP("TestUser", "TestMDP2"));
        System.out.println("\nUnregistering TestUser");
        this.deleteUser("TestUser", "TestMDP1");
        System.out.println("\nIs TestUser a user now ?");
        System.out.println(this.isUser("TestUser"));

    }
}
