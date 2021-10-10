package AlgoritmiaI.practica2;

import AlgoritmiaI.datastructures.linkedlist.LinkedList;

public final class AddressBook {
    private final LinkedList<Contact> contacts;

    public AddressBook () {
        this.contacts = new LinkedList<>();
    }

    public void addContact(Contact contact) {
        this.contacts.insert(contact);
    }

    public LinkedList<String> getContactNames () {
        LinkedList<String> contactNames = new LinkedList<>();

        contacts.forEach(contact -> contactNames.insert(formatContactName(contact)));

        return contactNames;
    }

    public Contact getContact(String contactName){
        Contact contactFound = null;

        for (Contact actualContact : contacts) {
            if(formatContactName(actualContact).equalsIgnoreCase(contactName)){
                contactFound = actualContact;
            }
        }

        return contactFound;
    }

    private String formatContactName (Contact contact) {
        return contact.getLastName() + ", " + contact.getFirstName();
    }

    public String exportToCsv(){
        StringBuilder csvStringBuilder = new StringBuilder();

        for (Contact actualContact : contacts) {
            csvStringBuilder.append(actualContact.getFirstName()).append(",")
                    .append(actualContact.getLastName()).append(",")
                    .append(actualContact.getCompany()).append(",")
                    .append(actualContact.getAddress()).append(",")
                    .append(actualContact.getEmail()).append("\n");
        }

        return csvStringBuilder.toString();
    }
}
