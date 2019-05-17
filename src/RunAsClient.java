import java.util.List;
import java.util.Scanner;

public class RunAsClient {


    public static void main(String[] args) {
        DbHandler.getInstance().open();

        System.out.println("Enter your name: ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.println("Welcome " + username +"\n");
        Client client = new Client();
        client.setName(username);
        DbHandler.getInstance().createClient(client);

        System.out.println("Choose an option: \n search \n buy \n make request");
        String choice = scanner.nextLine();


        switch(choice){
            case "search":
                System.out.println("What would you like to search for: books, ebooks, boardgames \n");
                String secondChoice = scanner.nextLine();
                switch (secondChoice) {
                    case "books":

                        System.out.println("Enter the title of the book: \n");
                        String title = scanner.nextLine();
                        printBooks( DbHandler.getInstance().searchByTitle(title));
                }
                break;
            case "buy":
                break;
            case "make request":
                break;

        }




    }

    public static void printBooks(List<Book> book) {
        for(Book b : book){
            System.out.print(b.getId() + " ");
            System.out.print(b.getTitle() + " ");
            System.out.print(b.getAuthor() + " ");
            System.out.print(b.getPrice() + " \n");

        }
    }


}

