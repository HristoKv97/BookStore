import org.sqlite.core.DB;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHandler {
    public static final String DBNAME = "bookstore.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Ico\\Desktop\\GitHub\\BookStore\\" + DBNAME;

    public static final String TABLE_BOOKS = "books";
    public static final String COLUMN_BOOK_ID = "id";
    public static final String COLUMN_BOOK_TITLE = "title";
    public static final String COLUMN_BOOK_AUTHOR = "authorName";
    public static final String COLUMN_BOOK_QUANTITY = "quantity";
    public static final String COLUMN_BOOK_PRICE = "price";

    public static final int INDEX_BOOK_ID = 1;
    public static final int INDEX_BOOK_TITLE = 2;
    public static final int INDEX_BOOK_AUTHOR= 3;
    public static final int INDEX_BOOK_QUANTITY= 4;
    public static final int INDEX_BOOK_PRICE= 5;


    public static final String TABLE_EBOOKS = "ebooks";
    public static final String COLUMN_EBOOK_ID = "id";
    public static final String COLUMN_EBOOK_TITLE = "title";
    public static final String COLUMN_EBOOK_AUTHOR = "authorName";
    public static final String COLUMN_EBOOK_QUANTITY = "quantity";
    public static final String COLUMN_EBOOK_PRICE = "price";

    public static final  int INDEX_EBOOK_ID = 1;
    public static final  int INDEX_EBOOK_TITLE = 2;
    public static final  int INDEX_EBOOK_AUTHOR = 3;
    public static final  int INDEX_EBOOK_QUANTITY= 4;
    public static final  int INDEX_EBOOK_PRICE = 5;

    public static final String TABLE_BOARDGAMES = "boardgames";
    public static final String COLUMN_BOARDGAMES_ID = "id";
    public static final String COLUMN_BOARDGAMES_TITLE = "title";
    public static final String COLUMN_BOARDGAMES_MAXPLAYERS = "maxPlayers";
    public static final String COLUMN_BOARDGAMES_MINPLAYERS = "minPlayers";
    public static final String COLUMN_BOARDGAMES_PRICE = "price";
    public static final String COLUMN_BOARDGAMES_QUANTITY = "quantity";

    public static final int INDEX_BOARDGAMES_ID_= 1;
    public static final int INDEX_BOARDGAMES_TITLE_= 2;
    public static final int INDEX_BOARDGAMES_MINPLAYERS= 3;
    public static final int INDEX_BOARDGAMES_MAXPLAYERS= 4;
    public static final int INDEX_BOARDGAMES_PRICE = 5;
    public static final int INDEX_BOARDGAMES_QUANTITY = 6;

    public static final String TABLE_REQUESTS = "requests";
    public static final String COLUMN_REQUESTS_ID = "id";
    public static final String COLUMN_REQUESTS_CLIENTID = "clientId";
    public static final String COLUMN_REQUESTS_ITEMID = "itemId";
    public static final String COLUMN_REQUESTS_TYPE = "product";

    public static final int INDEX_REQUESTS_ID = 1;
    public static final int INDEX_REQUESTS_CLIENTID = 2;
    public static final int INDEX_REQUESTS_ITEMID = 3;
    public static final int INDEX_REQUESTS_TYPE = 4;

    public static final String TABLE_CLIENTS = "clients";
    public static final String COLUMN_CLIENTS_ID = "id";
    public static final String COLUMN_CLIENTS_NAME = "name";

    public static final int INDEX_CLIENTS_ID = 1;
    public static final int INDEX_CLIENTS_NAME = 2;

    public static final  String QUERY_BOOKS = "SELECT " + COLUMN_BOOK_ID + ", " + COLUMN_BOOK_TITLE + ", " +
            COLUMN_BOOK_AUTHOR + ", " + COLUMN_BOOK_PRICE + ", " + COLUMN_BOOK_QUANTITY + " FROM " + TABLE_BOOKS + " WHERE " +
            COLUMN_BOOK_QUANTITY + " != 0";

    public static final String QUERY_EBOOKS = "SELECT " + COLUMN_EBOOK_ID + ", " + COLUMN_EBOOK_TITLE + ", " +
            COLUMN_EBOOK_AUTHOR + ", " + COLUMN_EBOOK_PRICE + ", " + COLUMN_BOOK_QUANTITY + " FROM " + TABLE_EBOOKS + " WHERE " +
            COLUMN_EBOOK_QUANTITY + " != 0";

    public static final  String QUERY_BOARDGAMES = "SELECT " + COLUMN_BOARDGAMES_ID + ", " + COLUMN_BOARDGAMES_TITLE +
            ", " + COLUMN_BOARDGAMES_MINPLAYERS + ", " + COLUMN_BOARDGAMES_MAXPLAYERS + ", " + COLUMN_BOARDGAMES_PRICE +
            " FROM " + TABLE_BOARDGAMES + " WHERE " + COLUMN_BOARDGAMES_QUANTITY + " != 0";

    public static final String QUERY_REQUESTS = "SELECT* FROM " + TABLE_REQUESTS;

    public static final String INSERT_BOOKS = "INSERT INTO " + TABLE_BOOKS + '(' + COLUMN_BOOK_ID + ", " + COLUMN_BOOK_TITLE +
            ", " + COLUMN_BOOK_AUTHOR + ", " + COLUMN_BOOK_QUANTITY + ", " + COLUMN_BOOK_PRICE + ") VALUES(?, ?, ?, ?, ?)";

    public static final  String INSERT_EBOOKS = "INSERT INTO " + TABLE_EBOOKS + '(' + COLUMN_EBOOK_ID + ", " + COLUMN_EBOOK_TITLE +
            ", " + COLUMN_EBOOK_AUTHOR + ", " + COLUMN_EBOOK_QUANTITY + ", " + COLUMN_EBOOK_PRICE + ") VALUES(?, ?, ?, ?, ?)";

    public static final  String INSERT_BOARDGAMES = "INSERT INTO " + TABLE_BOARDGAMES + '(' + COLUMN_BOARDGAMES_ID + ", " + COLUMN_BOARDGAMES_TITLE +
            ", " + COLUMN_BOARDGAMES_MINPLAYERS + ", " + COLUMN_BOARDGAMES_MAXPLAYERS + ", " + COLUMN_BOARDGAMES_PRICE + ", " + COLUMN_BOARDGAMES_QUANTITY +
            ") VALUES(?, ?, ?, ?, ?, ?)";

    public static final String INSERT_REQUESTS = "INSERT INTO " + TABLE_REQUESTS + '(' + COLUMN_REQUESTS_ID + ", " + COLUMN_REQUESTS_CLIENTID + ", " +
            COLUMN_REQUESTS_ITEMID + ", " + COLUMN_REQUESTS_TYPE + ") VALUES(?, ?, ?, ?)";

    private Connection con;
    private PreparedStatement queryAllBooks;
    private PreparedStatement insertIntoBooks;
    private PreparedStatement queryAlleBooks;
    private PreparedStatement insertIntoeBooks;
    private PreparedStatement queryAllBoardGames;
    private PreparedStatement insertIntoBoardGames;
    private PreparedStatement queryAllRequests;
    private PreparedStatement insertIntoRequests;

    private static DbHandler instance = new DbHandler();

    private DbHandler(){

    }

    public static DbHandler getInstance(){
        return instance;
    }

    public boolean open(){
        try {
            con = DriverManager.getConnection(CONNECTION_STRING);
            queryAllBooks = con.prepareStatement(QUERY_BOOKS);
            insertIntoBooks = con.prepareStatement(INSERT_BOOKS);
            queryAlleBooks = con.prepareStatement(QUERY_EBOOKS);
            insertIntoeBooks = con.prepareStatement(INSERT_EBOOKS);
            queryAllBoardGames = con.prepareStatement(QUERY_BOARDGAMES);
            insertIntoBoardGames = con.prepareStatement(INSERT_BOARDGAMES);
            queryAllRequests = con.prepareStatement(QUERY_REQUESTS);
            insertIntoRequests = con.prepareStatement(INSERT_REQUESTS);
            return true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    public void close(){
        try {
            if(queryAllBooks != null){
                queryAllBooks.close();
            }
            if(insertIntoBooks != null){
                insertIntoBooks.close();
            }
            if(queryAlleBooks != null){
                queryAlleBooks.close();
            }
            if(insertIntoeBooks != null){
                insertIntoeBooks.close();
            }
            if(queryAllBoardGames != null){
                queryAllBoardGames.close();
            }
            if(insertIntoBoardGames != null){
                insertIntoBoardGames.close();
            }
            if(queryAllRequests != null){
                queryAllRequests.close();
            }
            if(insertIntoRequests != null){
                insertIntoRequests.close();
            }
            if(con != null){
                con.close();
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Book> queryBooks(){
        try{
            ResultSet results = queryAllBooks.executeQuery();

            List<Book> books = new ArrayList<>();

            while(results.next()){
                Book book = new Book();
                book.setId(results.getInt(INDEX_BOOK_ID));
                book.setTitle(results.getString(INDEX_BOOK_TITLE));
                book.setAuthor(results.getString(INDEX_BOOK_AUTHOR));
                book.setQuantity(results.getInt(INDEX_BOOK_QUANTITY));
                book.setPrice(results.getDouble(INDEX_BOOK_PRICE));

                books.add(book);
            }
            return books;
        } catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @SuppressWarnings("Duplicates")
    public boolean insertBooks(Book book){
        try {
            insertIntoBooks.setInt(1, book.getId());
            insertIntoBooks.setString(2, book.getTitle());
            insertIntoBooks.setString(3, book.getAuthor());
            insertIntoBooks.setInt(4, book.getQuantity());
            insertIntoBooks.setDouble(5, book.getPrice());
            int affectedRows = insertIntoBooks.executeUpdate();
            if(affectedRows != 1){
                return false;
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return true;
    }


    public List<eBook> queryeBooks(){
        try{
            ResultSet results = queryAlleBooks.executeQuery();

            List<eBook> ebooks = new ArrayList<>();

            while(results.next()){
                eBook ebook = new eBook();
                ebook.setId(results.getInt(INDEX_EBOOK_ID));
                ebook.setTitle(results.getString(INDEX_EBOOK_TITLE));
                ebook.setAuthor(results.getString(INDEX_EBOOK_AUTHOR));
                ebook.setQuantity(results.getInt(INDEX_EBOOK_QUANTITY));
                ebook.setPrice(results.getDouble(INDEX_EBOOK_PRICE));

                ebooks.add(ebook);
            }
            return ebooks;
        } catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("Duplicates")
    public boolean inserteBooks(eBook ebook){
        try {
            insertIntoeBooks.setInt(1, ebook.getId());
            insertIntoeBooks.setString(2, ebook.getTitle());
            insertIntoeBooks.setString(3, ebook.getAuthor());
            insertIntoeBooks.setInt(4, ebook.getQuantity());
            insertIntoeBooks.setDouble(5, ebook.getPrice());
            int affectedRows = insertIntoeBooks.executeUpdate();
            if(affectedRows != 1){
                return false;
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return true;
    }

    public List<BoardGame> queryBoardGame(){
        try{
            ResultSet results = queryAllBoardGames.executeQuery();

            List<BoardGame> boardGames = new ArrayList<>();

            while(results.next()){
                BoardGame boardGame = new BoardGame();
                boardGame.setId(results.getInt(INDEX_EBOOK_ID));
                boardGame.setTitle(results.getString(INDEX_EBOOK_TITLE));
                boardGame.setMinPlayers(results.getInt(INDEX_BOARDGAMES_MINPLAYERS));
                boardGame.setMaxPlayers(results.getInt(INDEX_BOARDGAMES_MAXPLAYERS));
                boardGame.setQuantity(results.getInt(INDEX_EBOOK_QUANTITY));
                boardGame.setPrice(results.getDouble(INDEX_EBOOK_PRICE));

                boardGames.add(boardGame);
            }
            return boardGames;
        } catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean insertRequest(Request request){
        try{
            insertIntoRequests.setInt(1, request.getId());
            insertIntoRequests.setInt(2, request.getClientid());
            insertIntoRequests.setInt(3, request.getItemid());
            insertIntoRequests.setString(4, request.getProduct());
            int affectedRows = insertIntoRequests.executeUpdate();
            if(affectedRows != 1){
                return false;
            }
        } catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return true;
    }

    public List<Request> queryRequest(){
        try{
            ResultSet results = queryAllRequests.executeQuery();

            List<Request> requests = new ArrayList<>();

            while(results.next()){
                Request request = new Request();
                request.setId(results.getInt(INDEX_REQUESTS_ID));
                request.setClientid(results.getInt(INDEX_REQUESTS_CLIENTID));
                request.setProduct(results.getString(INDEX_REQUESTS_TYPE));
                request.setItemid(results.getInt(INDEX_REQUESTS_ITEMID));

              requests.add(request);
            }
            return requests;
        } catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }





}
