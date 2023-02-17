import JE.Utility.Friend;
import JE.Objects.Scripts.Base.Script;

public class TestScript extends Script {
    @Override public void start(){
        System.out.println("Hello World");
    }
    public final Friend method = new Friend(Script.class.getName(), args-> {int a = (int)args[0];boolean b = (boolean)args[1];
        System.out.println(a);
        int i = 0;
        i++;
        System.out.print("Hi");
        System.out.print("\nBye");
    });
}
