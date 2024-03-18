import java.util.Scanner;

class Contact {
    String name;
    String phoneNumber;
    String email;

    Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}

class ContactBook {
    Contact[] contacts = new Contact[100];
    int numContacts = 0;

    void addContact(Contact contact) {
        contacts[numContacts] = contact;
        numContacts++;
    }

    void deleteContact(int index) {
        if (index >= 0 && index < numContacts) {
            for (int i = index; i < numContacts - 1; i++) {
                contacts[i] = contacts[i + 1];
            }
            numContacts--;
        } else {
            System.out.println("There is no data in that index. Please input the right index!");
        }
    }

    void printList() {
        for (int i = 0; i < numContacts; i++) {
            System.out.println((i + 1) + ". " + contacts[i].name + " - " + contacts[i].phoneNumber + " - " + contacts[i].email);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ContactBook contactBook = new ContactBook();
        contactBook.addContact(new Contact("John Doe", "1234567890", "john.doe@example.com"));
        contactBook.addContact(new Contact("Jane Smith", "0987654321", "jane.smith@example.com"));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("**************************************************");
            System.out.println("People Innovation Excellence BINUS UNIVERSITY");
            System.out.println("(A)dd (D)elete (E)mail Search (P)rint List (Q)uit");
            System.out.print("Please Enter a command:");
            String command = scanner.nextLine();

            Contact contact = new Contact("", "", "");
            if (command.equalsIgnoreCase("A")) {
                while (true) {
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    contact.name = name;
                    while (true) {
                        System.out.print("Enter phone number (12 digits): ");
                        String phoneNumberInput = scanner.nextLine();
                        if (phoneNumberInput.matches("^[0-9]{12}$")) {
                            contact.phoneNumber = phoneNumberInput;
                            break;
                        } else {
                            System.out.println("Invalid phone number format. Please enter 12 digits.");
                        }
                    }
                    while (true) {
                        System.out.print("Enter email (example@example.com): ");
                        String emailInput = scanner.nextLine();
                        if (emailInput.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                            contact.email = emailInput;
                            break;
                        } else {
                            System.out.println("Invalid email format. Please enter a valid email address.");
                        }
                    }
                    contactBook.addContact(contact);
                    break;
                }
            } else if (command.equalsIgnoreCase("D")) {
                System.out.print("Enter index number to delete: ");
                int index = scanner.nextInt();
                scanner.nextLine(); // consume newline character
                contactBook.deleteContact(index - 1);
            } else if (command.equalsIgnoreCase("E")) {
                System.out.print("Enter email to search: ");
                String email = scanner.nextLine();
                for (int i = 0; i < contactBook.numContacts; i++) {
                    if (contactBook.contacts[i].email.equals(email)) {
                        System.out.println((i + 1) + ". " + contactBook.contacts[i].name + " - " + contactBook.contacts[i].phoneNumber + " - " + contactBook.contacts[i].email);
                        break;
                    }
                }
            } else if (command.equalsIgnoreCase("P")) {
                contactBook.printList();
            } else if (command.equalsIgnoreCase("Q")) {
                break;
            } else {
                System.out.println("Invalid command.");
            }

            while (true) {
                System.out.print("Do you want to continue? (Y/N): ");
                String continueInput = scanner.nextLine();
                if (continueInput.equalsIgnoreCase("Y")) {
                    break;
                } else if (continueInput.equalsIgnoreCase("N")) {
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                } else {
                    System.out.println("Invalid input. Please enter Y or N.");
                }
            }
        }
    }
}