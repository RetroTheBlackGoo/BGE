package us.mudkip989.plugins.bge.game.object;

import org.bukkit.*;
import org.bukkit.util.*;
import org.joml.*;
import us.mudkip989.plugins.bge.game.object.enums.*;

import java.util.*;

public abstract class Object {
    private Matrix4f transform;
    private World world;
    private Object parent;
    private List<Object> children;
    protected UUID uuid;
    private UUID gameId;

    public Object(Matrix4f location, World w, UUID gid) {
        parent = null;
        transform = location;
        uuid = UUID.randomUUID();
        children = new ArrayList<>();
        world = w;
        gameId = gid;
    }

    public Object(Matrix4f location, World w, Object obj) {
        if (obj != null){
            parent = obj;
            parent.addChild(this);
        }
        transform = location;
        uuid = UUID.randomUUID();
        children = new ArrayList<>();
        world = w;
    }



    //Grab Transform from parent and apply to current
    public Matrix4f getWorldSpaceTransform() {
        Matrix4f ptran;
        Matrix4f localtran = new Matrix4f();
        if(parent != null) {
            ptran = parent.getWorldSpaceTransform();
        } else {
            ptran = new Matrix4f().scale(1);
        }

        localtran.translate(transform.getTranslation(new Vector3f()));
        localtran.rotation(transform.getRotation(new AxisAngle4f()));
        localtran.mul(ptran);

        return localtran;
    }

    public Matrix4f getWorldSpaceTransform(Matrix4f ptran) {
        Matrix4f localtran = new Matrix4f();
        localtran.translate(transform.getTranslation(new Vector3f()));
        localtran.rotation(transform.getRotation(new AxisAngle4f()));
        localtran.mul(ptran);

        return localtran;
    }

    public abstract void teleport(Matrix4f trans, World w);

    public void delete() {
        setParent(null);
        children.forEach(Object::delete);
    }

    public void update() {
        Matrix4f trans = getWorldSpaceTransform();
        teleport(trans, world);
        children.forEach(child -> child.update(trans, world));
        //Pull info apply to this
    }

    public void update(Matrix4f trans, World w) {
        Matrix4f trans2 = getWorldSpaceTransform(trans);
        teleport(trans2, w);
        children.forEach(child -> child.update(trans2, w));
        //parent force updating this
    }

    public void setParent(Object obj) {
        if(parent != null){
            parent.removeChild(this);
        }

        parent = obj;
        if(parent != null){
            parent.addChild(this);
        }
    }

    protected void addChild(Object obj) {
        if(obj != null) {
            children.add(obj);
        }
    }

    protected void removeChild(Object obj) {
        if(obj != null) {
            children.remove(obj);
        }
    }

}
