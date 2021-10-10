package AlgoritmiaI.practica2;

import AlgoritmiaI.datastructures.linkedlist.LinkedList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;


public final class AddressBookTest {
    private AddressBook addressBook;

    @Before
    public void setUp() {
        addressBook = new AddressBook();
    }

    @Test
    public void testAddressBook() {
        final LinkedList<String> names = addressBook.getContactNames();
        final Contact contact = addressBook.getContact("");

        Assert.assertEquals(0, names.size());
        assertNull(contact);
    }

    @Test
    public void testGetContactNames() {
        fillContacts(addressBook);

        final LinkedList<String> names = addressBook.getContactNames();
        final LinkedList<String> expected = new LinkedList<>();
        expected.insert("Appleseed, John");
        expected.insert("Doe, John");
        expected.insert("Doe, Jane");

        assertEquals(expected.size(), names.size());

        Iterator<String> namesIterator = names.iterator();
        Iterator<String> expectedIterator = expected.iterator();

        while (namesIterator.hasNext() && expectedIterator.hasNext()){
            assertEquals(expectedIterator.next(), namesIterator.next());
        }

    }

    @Test
    public void testGetContact() {
        fillContacts(addressBook);

        assertNotNull(addressBook.getContact("Appleseed, John"));
    }

    @Test
    public void exportContactsAsCsv(){
        fillContacts(addressBook);

        String expectedCsv = String.join("\n", new String[]{
                "John,Appleseed,Uneatlantico,Calle Isabel Torres 21,john.appleseed@uneatlantico.es",
                "John,Doe,Ingram Micro,Plaza Manuel Llano,john.doe@ingrammicro.com",
                "Jane,Doe,Oracle,Calle Albert Einstein 1,jane.doe@oracle.com",
        });
        String csv = addressBook.exportToCsv();

        Assert.assertEquals(expectedCsv + "\n", csv);
    }

    private void fillContacts(AddressBook addressBook) {
        final Contact johnAppleseed = new Contact();
        johnAppleseed.setFirstName("John");
        johnAppleseed.setLastName("Appleseed");
        johnAppleseed.setCompany("Uneatlantico");
        johnAppleseed.setAddress("Calle Isabel Torres 21");
        johnAppleseed.setEmail("john.appleseed@uneatlantico.es");
        addressBook.addContact(johnAppleseed);

        final Contact johnDoe = new Contact();
        johnDoe.setFirstName("John");
        johnDoe.setLastName("Doe");
        johnDoe.setCompany("Ingram Micro");
        johnDoe.setAddress("Plaza Manuel Llano");
        johnDoe.setEmail("john.doe@ingrammicro.com");
        addressBook.addContact(johnDoe);

        final Contact janeDoe = new Contact();
        janeDoe.setFirstName("Jane");
        janeDoe.setLastName("Doe");
        janeDoe.setCompany("Oracle");
        janeDoe.setAddress("Calle Albert Einstein 1");
        janeDoe.setEmail("jane.doe@oracle.com");
        addressBook.addContact(janeDoe);
    }
}
