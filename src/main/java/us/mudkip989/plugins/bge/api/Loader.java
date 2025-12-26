package us.mudkip989.plugins.bge.api;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class Loader {
    public static void main() throws IOException {
        Path dir = Paths.get("Games");

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.jar")) {
            for (Path jarPath : stream) {

                try (JarFile jar = new JarFile(jarPath.toFile())) {
                    Manifest manifest = jar.getManifest();
                    if (manifest == null) continue;

                    String mainClassName = manifest
                            .getMainAttributes()
                            .getValue("Main-Class");

                    if (mainClassName == null) continue;

                    URLClassLoader loader = new URLClassLoader(
                            new URL[]{ jarPath.toUri().toURL() },
                            ClassLoader.getSystemClassLoader()
                    );

                    Class<?> mainClass = Class.forName(mainClassName, true, loader);

                    Method main = mainClass.getMethod("main", String[].class);

                    System.out.println("Running " + jarPath.getFileName());
                    main.invoke(null, (Object) new String[0]);
                } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                         IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
