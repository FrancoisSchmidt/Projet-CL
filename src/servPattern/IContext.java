package servPattern;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;

public interface IContext {
    public void createGroupChat(String groupName);
    public void addUserToGroupChat(String userName, String groupName);
    public void removeUserOfGroupChat(String userName, String groupName);
    public void deleteGroupChat(String groupName);
    public void addConnectedUser(String userName, BufferedReader socIn, PrintStream socOut);
    public void removeConnectedUser(String userName);
    public BufferedReader getUserSocIn(String userName);
    public PrintStream getUserSocOut(String userName);
    public ArrayList getConnectedUserList();
    public void refreshUserList();
    public void sendMessageToUser(String fromUser, String userName, String Text);
    public void sendMessageToAll(String fromUser, String Text);
    public void sendMessageToGroup(String fromUser, String groupName,String Text);
    public ArrayList getGroupList();
}
