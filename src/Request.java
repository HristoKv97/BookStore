public class Request {
    private int id;
    private String product;
    private String itemName;
    private String itemAuthor;
    private int clientid;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemAuthor() {
        return itemAuthor;
    }

    public void setItemAuthor(String itemAuthor) {
        this.itemAuthor = itemAuthor;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }



    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }


}
