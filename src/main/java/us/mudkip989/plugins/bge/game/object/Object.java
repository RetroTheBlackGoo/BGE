package us.mudkip989.plugins.bge.game.object;

import org.bukkit.*;
import org.bukkit.util.*;
import org.joml.*;
import us.mudkip989.plugins.bge.game.object.enums.*;

import java.util.*;

public abstract class Object {
    private Location location;
    private Matrix4f transform;
    private Object parent;
    private List<Object> children;

    /*
    ----------
    Possible Transform/Teleport Combos

     - Parent Only
     - Parent and Children(Recursive)

     */

//    public void teleport(Location loc){
//        switch(teleportMode){
//            case WORLD -> {
//                location = loc;
//            }
//            case PARENT -> {
//                if (parent == null) {
//                    location = loc;
//                } else {
//                    location = parent.getLocation().add(loc.toVector());
//                }
//
//            }
//            case LOCAL -> {
//
//            }
//        }
//
//        if(children != null){
//            children.forEach((ob) -> {if (ob.teleportMode == TeleportMode.PARENT) ob.update();});
//        }
//    }


    public Location getLocation() {return location.clone();

    }

    public Matrix4f getWorldSpaceTransform(){
        Matrix4f ptran;
        if(parent != null) {
            ptran = parent.getWorldSpaceTransform();
        } else {
            ptran = new Matrix4f().scale(1);
        }
        Vector3f translation = new Vector3f();
        Vector3f scale = new Vector3f();
        AxisAngle4f rotation = new AxisAngle4f();

        transform.getTranslation(translation);
        transform.getScale(scale);
        transform.getRotation(rotation);



        return null;
    }

    public Location getWorldSpaceLocation() {
        return null;
    }

    public void update() {

        //Pull info apply to this
    }

    public void update(Location loc, Transformation trans){
        //parent force updating this
    }
}
