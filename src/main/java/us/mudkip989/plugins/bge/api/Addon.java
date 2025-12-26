package us.mudkip989.plugins.bge.api;

public interface Addon {
    String name();
    String version();
    Integer BGEVersion();
    void load();
}
