import java.util.List;

public class Main {

    public static void main(String[] args) {

        //submitting a request
       DbHandler.getInstance().open();
//        Request request = new Request();
//        request.setId(9);
//        request.setItemid(11);
//        request.setClientid(13);
//        request.setProduct("asdasdasdasd");
//
//
//        //DbHandler.getInstance().insertRequest(request);
        //System.out.println("ID Title Author Price Quantity");
        //Printing the results from search
        //printResults(DbHandler.getInstance().searchByTitle("kaz"));
        // Prints whole inventory

        printResults(DbHandler.getInstance().queryBoardGame(), DbHandler.getInstance().queryBooks(), DbHandler.getInstance().queryeBooks());
        DbHandler.getInstance().buyABook(14, 5);
        printResults(DbHandler.getInstance().queryBoardGame(), DbHandler.getInstance().queryBooks(), DbHandler.getInstance().queryeBooks());

    }


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


}


