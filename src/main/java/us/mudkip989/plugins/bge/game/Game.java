package us.mudkip989.plugins.bge.game;

import org.bukkit.scheduler.*;
import us.mudkip989.plugins.bge.game.object.Object;

import java.util.*;

public abstract class Game {

    public UUID uuid;
    public BukkitTask task;
    public ArrayList<Object> objectTracker = new ArrayList<>();

    public Game(){
        uuid = UUID.randomUUID();
        task = runGameTask();
    }

    public Game(String oldUUID){
        uuid = UUID.fromString(oldUUID);
        task = runGameTask();
    }

    public void createObject(Class<Object> call){

    }

    public abstract BukkitTask runGameTask();

}
