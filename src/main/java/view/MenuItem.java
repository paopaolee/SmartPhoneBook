package view;

public class MenuItem {
    private final String key;
    private final String name;

    public MenuItem(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }
}
