package utils;

import beans.Contact;

import java.util.List;

public class ContactPrinter {
    private ContactPrinter() {}
    public static void printContactList(List<Contact> contactList) {
        contactList.forEach(contact -> {
            StringBuilder sb = new StringBuilder();
            sb.append(contact.getId()).append("#").append("\t");
            sb.append(contact.getName()).append("#").append("\t");
            sb.append(contact.getAlias()).append("#").append("\t");
            sb.append(contact.getAge()).append("#").append("\t");
            sb.append(contact.getGender()).append("#").append("\t");
            sb.append(contact.getTelephone()).append("#").append("\t");
            System.out.println(sb);
        });
    }

    public static void printContact (Contact contact) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(contact.getId()).append("#").append("\t");
        sb.append("姓名: ").append(contact.getName()).append("#").append("\t");
        sb.append("昵称: ").append(contact.getAlias()).append("#").append("\t");
        sb.append("年龄: ").append(contact.getAge()).append("#").append("\t");
        sb.append("性别: ").append(contact.getGender()).append("#").append("\t");
        sb.append("手机号: ").append(contact.getTelephone()).append("#").append("\t");
        System.out.println(sb);
    }
}
