package model;

import beans.Contact;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ContactModel {
    private final List<Contact> contactList = new ArrayList<>(100);
    public void  add(Contact contact) {
        contactList.add(contact);
    }

    public List<Contact> getAll() {
        return contactList;
    }

    public List<Contact> getByName(String name) {
       return contactList
               .stream()
               .filter(contact -> contact.getName().contains(name) || contact.getAlias().contains(name))
               .collect(Collectors.toList());
    }
    public List<Contact> searchByKeyword(String keyword) {
        return  contactList.stream().filter(contact -> {
            boolean likeName = contact.getName().contains(keyword);
            boolean likeAlias = contact.getAlias().contains(keyword);
            boolean likeTelephone = contact.getTelephone().contains(keyword);
            return  likeName || likeAlias || likeTelephone;
        }).collect(Collectors.toList());
    }

    public List<Contact> getByTelephoneNum(String keyword) {
        return contactList
                .stream()
                .filter(contact -> {
                    String telephone = contact.getTelephone();
                    return telephone.contains(keyword);
                })
                .collect(Collectors.toList());
    }

    public void deleteById(int id) {
        contactList.removeIf(contact -> contact.getId() == id);
    }
    public void deleteAll() {
        contactList.clear();
    }
    public void orderByName() {
        contactList.sort(Comparator.comparing(Contact::getName)
                .thenComparing(Contact::getAlias)
        );
    }


}
