package view;

import beans.Contact;
import beans.Gender;
import org.greenrobot.eventbus.EventBus;
import utils.ContactPrinter;

import java.util.ArrayList;
import java.util.List;

public class ContactView {
    private final ContactMenu menu;
    private final UserInput userInput;
    private final List<MenuItem> menuList;
    public ContactView() {
        this.menu = new ContactMenu();
        this.userInput = new UserInput();
        this.menuList = new ArrayList<>();
        menuList.add(new MenuItem("1", "添加联系人"));
        menuList.add(new MenuItem("2", "查找联系人"));
        menuList.add(new MenuItem("3", "删除指定联系人"));
        menuList.add(new MenuItem("4", "删除全部联系人"));
        menuList.add(new MenuItem("5", "按名称排序"));
        menuList.add(new MenuItem("6", "退出系统"));
    }
    public void renderMainMenu() {
        menu.render(menuList);
        MenuItem selectedMenuItem = userInput.getSelectedMenuItem(menuList);
        // 发布菜单选择事件
        EventBus.getDefault().post(selectedMenuItem);
    }
    public Contact addContact() {
        String name = userInput.getName();
        String aliasName = userInput.getAliasName();
        String telephone = userInput.getTelephone();
        int age = userInput.getAge();
        Gender gender = userInput.getGender();
        Contact contact = new Contact();
        contact.setName(name);
        contact.setAlias(aliasName);
        contact.setTelephone(telephone);
        contact.setAge(age);
        contact.setGender(gender);
        return contact;
    }
    public UserInput getUserInput() {
        return this.userInput;
    }
    public void printContacts(List<Contact> contactList) {
        if (contactList.isEmpty()) {
            System.out.println("未添加联系人");
            return;
        }
        ContactPrinter.printContactList(contactList);
    }
    public void printContact(Contact contact) {
        if (contact == null) {
            System.out.println("未查询到此联系人");
            return;
        }
        ContactPrinter.printContact(contact);
    }
}

