package us.mudkip989.plugins.bge.game.processors;

import javax.annotation.*;

public class CommandOptionParser {


    public static String parseOptionString(String options, String option){

        String[] vals = options.split(" ");

        String val = "";

        for(String thing: vals){
            if(thing.startsWith("-" + option)){
                val = thing.split("=", 2)[1];
            }
        }

        return val;
    }

    public static Integer parseOptionInt(String options, String option){

        String[] vals = options.split(" ");

        String val = "";

        for(String thing: vals){
            if(thing.startsWith("-" + option)){
                val = thing.split("=", 2)[1];
            }
        }

        if(val == ""||val == null){
            return 0;
        }

        return Integer.parseInt(val);
    }


    public static Float parseOptionFloat(String options, String option){

        String[] vals = options.split(" ");

        String val = "";

        for(String thing: vals){
            if(thing.startsWith("-" + option)){
                val = thing.split("=", 2)[1];
            }
        }

        if(val == ""||val == null){
            return 0f;
        }

        return Float.parseFloat(val);
    }


}
