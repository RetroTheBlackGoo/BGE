package us.mudkip989.plugins.bge.game.object;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.joml.*;

public class ModelDisplay extends Object{

    private ItemDisplay entity;

    public ModelDisplay(Matrix4f transformation, World w) {
        super(transformation, w);
    }

    @Override
    public void delete() {
        super.delete();
        entity.remove();
    }

    @Override
    public void teleport(Matrix4f trans) {

    }
}
