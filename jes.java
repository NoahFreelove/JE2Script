import JE.Objects.GameObject;
import org.joml.Vector2f;
public void start(){
System.out.println(getAttachedObject().toString());
getAttachedObject().getTransform().setPosition(new Vector2f(0,0,0));
Vector2f a=getAttachedObject().getTransform().position();
GameObject go=new GameObject();
System.out.println(go.getActive());
}
