package appGui_Tab;

public class MessageUI {
    public String From;
    public String msg;
    public MessageUI(String from, String msg) {
        this.From = from;
        this.msg = msg;
    }
    public String getFrom() {return From;}
    public String getMsg() {return msg;}
}
