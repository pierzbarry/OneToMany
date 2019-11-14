// hard code companies and people
// make new company, person, and link - user input
// add a link - connection class
// be able to edit the company.name, person.name
// edit connection between one person and one company (changing a person works for company) 

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // poor mans database
    private static ArrayList<Company> companyDB = new ArrayList<>();
    private static ArrayList<Person> personDB = new ArrayList<>();
    private static ArrayList<Link> linkDB = new ArrayList<>();
    private static ArrayList<User> userDB = new ArrayList<>();

    private static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {

        // calls the database constructor methods
        createCompanyDB();
        createPersonDB();
        createLinkDB();
        createUserDB();

        // display our constructed information
        // showAllLinks();

        // require user login to use database app
        validateLogIn();

        // adding user information
        String userStr;

        do {
            System.out.print("\nWould you like to...\n\n" +
                    "\t[\"P\"] add a Person\n" +
                    "\t[\"C\"] add a Company\n" +
                    "\t[\"L\"] add a Link\n" +
                    "\t[\"E\"] to edit some information\n" +
                    "\t[\"D\"] to display a database\n\n" +
                    "\t[\"Q\"] to Logout\n\n" +
                    "Your Choice: ");
            userStr = keyboard.nextLine();

            if (userStr.equalsIgnoreCase("P")) {
                createNewPerson();
                showPersonDB();
            } else if (userStr.equalsIgnoreCase("C")) {
                createNewCompany();
                showCompanyDB();
            } else if (userStr.equalsIgnoreCase("L")) {
                createNewLink();
                showAllLinksByCompany();
            } else if (userStr.equalsIgnoreCase("E")) {
                // edit info
                System.out.println("What database would you like to edit? " +
                        "\nPerson [\"P\"] Company [\"C\"] or Quit [\"Q\"]: ");
                userStr = keyboard.nextLine();

                if (userStr.equalsIgnoreCase("P")) {
                    editPerson();
                } else if (userStr.equalsIgnoreCase("C")) {
                    editCompany();
                } else if (userStr.equalsIgnoreCase("Q")) {
                    break;
                }

            } else if (userStr.equalsIgnoreCase("D")) {
                System.out.print("Which database would you like to display? " +
                        "\nPerson [\"P\"] Company [\"C\"] List [\"L\"] Quit [\"Q\"]: ");
                userStr = keyboard.nextLine();

                if (userStr.equalsIgnoreCase("P")) {
                    showPersonDB();
                } else if (userStr.equalsIgnoreCase("C")) {
                    showCompanyDB();
                } else if (userStr.equalsIgnoreCase("L")) {
                    showAllLinksByCompany();
                } else if (userStr.equalsIgnoreCase("Q")) {
                    break;
                }

            } else if (userStr.equalsIgnoreCase("Q")) {
                System.out.println("\nYou have logged out! See ya later <3");
                validateLogIn();
            }
        } while (true);

        // has to go right before main method
        keyboard.close();
    }

    private static void editPerson() {
        System.out.println("Enter the ID of the person you would like to edit...");
        showPersonDB();
        System.out.print("Your choice: ");
        int userId = keyboard.nextInt();
        keyboard.nextLine();

        // setting object to null so IDE doesn't complain about return
        Person myPerson = null;

        // find the person they entered and REMEMBER IT (to actual person object)
        for (Person person : personDB) {
            if (person.getId() == userId) {
                myPerson = person;
            }
        }

        // assuming we found it and remembered it
        // replace said name with user input
        if (myPerson != null) {
            System.out.print("Please enter a new Name: ");
            String userStr = keyboard.nextLine();
            myPerson.setName(userStr);

            System.out.println("Successfully changed information to: ");
            System.out.println("\tId: " + myPerson.getId() + " Name:" + myPerson.getName());
        } else {
            System.out.println("Sorry, I could not find that person.");
        }
    }

    private static void editCompany() {
        System.out.println("Enter the ID of the company you would like to edit...");
        showCompanyDB();
        System.out.print("Your choice: ");
        int userId = keyboard.nextInt();
        keyboard.nextLine();

        Company myCompany = null;

        for (Company company : companyDB) {
            if (company.getId() == userId) {
                myCompany = company;
            }
        }

        if (myCompany != null) {
            System.out.print("Please enter a new Name: ");
            String userStr = keyboard.nextLine();
            myCompany.setName(userStr);

            System.out.println("Successfully changed information to: ");
            System.out.println("\tId: " + myCompany.getId() + " Name: " + myCompany.getName());
        } else {
            System.out.println("Sorry, I could not find that company.");
        }
    }

    private static void createNewPerson() {
        String name;
        System.out.print("\nPlease enter the Person's name: ");
        name = keyboard.nextLine();

        // setting person's id to be one larger than the current largest (thus making each id unique)
        // i.e if the last personID is 10, the new one will be 11
        int id = personDB.size() + 1;
        personDB.add(new Person(id, name));
    }

    private static void createNewCompany() {
        String name;
        System.out.print("\nPlease enter the Company's name: ");
        name = keyboard.nextLine();

        int id = companyDB.size() + 1;
        companyDB.add(new Company(id, name));
    }

    private static void createNewLink() {
        int personId;
        System.out.println("\nPlease enter the ID of the Person you wish to link...");
        showPersonDB();
        System.out.print("Your choice: ");
        personId = keyboard.nextInt();
        keyboard.nextLine();
        String personName = LookUpPersonName(personId);

        int companyId;
        System.out.println("\nPlease enter the ID of the company you wish to link with" + personName);
        showCompanyDB();
        System.out.print("Your choice: ");
        companyId = keyboard.nextInt();
        keyboard.nextLine();
        String companyName = LookUpCompanyName(companyId);

        System.out.println("We are linking " + personName + " with " + companyName);
        System.out.print("\nPlease enter the start date for " + personName + " at " + companyName + ": ");
        String startDate = keyboard.nextLine();

        System.out.print("Enter the role " + personName + " has at " + companyName + ": ");
        String role = keyboard.nextLine();

        System.out.println();
        int id = linkDB.size() + 1;
        linkDB.add(new Link(id, companyId, personId, startDate, role));
    }

    private static void showPersonDB() {
        // for loop goes through database array and prints its contents
        System.out.println("Person Database: ");
        for (Person person : personDB) {
            System.out.println("\tId: " + person.getId() + " Name: " + person.getName());
        }
        System.out.println();
    }

    private static void showCompanyDB() {
        System.out.println("Company Database: ");
        for (Company company : companyDB) {
            System.out.println("\tId: " + company.getId() + " Name: " + company.getName());
        }
        System.out.println();
    }

    private static void showAllLinks() {
        System.out.println("Link Database: ");

        // setting outside forloop variable to create memory
        int rememberMe = 0;

        for (Link link : linkDB) {

            // if previous company id is not the same as the current, add space
            if (rememberMe != link.getCompanyId()) {
                System.out.println();
            }
            System.out.println(LookUpCompanyName(link.getCompanyId()) + " + "
                    + LookUpPersonName(link.getPersonId()) + " :: "
                    + link.getStartDate() + ", " + link.getRole());

            // this sets the counter back to the current company
            // because forloop does not remember what it just looked at
            rememberMe = link.getCompanyId();
        }
    }

    private static void showAllLinksByCompany() {
        // same concept as showAllLinks but organizes by company
        System.out.println();
        for (Company company : companyDB) {
            System.out.println(company.getName() + ": ");
            // loop through all links
            for (Link link : linkDB) {
                // see if the link belongs to current company
                if (link.getCompanyId() == company.getId()) {
                    // loop through all people
                    for (Person person : personDB) {
                        // see if person belongs to current link
                        if (person.getId() == link.getPersonId()) {
                            System.out.println("\t" + person.getName() + " :: " + link.getStartDate() + ", " + link.getRole() + ".");
                        }
                    }
                }
            }
            System.out.println();
        }
    }

    private static String LookUpCompanyName(int companyId) {
        for (Company company : companyDB) {
            if (company.getId() == companyId) {
                return company.getName();
            }
        }
        return "Unknown";
    }

    private static String LookUpPersonName(int personId) {
        for (Person person : personDB) {
            if (person.getId() == personId) {
                return person.getName();
            }
        }
        return "Unknown";
    }

    private static void createCompanyDB() {

        // create companies
        companyDB.add(new Company(1, "Apple"));
        companyDB.add(new Company(2, "Waffle House"));
        companyDB.add(new Company(3, "Starbucks"));
        companyDB.add(new Company(4, "Unemployed"));
    }

    private static void createPersonDB() {

        // create people
        personDB.add(new Person(1, "Karen"));
        personDB.add(new Person(2, "Pierz"));
        personDB.add(new Person(3, "Molly"));
        personDB.add(new Person(4, "Josh"));
        personDB.add(new Person(5, "Kyle"));
        personDB.add(new Person(6, "Mac"));
        personDB.add(new Person(7, "Brandy"));
        personDB.add(new Person(8, "Anne"));
        personDB.add(new Person(9, "Jane"));
        personDB.add(new Person(10, "Ben"));
    }

    private static void createLinkDB() {
        // 1 == Apple || 2 == Waffle House || 3 == Starbucks || 4 == Unemployed

        // 1 == Karen || 2 == Pierz || 3 == Molly || 4 == Josh || 5 == Kyle
        // 6 == Mac || 7 == Brandy || 8 == Anne || 9 == Jane || 10 == Ben

        // create links between companies and people
        linkDB.add(new Link(1, 1, 1, "June 2018", "Front-End Developer"));
        linkDB.add(new Link(2, 1, 2, "July 2019", "Janitor"));
        linkDB.add(new Link(8, 1, 10, "April 2019", "Development Operator"));

        linkDB.add(new Link(4, 3, 4, "February 2009", "Barista"));
        linkDB.add(new Link(6, 3, 6, "December 2017", "Barista"));
        linkDB.add(new Link(5, 3, 5, "January 2015", "Shift Lead"));

        linkDB.add(new Link(7, 2, 7, "June 2020", "Cook"));
        linkDB.add(new Link(3, 2, 3, "March 2002", "Server"));
        linkDB.add(new Link(9, 2, 9, "October 2016", "Server"));

        linkDB.add(new Link(10, 4, 8, "January 2020", "Laid Off"));
    }

    private static void createUserDB(){

        // create user login information
        userDB.add(new User("pierz", "pass"));
        userDB.add(new User("user", "word"));
    }

    private static boolean validateLogIn() {
        // requires user to input valid username and password to use app

        String userName, userPassword;
        int counter = 0;

        while (true) {

            System.out.println("\n|▓▓▓【☆】▓▓▓| Database Builder |▓▓▓【☆】▓▓▓|");

            System.out.print("Please enter a valid username: ");
            userName = keyboard.nextLine();
            System.out.print("Please enter a valid password: ");
            userPassword = keyboard.nextLine();


            // loops through entire userDB array and compares keyboard input to it
            for (User u : userDB) {
                // returns true (thus ending loop) if a matching username and password combination are found
                if (u.getName().equals(userName)) {
                    if (u.getPassword().equals(userPassword)) {
                        System.out.println("\nYou have successfully logged in!");
                        return true;
                    }
                }
            }

            // multiple attempt limiter if desired
            // if you input this make sure you edit beginning of program because otherwise after
            // three failed attempts it will log you in lol!

//            counter++;
//            if (counter < 3) {
//                System.out.println("Invalid login. Please try again. ");
//            } else {
//                System.out.println("Three invalid logins. Sorry! Try again another day.");
//                return false;
//            }
        }
    }
} // closes main