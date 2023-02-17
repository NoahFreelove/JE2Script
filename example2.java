import JE.Objects.GameObject;
import JE.Objects.Scripts.Base.Script;

public class CoolScript extends Script {
    @Override public void update(){
        GameObject go = new GameObject();
        System.out.println(go.toString());
    }
}
