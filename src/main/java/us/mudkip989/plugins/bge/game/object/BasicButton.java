package us.mudkip989.plugins.bge.game.object;

import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.util.Vector;
import org.joml.*;
import us.mudkip989.plugins.bge.dataTypes.*;
import us.mudkip989.plugins.bge.util.*;

import java.lang.Math;
import java.util.*;

public class BasicButton extends Object {

    public hitbox bounding;

    public BasicButton(Matrix4f transformation, World w, UUID game, hitbox hb) {
        super(transformation, w, game);
        bounding = hb;
    }

    @Override
    public void teleport(Matrix4f trans, World w) {

    }

    public RaycastResult raycastToObject(Vector3f pos, Vector3f dir, World dim){
        if(!dim.equals(world)){
            return null;
        }
        //convert to transformation
        Matrix4f rayTransform = TransformUtils.getTransform(new Location(dim, pos.x, pos.y, pos.z).setDirection(new Vector(dir.x, dir.y, dir.z)));
        Matrix4f result = new Matrix4f();
        Matrix4f inverse = transform.invert();
        inverse.mul(rayTransform, result);
        //get raycast ray as transform relative to object
        Vector3f newStartPos = new Vector3f();
        Vector3f newDir = new Vector3f();
        AxisAngle4f intermediate = new AxisAngle4f();
        //set new raycast vars
        result.getTranslation(newStartPos);
        result.getRotation(intermediate);
        intermediate.transform(new Vector3f(0, 0, 1), newDir);
        //define bounds
        float minX = bounding.center().x - bounding.size().x;
        float maxX = bounding.center().x + bounding.size().x;
        float minY = bounding.center().y - bounding.size().y;
        float maxY = bounding.center().y + bounding.size().y;
        float minZ = bounding.center().z - bounding.size().z;
        float maxZ = bounding.center().z + bounding.size().z;

        // r.dir is unit direction vector of ray
        Vector3f dirfrac = new Vector3f();

        dirfrac.x = 1.0f / newDir.x;
        dirfrac.y = 1.0f / newDir.y;
        dirfrac.z = 1.0f / newDir.z;
        // lb is the corner of AABB with minimal coordinates - left bottom, rt is maximal corner
        // r.org is origin of ray
        float t1 = (minX - newStartPos.x)*dirfrac.x;
        float t2 = (maxX - newStartPos.x)*dirfrac.x;
        float t3 = (minY - newStartPos.y)*dirfrac.y;
        float t4 = (maxY - newStartPos.y)*dirfrac.y;
        float t5 = (minZ - newStartPos.z)*dirfrac.z;
        float t6 = (maxZ - newStartPos.z)*dirfrac.z;

        float tmin = Math.max(Math.max(Math.min(t1, t2), Math.min(t3, t4)), Math.min(t5, t6));
        float tmax = Math.min(Math.min(Math.max(t1, t2), Math.max(t3, t4)), Math.max(t5, t6));
        float t = 0;
        // if tmax < 0, ray (line) is intersecting AABB, but the whole AABB is behind us
        if (tmax < 0)
        {
            t = tmax;
        //            return false;
        }

        // if tmin > tmax, ray doesn't intersect AABB
        if (tmin > tmax)
        {
            t = tmax;
        //            return false;
        }

        t = tmin;
        //        return true;

        //Use t for getting end location and distance
        Vector3f endPoint = pos.add(dir.mul(t, new Vector3f()), new Vector3f());



        return new RaycastResult(endPoint, dir.mul(t, new Vector3f()).length());
    }

}
