package view;

import java.util.List;

public class ContactMenu implements MenuRender {
    public void render(List<MenuItem> menuItemList) {
        System.out.println("**************************");
        menuItemList
                .forEach(item -> System.out.println("\t" + item.getKey() + " " + item.getName() + "\t"));
        System.out.println("**************************");
    }
}
