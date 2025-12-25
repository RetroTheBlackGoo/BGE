package us.mudkip989.plugins.bge.game.object;

import org.bukkit.*;
import org.joml.*;

import java.util.*;

public class GroupObject extends Object{
    public GroupObject(Matrix4f location, World w, UUID gid) {
        super(location, w, gid);
    }

    @Override
    public void teleport(Matrix4f trans, World w) {

    }
}
