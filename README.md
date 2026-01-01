
# What Is This?

Board Game Engine (or BGE) is a Paper Minecraft plugin meant to bring Board Game and Card Games to Minecraft without the need to install any additional mods. However, making all games and/or a custom scripting language is too much for me to handle. That is where you come in! 

BGE is mostly an API that can automatically run games through the use of addons, making the game selection very customizable to fit your server's needs. As it is an API, all addons are capable of importing and using their choice of Java libraries to enhance their games within Minecraft limits.

# What Version Do I Use?

Currently, the plugin has only been tested on 1.21.8. Later versions may be supported at a later time if they do not already work with the current version of the plugin.

# What Games Are There?

Currently, there are no existing addons as this plugin has only been made publicly available recently.

# What Other Features Are There?

- Custom Object System allowing for Godot style object parenting
  - Objects that are children of other inherit the transformations of all the parents, sequentially
- Make custom Objects by extending the base Object, allowing for flexibility for newer Minecraft and Game features.
- Asynchronous Loops
  - Reduces server tick time by offloading all tasks in a game to not halt the main thread unless absolutely necessary.
  - For things that require the main thread, a Concurrent Queue is cycled through every tick to handle things like entity teleports.
- A Game Registry that may be expanded upon at a later point to allow for better management features.
- Game options that can be included after the initial create command to allow for modifying the base settings a certain game has.
- Addon Loader that runs both at startup and with `/bge reload`.
  - Running `/bge disable` runs the reload command without starting any addons. This allows for emergency disabling of a broken or problematic addon.
    - It should be noted that the main reason this was added was in hopes to allow people trying to delete the addon on a Windows machine could do so with being stopped by the addon currently being open in the server. This has not been tested, but my hope is that it is fixed by this addition.


## Planned Features 

- Resource pack support
  - You can currently just load the pack manually, either by server properties or by having the other player load the pack through their settings.
- Permission Nodes
  - I am sorry, I have not touched those yet. This will come soon... Hopefully.
- Game Auto-Load
  - By the use of a config file, you will be able to have specific games load with specific settings at specific locations. Especially great since game instances get deleted whenever the plugin loads or reloads. Better in the case of occasional server crashes and restarts.
- Game Unloaded Pausing
  - Pause the game loop whenever there are no players nearby. This can allow for a game to continue running in a reduced state until players are close enough to interact.
- Config File
  - While there is technically already a config file, it serves no purpose at this moment as my main focus lately has been getting something that works.

# How Can I Make A Game?

1. ### Create a Java Project

Any Editor is fine as long as it supports Java 21 and compiling.

2. ### Add the plugin dependency

There is no Maven Repository at this time, so you will have to add it the old-fashioned way. For best results, Always use the latest plugin Jar as there is it serves as the API itself.
You will also need to include Paper as another dependency as the plugin still uses Paper in the Game and Object classes.

3. ### Create a new class extending `us.mudkip989.plugins.bge.api.BGEAddon`

This is how you will initialize the addon. Without this, BGE will not be able to add your games to the list.

4. ### Create a new class extending `us.mudkip989.plugins.bge.game.Game`

This is your Game itself. You can do whatever you want in this file. See the builtin examples for how to use.

5. ### Register your Game/s in your Addon class

Remember to run `BGE.registerGame(String id, Class<Game> game);` in the `onAddonLoad` function so your games can be used. Failure to do so will result in the Games in your addon being unavailable.

6. ### Compile and test

Once you are ready to test, you can compile your project and add it to the addons folder on the server located in `plugins/BGE/` and run `/bge reload` so that the plugin locates your addon.


# Contributing

This project is open source. You are free to privately modify the plugin to your wishes. If, however, you wish to help the main plugin out, you can make a Pull Request containing your edits, describing what it does and why you think I should add it to the main plugin. I will leave credits for each pull request in the commit that contains the change (and maybe in the code comments).
