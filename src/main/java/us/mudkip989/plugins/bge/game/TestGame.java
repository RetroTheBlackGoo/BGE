package us.mudkip989.plugins.bge.game;

import org.bukkit.entity.*;
import org.bukkit.scheduler.*;
import us.mudkip989.plugins.bge.*;

import java.util.*;

public class TestGame extends Game{

    public ArrayList<Player> players = new ArrayList<>();

    //Rock Paper Scissors?
    public TestGame() {
        super();
    }



    @Override
    public BukkitTask runGameTask() {

        return new BukkitRunnable() {
            @Override
            public void run() {

            }
        }.runTaskTimerAsynchronously(BGE.instance, 1, 1);
    }


}
