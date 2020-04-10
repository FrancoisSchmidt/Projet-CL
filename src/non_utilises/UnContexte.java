package non_utilises;

import servPattern.IContext;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;

public class UnContexte implements IContext {

    public void createGroupChat(String groupName) {

    }

    public void addUserToGroupChat(String userName, String groupName) {

    }

    public void removeUserOfGroupChat(String userName, String groupName) {

    }

    public void deleteGroupChat(String groupName) {

    }

    public void addConnectedUser(String userName, BufferedReader socIn, PrintStream socOut) {

    }

    public void removeConnectedUser(String userName) {

    }

    public BufferedReader getUserSocIn(String userName) {
        return null;
    }

    public PrintStream getUserSocOut(String userName) {
        return null;
    }

    public ArrayList getConnectedUserList() {
        return null;
    }

    public void sendMessageToUser(String fromUser, String userName, String Text) {

    }

    public void sendMessageToAll(String fromUser, String Text) {

    }

    public void sendMessageToGroup(String fromUser, String groupName, String Text) {

    }

    public ArrayList getGroupList() {
        return null;
    }

    public void refreshUserList(){

    }

    public void inviteToGroup(String author, String grpName, String invited){

    }

    public ArrayList getGroupMemberList(String grpName) {
        return null;
    }
}
