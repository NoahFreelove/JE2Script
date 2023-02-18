package org.JEScript.Compiler;
import JE.Utility.Friend;
import JE.Objects.Scripts.Base.Script;
import org.JEScript.Main;


public class MessageRecorder extends Script{
    private String[] allMessages = new String[1];
    public MessageRecorder(){
        allMessages[0] = "Hello, World!";
    }
    @Override public void start(){
        System.out.println("start");
    }
    public void onMessageSent(String msg){
        allMessages[0] = msg;
    }
    public final Friend printRecentMessage = new Friend(Main.class.getName(), args-> {
        System.out.println(allMessages[0]);
    });
    public final Friend getAllMessages = new Friend(Compiler.class.getName(), allMessages);
}
