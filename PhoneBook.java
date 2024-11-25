package ncu;
import java.util.Scanner;
import java.util.*;

public class PhoneBook {

    // Contact class to store contact details
    static class Contact {
        String name;
        String phoneNumber;
        String email;
        String address;

        Contact(String name, String phoneNumber, String email, String address) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.address = address;
        }

        @Override
        public String toString() {
            return "Name: " + name + "\nPhone Number: " + phoneNumber + "\nEmail: " + email + "\nAddress: " + address;
        }
    }

    private final Map<String, Contact> contacts = new HashMap<>();

    // Method to add a contact
    public void addContact(String name, String phoneNumber, String email, String address) {
        if (contacts.containsKey(name)) {
            System.out.println("Error: Duplicate contact. A contact with this name already exists.");
        } else if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Error: Invalid phone number. Please enter a valid phone number.");
        } else {
            contacts.put(name, new Contact(name, phoneNumber, email, address));
            System.out.println("Contact added successfully.");
        }
    }

    // Method to edit a contact
    public void editContact(String name, String phoneNumber, String email, String address) {
        Contact contact = contacts.get(name);
        if (contact == null) {
            System.out.println("Error: Contact not found.");
        } else {
            if (phoneNumber != null && !isValidPhoneNumber(phoneNumber)) {
                System.out.println("Error: Invalid phone number. Please enter a valid phone number.");
                return;
            }

            contact.phoneNumber = phoneNumber != null ? phoneNumber : contact.phoneNumber;
            contact.email = email != null ? email : contact.email;
            contact.address = address != null ? address : contact.address;
            System.out.println("Contact updated successfully.");
        }
    }

    // Method to delete a contact
    public void deleteContact(String name) {
        if (contacts.remove(name) != null) {
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Error: Contact not found.");
        }
    }

    // Method to search contacts
    public void searchContacts(String query) {
        boolean found = false;
        for (Contact contact : contacts.values()) {
            if (contact.name.contains(query) || contact.phoneNumber.contains(query) ||
                    (contact.email != null && contact.email.contains(query)) ||
                    (contact.address != null && contact.address.contains(query))) {
                System.out.println(contact);
                System.out.println("------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No contacts found for the given query.");
        }
    }

    // Method to display all contacts
    public void displayContacts(boolean alphabeticalOrder) {
        List<Contact> contactList = new ArrayList<>(contacts.values());
        if (alphabeticalOrder) {
            contactList.sort(Comparator.comparing(contact -> contact.name));
        }

        for (Contact contact : contactList) {
            System.out.println(contact);
            System.out.println("------------------------");
        }
    }

    // Utility method to validate phone numbers
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}"); // Ensures phone number is exactly 10 digits
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- PhoneBook Menu ---");
            System.out.println("1. Add Contact");
            System.out.println("2. Edit Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Search Contacts");
            System.out.println("5. Display Contacts");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    phoneBook.addContact(name, phoneNumber, email, address);
                    break;
                case 2:
                    System.out.print("Enter name of the contact to edit: ");
                    String editName = scanner.nextLine();
                    System.out.print("Enter new phone number (or press Enter to skip): ");
                    String newPhoneNumber = scanner.nextLine();
                    newPhoneNumber = newPhoneNumber.isEmpty() ? null : newPhoneNumber;
                    System.out.print("Enter new email (or press Enter to skip): ");
                    String newEmail = scanner.nextLine();
                    newEmail = newEmail.isEmpty() ? null : newEmail;
                    System.out.print("Enter new address (or press Enter to skip): ");
                    String newAddress = scanner.nextLine();
                    newAddress = newAddress.isEmpty() ? null : newAddress;
                    phoneBook.editContact(editName, newPhoneNumber, newEmail, newAddress);
                    break;
                case 3:
                    System.out.print("Enter name of the contact to delete: ");
                    String deleteName = scanner.nextLine();
                    phoneBook.deleteContact(deleteName);
                    break;
                case 4:
                    System.out.print("Enter search query: ");
                    String query = scanner.nextLine();
                    phoneBook.searchContacts(query);
                    break;
                case 5:
                    System.out.print("Display contacts in alphabetical order? (yes/no): ");
                    boolean alphabeticalOrder = scanner.nextLine().equalsIgnoreCase("yes");
                    phoneBook.displayContacts(alphabeticalOrder);
                    break;
                case 6:
                    System.out.println("Exiting PhoneBook. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}



