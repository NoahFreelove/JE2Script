import JE.Manager;
import JE.Objects.GameObject;
import org.joml.Vector2f;
public void start(){
getAttachedObject().getTransform().setPosition(new Vector2f(0,0,0));
System.out.println(new GameObject().toString());
Manager.activeScene().world.gameObjects.get(0).getTransform().setPosition(getAttachedObject().getTransform().position());
}
