package context;

import servPattern.IContext;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;

public class LeContext implements IContext {
    private HashMap<String, User> connectedUserList = new HashMap<>();

    private HashMap<String, ArrayList<String>> groupChatList = new HashMap<>();

    public LeContext(){
        this.groupChatList.put("General",new ArrayList<String>());
    }

    public void createGroupChat(String groupName){
        this.groupChatList.put(groupName, new ArrayList<String>());
    }

    public void addUserToGroupChat(String userName, String groupName){
        if (!(this.groupChatList.get(groupName).contains(userName))) {
            this.groupChatList.get(groupName).add(userName);
        }
    }

    public void removeUserOfGroupChat(String userName, String groupName){
        if (this.groupChatList.get(groupName).contains(userName)) {
            this.groupChatList.get(groupName).remove(this.groupChatList.get(groupName).indexOf(userName));
        }
    }

    public void deleteGroupChat(String groupName){
        this.groupChatList.get(groupName).clear();
        this.groupChatList.remove(groupName);
    }

    public void addConnectedUser(String userName, BufferedReader socIn, PrintStream socOut){
        User user = new User(userName, socIn, socOut);
        this.connectedUserList.put(userName, user);
        this.addUserToGroupChat(userName, "General");
    }

    public void removeConnectedUser(String userName){
        this.connectedUserList.remove(userName);
        this.removeUserOfGroupChat(userName, "General");
    }

    public BufferedReader getUserSocIn(String userName){
        return this.connectedUserList.get("userName").getSocIn();
    }

    public PrintStream getUserSocOut(String userName){
        return this.connectedUserList.get("userName").getSocOut();
    }

    public ArrayList getConnectedUserList(){
        return new ArrayList<String>(this.connectedUserList.keySet());
    }

    public void sendMessageToUser(String fromUser, String userName, String Text){
        String message = fromUser + " : " + Text;
        this.getUserSocOut(fromUser).println(message);
        this.getUserSocOut(userName).println(message);
    }

    public void sendMessageToAll(String fromUser, String Text){
        ArrayList listDest = this.groupChatList.get("General");
        String message = fromUser + " : " + Text;
        PrintStream os;
        for (int i = 0 ; i < listDest.size(); i++) {
            os = this.getUserSocOut(listDest.get(i))
            os.println(message);
        }
    }

    public void sendMessageToGroup(String fromUser, String groupName,String Text){
        String message = fromUser + " : " + Text;
    }
}
