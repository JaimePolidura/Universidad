package es.jaime.entregas.ejercicio2;

import es.jaime.datastructures.linkedlist.MyLinkedList;

public final class AddressBook {
    private final MyLinkedList<Contact> contacts;

    public AddressBook () {
        this.contacts = new MyLinkedList<>();
    }

    public void addContact(Contact contact) {
        this.contacts.insert(contact);
    }

    public MyLinkedList<String> getContactNames () {
        MyLinkedList<String> contactNames = new MyLinkedList<>();

        for (Contact contact : contacts) {
            contactNames.insert(contact.getLastName() + ", " + contact.getFirstName());
        }

        //contacts.forEach(contact -> contactNames.insert(formatContactName(contact)));
        return contactNames;
    }

    public Contact getContact(String contactName){
        for (Contact actualContact : contacts) {
            if(formatContactName(actualContact).equalsIgnoreCase(contactName)){
                return actualContact;
            }
        }

        return null;
    }

    private String formatContactName (Contact contact) {
        return contact.getLastName() + ", " + contact.getFirstName();
    }

    public String exportToCsv(){
        String csv = "";

        for (Contact contact : contacts) {
            csv = csv + contact.getFirstName() + ","
                    + contact.getLastName() + ","
                    + contact.getCompany() + ","
                    + contact.getAddress() + ","
                    + contact.getEmail() + "\n";
        }

        return csv;
    }
}
