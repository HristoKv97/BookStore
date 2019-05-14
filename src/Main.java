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
        printResults(DbHandler.getInstance().queryRequest());
    }


    public static void printResults(List<Request> requests) {
        for (Request r : requests) {
            System.out.print(r.getId() + " ");
           System.out.print(r.getClientid() + "  ");
            System.out.print(r.getItemid() + "  ");
           System.out.print(r.getProduct() + "   \n");
        }
    }
}


