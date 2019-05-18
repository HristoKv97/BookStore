import org.sqlite.core.DB;


import java.util.List;

public class Main {

    public static void main(String[] args) {
        //opening the connection
       DbHandler.getInstance().open();

        //Printing the results from search by part of title, same can be done for ebooks
        searchBook(DbHandler.getInstance().searchByTitle("kaz"));
        //Printing the results from search by author, same can be done for ebooks
        searchBook(DbHandler.getInstance().searchByAuthor(""));
        // Prints whole inventory
        printResults(DbHandler.getInstance().queryBoardGame(), DbHandler.getInstance().queryBooks(), DbHandler.getInstance().queryeBooks());
        //Prints the results from boardgame search by number of players
        searchBoardGame(DbHandler.getInstance().searchByNumOfPlayers(5));
        //Prints the results from boardgame search by part of title
        searchBoardGame(DbHandler.getInstance().searchBoardGameByTitle("Exam"));
        //Buy a book, ebook or boardgame by Catalog number and quantity
        DbHandler.getInstance().buyABook(14, 5);
        DbHandler.getInstance().buyAnEBook(14, 5);
        DbHandler.getInstance().buyABoardGame(14, 5);

}

    //Prints whole inventory
    public static void printResults(List<BoardGame> boardgames, List<Book> book, List<eBook> ebook) {
        System.out.println("__________________________BOARDGAMES_______________________________________");
        for (BoardGame b : boardgames) {
            System.out.print(b.getId() + " ");
           System.out.print(b.getTitle() + "  ");
            System.out.print(b.getMinPlayers() + "  ");
           System.out.print(b.getMaxPlayers() + "  ");
           System.out.print(b.getPrice() + "   ");
            System.out.println(b.getQuantity() + " \n");
        }

        System.out.println("__________________________BOOKS_______________________________________");

        for(Book b : book){
            System.out.print(b.getId() + " ");
            System.out.print(b.getTitle() + " ");
            System.out.print(b.getAuthor() + " ");
            System.out.print(b.getPrice() + " ");
            System.out.println(b.getQuantity() + " \n");
        }
        System.out.println("__________________________E-BOOKS_______________________________________");
        for(eBook b: ebook){

            System.out.print(b.getId() + " ");
            System.out.print(b.getTitle() + " ");
            System.out.print(b.getAuthor() + " ");
            System.out.print(b.getPrice() + " ");
            System.out.println(b.getQuantity() + " \n");
        }
    }

    public static void searchBook(List<Book> book){

        for(Book b : book){
            System.out.print(b.getId() + " ");
            System.out.print(b.getTitle() + " ");
            System.out.print(b.getAuthor() + " ");
            System.out.print(b.getPrice() + " \n");
        }

    }

    // Inserts a new book, if book exists -> adds only the quantity, if not -> creates a new entry in DB and
    // and removes client request if available
    public static void addBook(){

        Book book = new Book();
        book.setId(4);
        book.setTitle("dada12");
        book.setAuthor("dada12");
        book.setPrice(23);
        book.setQuantity(10);
        DbHandler.getInstance().insertBooks(book);

    }

    public static void addеBook(){

        eBook ebook = new eBook();
        ebook.setId(4);
        ebook.setTitle("Example");
        ebook.setAuthor("Example");
        ebook.setPrice(23);
        ebook.setQuantity(10);
        DbHandler.getInstance().inserteBooks(ebook);

    }

    public static void addBoardGame(){
        BoardGame boardGame = new BoardGame();

        boardGame.setId(10);
        boardGame.setTitle("Example");
        boardGame.setMinPlayers(2);
        boardGame.setMaxPlayers(6);
        boardGame.setPrice(10);
        boardGame.setQuantity(10);

        DbHandler.getInstance().insertBoardGames(boardGame);
    }
    public static void addRequest(){

        Request request = new Request();

        request.setId(1);
        request.setClientid(1);
        request.setItemName("Example");
        request.setItemAuthor("Example");
        request.setProduct("books");

        DbHandler.getInstance().insertRequest(request);
    }

    public static void printRequests(List<Request> requests){
        for(Request r : requests){
            System.out.print(r.getId() + " ");
            System.out.print(r.getClientid() + " ");
            System.out.print(r.getItemName() + " ");
            System.out.print(r.getItemAuthor() + " ");
            System.out.print(r.getProduct() + " \n");
        }
    }

    public static void searchBoardGame(List<BoardGame> boardGames){

        for (BoardGame b : boardGames) {
            System.out.print(b.getId() + " ");
            System.out.print(b.getTitle() + "  ");
            System.out.print(b.getMinPlayers() + "  ");
            System.out.print(b.getMaxPlayers() + "  ");
            System.out.print(b.getPrice() + "   ");
            System.out.println(b.getQuantity() + " \n");
        }
    }




}


