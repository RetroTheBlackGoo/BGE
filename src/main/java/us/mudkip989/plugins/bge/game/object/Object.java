package us.mudkip989.plugins.bge.game.object;

import org.bukkit.*;
import org.bukkit.util.*;
import org.joml.*;
import us.mudkip989.plugins.bge.game.object.enums.*;

import java.util.*;

public abstract class Object {
    private Matrix4f transform;
    private Object parent;
    private List<Object> children;


    public Matrix4f getWorldSpaceTransform(){
        Matrix4f ptran;
        Matrix4f localtran = new Matrix4f();
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

        localtran.translate(transform.getTranslation(new Vector3f()));
        localtran.rotation(transform.getRotation(new AxisAngle4f()));


        return null;
    }

    public void update() {

        //Pull info apply to this
    }

    public void update(Location loc, Transformation trans){
        //parent force updating this
    }
}
