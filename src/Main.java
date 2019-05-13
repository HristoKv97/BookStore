import java.util.List;

public class Main {

    public static void main(String[] args) {
        DbHandler.getInstance().open();
        eBook ebook = new eBook();
        ebook.setId(1);
        ebook.setTitle("asdas");
        ebook.setAuthor("Ivan");
        ebook.setQuantity(120);
        ebook.setPrice(25.5);
        DbHandler.getInstance().inserteBooks(ebook);
        System.out.println("ID Title Author Price Quantity");
        printResults(DbHandler.getInstance().queryeBooks());
    }


    public static void printResults(List<eBook> ebooks) {
        for (eBook b : ebooks) {
            System.out.print(b.getId() + " ");
            System.out.print(b.getTitle() + " ");
            System.out.print(b.getAuthor() + " ");
            System.out.print(b.getPrice() + " ");
            System.out.print(b.getQuantity() + " \n");
        }
    }
}


