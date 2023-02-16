package controller;

import beans.Contact;
import model.ContactModel;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import utils.ContactPrinter;
import view.ContactView;
import view.MenuItem;
import view.UserInput;

import java.util.List;

public class ContactController {
    private final ContactView contactView;
    private final ContactModel contactModel;
    public ContactController () {
        this.contactView = new ContactView();
        this.contactModel = new ContactModel();
    }
    public void start () {
        System.out.println("\t欢迎使用智能电话簿\t");
        contactView.renderMainMenu();
    }
    private void exit() {
        EventBus.getDefault().unregister(this);
        System.out.println("\t谢谢使用\t");
        System.exit(0);
    }
    private void addContact() {
        Contact contact = contactView.addContact();
        contactModel.add(contact);
        System.out.println("添加联系人成功");
        contactView.renderMainMenu();
    }
    private void searchContact() {
        String keyword = contactView.getUserInput().getSearchKeyword();
        List<Contact> contacts = contactModel.searchByKeyword(keyword);
        System.out.println("搜索结果：");
        contactView.printContacts(contacts);
        contactView.renderMainMenu();
    }
    private void deleteContactById() {
        UserInput userInput = contactView.getUserInput();
        List<Contact> contacts = contactModel.getAll();
        ContactPrinter.printContactList(contacts);
        Contact selectedContact = userInput.getSelectedContact(contacts);
        contactView.printContact(selectedContact);
        boolean isConfirm = userInput.isConfirm("确定删除此联系人？");
        if (isConfirm) {
            contacts.removeIf(contact -> contact.getId() == selectedContact.getId());
            System.out.println("删除成功");
        }
        contactView.renderMainMenu();
    }
    private void deleteAllContacts() {
        boolean isConfirm = contactView.getUserInput().isConfirm("确定删除所有联系人？");
        if (isConfirm) {
            contactModel.deleteAll();
            System.out.println("删除成功");
        }
        contactView.renderMainMenu();
    }
    private void orderContacts() {
        contactModel.orderByName();
        List<Contact> contactList = contactModel.getAll();
        System.out.println("按名称排序：");
        contactView.printContacts(contactList);
    }

    /**
     * 订阅菜单选择事件
     * @param menuItem 选择的菜单
     */
    @Subscribe
    public void handleMenuItemSelect(MenuItem menuItem) {
        System.out.println("\t" + menuItem.getName() + "页面");
        switch (menuItem.getKey()) {
            case "1":
                addContact();
                break;
            case  "2":
                searchContact();
                break;
            case "3":
                deleteContactById();
                break;
            case "4":
                deleteAllContacts();
                break;
            case "5":
                orderContacts();
                break;
            case "6":
                exit();
        }
    }

}
