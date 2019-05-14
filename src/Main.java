import java.util.List;

public class Main {

    public static void main(String[] args) {
        DbHandler.getInstance().open();
        Request request = new Request();
        request.setId(9);
        request.setItemid(11);
        request.setClientid(13);
        request.setProduct("asdasdasdasd");


        //DbHandler.getInstance().insertRequest(request);
        //System.out.println("ID Title Author Price Quantity");
        printResults(DbHandler.getInstance().searchByTitle("Prikazki"));
    }


    public static void printResults(List<Book> books) {
        for (Book b : books) {
            System.out.print(b.getId() + " ");
           System.out.print(b.getAuthor() + "  ");
            System.out.print(b.getTitle() + "  ");
           System.out.print(b.getPrice() + "   \n");
        }
    }
}


