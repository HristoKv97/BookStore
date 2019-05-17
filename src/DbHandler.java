import org.sqlite.core.DB;

import javax.naming.directory.SearchResult;
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
    public static final String COLUMN_BOOK_PRICE = "price";
    public static final String COLUMN_BOOK_QUANTITY = "quantity";


    public static final int INDEX_BOOK_ID = 1;
    public static final int INDEX_BOOK_TITLE = 2;
    public static final int INDEX_BOOK_AUTHOR = 3;

    public static final int INDEX_BOOK_PRICE = 4;
    public static final int INDEX_BOOK_QUANTITY = 5;


    public static final String TABLE_EBOOKS = "ebooks";
    public static final String COLUMN_EBOOK_ID = "id";
    public static final String COLUMN_EBOOK_TITLE = "title";
    public static final String COLUMN_EBOOK_AUTHOR = "authorName";
    public static final String COLUMN_EBOOK_PRICE = "price";
    public static final String COLUMN_EBOOK_QUANTITY = "quantity";


    public static final int INDEX_EBOOK_ID = 1;
    public static final int INDEX_EBOOK_TITLE = 2;
    public static final int INDEX_EBOOK_AUTHOR = 3;
    public static final int INDEX_EBOOK_QUANTITY = 4;
    public static final int INDEX_EBOOK_PRICE = 5;

    public static final String TABLE_BOARDGAMES = "boardgames";
    public static final String COLUMN_BOARDGAMES_ID = "id";
    public static final String COLUMN_BOARDGAMES_TITLE = "title";
    public static final String COLUMN_BOARDGAMES_MINPLAYERS = "minPlayers";
    public static final String COLUMN_BOARDGAMES_MAXPLAYERS = "maxPlayers";
    public static final String COLUMN_BOARDGAMES_PRICE = "price";
    public static final String COLUMN_BOARDGAMES_QUANTITY = "quantity";

    public static final int INDEX_BOARDGAMES_ID = 1;
    public static final int INDEX_BOARDGAMES_TITLE = 2;
    public static final int INDEX_BOARDGAMES_MINPLAYERS = 3;
    public static final int INDEX_BOARDGAMES_MAXPLAYERS = 4;
    public static final int INDEX_BOARDGAMES_PRICE = 5;
    public static final int INDEX_BOARDGAMES_QUANTITY = 6;

    public static final String TABLE_REQUESTS = "requests";
    public static final String COLUMN_REQUESTS_ID = "id";
    public static final String COLUMN_REQUESTS_CLIENTID = "clientId";
    public static final String COLUMN_REQUESTS_ITEM_NAME = "itemName";
    public static final String COLUMN_REQUESTS_ITEM_AUTHOR = "itemAuthor";
    public static final String COLUMN_REQUESTS_TYPE = "product";

    public static final int INDEX_REQUESTS_ID = 1;
    public static final int INDEX_REQUESTS_CLIENTID = 2;
    public static final int INDEX_REQUESTS_ITEM_NAME = 3;
    public static final int INDEX_REQUESTS_ITEM_AUTHOR = 4;
    public static final int INDEX_REQUESTS_TYPE = 5;

    public static final String TABLE_CLIENTS = "clients";
    public static final String COLUMN_CLIENTS_ID = "id";
    public static final String COLUMN_CLIENTS_NAME = "name";

    public static final int INDEX_CLIENTS_ID = 1;
    public static final int INDEX_CLIENTS_NAME = 2;


    public static final String QUERY_BOOKS = "SELECT " + COLUMN_BOOK_ID + ", " + COLUMN_BOOK_TITLE + ", " +
            COLUMN_BOOK_AUTHOR + ", " + COLUMN_BOOK_PRICE + ", " + COLUMN_BOOK_QUANTITY + " FROM " + TABLE_BOOKS + " WHERE " +
            COLUMN_BOOK_QUANTITY + " != 0";

    public static final String QUERY_EBOOKS = "SELECT " + COLUMN_EBOOK_ID + ", " + COLUMN_EBOOK_TITLE + ", " +
            COLUMN_EBOOK_AUTHOR + ", " + COLUMN_EBOOK_PRICE + ", " + COLUMN_BOOK_QUANTITY + " FROM " + TABLE_EBOOKS + " WHERE " +
            COLUMN_EBOOK_QUANTITY + " != 0";

    public static final String QUERY_BOARDGAMES = "SELECT " + COLUMN_BOARDGAMES_ID + ", " + COLUMN_BOARDGAMES_TITLE +
            ", " + COLUMN_BOARDGAMES_MINPLAYERS + ", " + COLUMN_BOARDGAMES_MAXPLAYERS + ", " + COLUMN_BOARDGAMES_PRICE +
            " FROM " + TABLE_BOARDGAMES + " WHERE " + COLUMN_BOARDGAMES_QUANTITY + " != 0";

    public static final String QUERY_REQUESTS = "SELECT* FROM " + TABLE_REQUESTS;

    public static final String INSERT_BOOKS = "INSERT OR IGNORE INTO " + TABLE_BOOKS + '(' + COLUMN_BOOK_ID + ", " + COLUMN_BOOK_TITLE +
            ", " + COLUMN_BOOK_AUTHOR + ", " + COLUMN_BOOK_QUANTITY + ", " + COLUMN_BOOK_PRICE + ") VALUES(?, ?, ?, ?, ?)" + " ON CONFLICT(" +
            COLUMN_BOOK_ID + ") DO UPDATE SET quantity = " + COLUMN_BOOK_QUANTITY + " + ?";


    public static final String INSERT_EBOOKS = "INSERT OR IGNORE INTO " + TABLE_EBOOKS + '(' + COLUMN_EBOOK_ID + ", " + COLUMN_EBOOK_TITLE +
            ", " + COLUMN_EBOOK_AUTHOR + ", " + COLUMN_EBOOK_QUANTITY + ", " + COLUMN_EBOOK_PRICE + ") VALUES(?, ?, ?, ?, ?)"   + " ON CONFLICT(" +
            COLUMN_EBOOK_ID + ") DO UPDATE SET quantity = " + COLUMN_EBOOK_QUANTITY + " + ?";

    public static final String INSERT_BOARDGAMES = "INSERT OR IGNORE INTO " + TABLE_BOARDGAMES + '(' + COLUMN_BOARDGAMES_ID + ", " + COLUMN_BOARDGAMES_TITLE +
            ", " + COLUMN_BOARDGAMES_MINPLAYERS + ", " + COLUMN_BOARDGAMES_MAXPLAYERS + ", " + COLUMN_BOARDGAMES_PRICE + ", " + COLUMN_BOARDGAMES_QUANTITY +
            ") VALUES(?, ?, ?, ?, ?, ?)" + " ON CONFLICT(" +
            COLUMN_BOARDGAMES_ID + ") DO UPDATE SET quantity = " + COLUMN_BOARDGAMES_QUANTITY + " + ?";

    public static final String INSERT_REQUESTS = "INSERT INTO " + TABLE_REQUESTS + '(' + COLUMN_REQUESTS_ID + ", " + COLUMN_REQUESTS_CLIENTID + ", " +
            COLUMN_REQUESTS_ITEM_NAME + ", " + COLUMN_REQUESTS_ITEM_AUTHOR + ", " + COLUMN_REQUESTS_TYPE + ") VALUES(?, ?, ?, ?, ?)";

    public static final String SEARCH_BOOK_BY_TITLE = "SELECT " + COLUMN_BOOK_ID + ", " + COLUMN_BOOK_TITLE + ", " +
            COLUMN_BOOK_AUTHOR + ", " + COLUMN_BOOK_PRICE + " FROM " + TABLE_BOOKS + " WHERE " +
            COLUMN_BOOK_TITLE + " LIKE ?";

    public static final String SEARCH_BY_AUTHOR = "SELECT " + COLUMN_BOOK_ID + ", " + COLUMN_BOOK_TITLE + ", " +
            COLUMN_BOOK_AUTHOR + ", " + COLUMN_BOOK_PRICE + " FROM " + TABLE_BOOKS + " WHERE " +
            COLUMN_BOOK_AUTHOR + " = ?";

    public static final String SEARCH_BOARDGAME_BY_TITLE = "SELECT " + COLUMN_BOARDGAMES_ID + ", " + COLUMN_BOARDGAMES_TITLE + ", " +
            COLUMN_BOARDGAMES_MINPLAYERS + ", " + COLUMN_BOARDGAMES_MAXPLAYERS + ", " + COLUMN_BOARDGAMES_PRICE + " FROM " + TABLE_BOARDGAMES +
            " WHERE " + COLUMN_BOARDGAMES_TITLE + " LIKE ?";

    public static final String SEARCH_BOARDGAME_BY_PLAYERS = "SELECT " + COLUMN_BOARDGAMES_ID + ", " + COLUMN_BOARDGAMES_TITLE + ", " +
            COLUMN_BOARDGAMES_MINPLAYERS + ", " + COLUMN_BOARDGAMES_MAXPLAYERS + ", " + COLUMN_BOARDGAMES_PRICE + " FROM " + TABLE_BOARDGAMES +
            " WHERE ?" + " BETWEEN " + COLUMN_BOARDGAMES_MINPLAYERS + " AND " + COLUMN_BOARDGAMES_MAXPLAYERS;

    public static final String BUY_A_BOOK = "UPDATE " + TABLE_BOOKS + " SET " + COLUMN_BOOK_QUANTITY + " = " + COLUMN_BOOK_QUANTITY +
            " - ?" + " WHERE id = ?";
    public static final String BUY_AN_EBOOK = "UPDATE " + TABLE_EBOOKS + " SET " + COLUMN_EBOOK_QUANTITY + " = " + COLUMN_EBOOK_QUANTITY +
            " - ?" + " WHERE id = ?";
    public static final String BUY_A_BOARDGAME = "UPDATE " + TABLE_BOARDGAMES + " SET " + COLUMN_BOARDGAMES_QUANTITY + " = " + COLUMN_BOARDGAMES_QUANTITY +
            " - ?" + " WHERE id = ?";

    public static final String DELETE_A_REQUEST = "DELETE FROM " + TABLE_REQUESTS + " WHERE " +
            COLUMN_REQUESTS_TYPE + " = ? AND " + COLUMN_REQUESTS_ITEM_NAME +  " = ? AND " + COLUMN_REQUESTS_ITEM_AUTHOR +
            " = ?";




    private Connection con;
    private PreparedStatement queryAllBooks;
    private PreparedStatement insertIntoBooks;
    private PreparedStatement queryAlleBooks;
    private PreparedStatement insertIntoeBooks;
    private PreparedStatement queryAllBoardGames;
    private PreparedStatement insertIntoBoardGames;
    private PreparedStatement queryAllRequests;
    private PreparedStatement insertIntoRequests;
    private PreparedStatement searchByPartOfTitle;
    private PreparedStatement searchByAuthor;
    private PreparedStatement searchBoardGameByTitle;
    private PreparedStatement searchByNumOfPlayers;
    private PreparedStatement buyABook;
    private PreparedStatement buyAnEbook;
    private PreparedStatement buyABoardGame;
    private PreparedStatement deleteARequest;

    private static DbHandler instance = new DbHandler();

    private DbHandler() {

    }

    public static DbHandler getInstance() {
        return instance;
    }

    public boolean open() {
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
            searchByPartOfTitle = con.prepareStatement(SEARCH_BOOK_BY_TITLE);
            searchByAuthor = con.prepareStatement(SEARCH_BY_AUTHOR);
            searchBoardGameByTitle = con.prepareStatement(SEARCH_BOARDGAME_BY_TITLE);
            searchByNumOfPlayers = con.prepareStatement(SEARCH_BOARDGAME_BY_PLAYERS);
            buyABook = con.prepareStatement(BUY_A_BOOK);
            buyAnEbook = con.prepareStatement(BUY_AN_EBOOK);
            buyABoardGame = con.prepareStatement(BUY_A_BOARDGAME);
            deleteARequest = con.prepareStatement(DELETE_A_REQUEST);
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public void close() {
        try {
            if (queryAllBooks != null) {
                queryAllBooks.close();
            }
            if (insertIntoBooks != null) {
                insertIntoBooks.close();
            }
            if (queryAlleBooks != null) {
                queryAlleBooks.close();
            }
            if (insertIntoeBooks != null) {
                insertIntoeBooks.close();
            }
            if (queryAllBoardGames != null) {
                queryAllBoardGames.close();
            }
            if (insertIntoBoardGames != null) {
                insertIntoBoardGames.close();
            }
            if (queryAllRequests != null) {
                queryAllRequests.close();
            }
            if (insertIntoRequests != null) {
                insertIntoRequests.close();
            }
            if (searchByPartOfTitle != null) {
                searchByPartOfTitle.close();
            }
            if (searchByAuthor != null) {
                searchByAuthor.close();
            }
            if (searchBoardGameByTitle != null) {
                searchBoardGameByTitle.close();
            }
            if(searchByNumOfPlayers != null){
                searchByNumOfPlayers.close();
            }
            if(buyABook != null){
                buyABook.close();
            }
            if(buyAnEbook != null){
                buyAnEbook.close();
            }
            if(buyABoardGame != null){
                buyABoardGame.close();
            }
            if(deleteARequest != null){
                deleteARequest.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Book> queryBooks() {
        try {
            ResultSet results = queryAllBooks.executeQuery();

            List<Book> books = new ArrayList<>();

            while (results.next()) {
                Book book = new Book();
                book.setId(results.getInt(INDEX_BOOK_ID));
                book.setTitle(results.getString(INDEX_BOOK_TITLE));
                book.setAuthor(results.getString(INDEX_BOOK_AUTHOR));
                book.setQuantity(results.getInt(INDEX_BOOK_QUANTITY));
                book.setPrice(results.getDouble(INDEX_BOOK_PRICE));

                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("Duplicates")
    public boolean insertBooks(Book book) {
        try {
            insertIntoBooks.setInt(1, book.getId());
            insertIntoBooks.setString(2, book.getTitle());
            insertIntoBooks.setString(3, book.getAuthor());
            insertIntoBooks.setInt(4, book.getQuantity());
            insertIntoBooks.setDouble(5, book.getPrice());
            insertIntoBooks.setInt(6, book.getQuantity());

            deleteARequest.setString(1, "books");
            deleteARequest.setString(2, book.getTitle());
            deleteARequest.setString(3, book.getAuthor());
            deleteARequest.executeUpdate();

            int affectedRows = insertIntoBooks.executeUpdate();
            if (affectedRows != 1) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }


    public List<eBook> queryeBooks() {
        try {
            ResultSet results = queryAlleBooks.executeQuery();

            List<eBook> ebooks = new ArrayList<>();

            while (results.next()) {
                eBook ebook = new eBook();
                ebook.setId(results.getInt(INDEX_EBOOK_ID));
                ebook.setTitle(results.getString(INDEX_EBOOK_TITLE));
                ebook.setAuthor(results.getString(INDEX_EBOOK_AUTHOR));
                ebook.setQuantity(results.getInt(INDEX_EBOOK_QUANTITY));
                ebook.setPrice(results.getDouble(INDEX_EBOOK_PRICE));

                ebooks.add(ebook);
            }
            return ebooks;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("Duplicates")
    public boolean inserteBooks(eBook ebook, int quont) {
        try {
            insertIntoeBooks.setInt(1, ebook.getId());
            insertIntoeBooks.setString(2, ebook.getTitle());
            insertIntoeBooks.setString(3, ebook.getAuthor());
            insertIntoeBooks.setInt(4, ebook.getQuantity());
            insertIntoeBooks.setDouble(5, ebook.getPrice());
            insertIntoeBooks.setInt(6, quont);

            deleteARequest.setString(1, "еbooks");
            deleteARequest.setString(2, ebook.getTitle());
            deleteARequest.setString(3, ebook.getAuthor());
            deleteARequest.executeUpdate();

            int affectedRows = insertIntoeBooks.executeUpdate();
            if (affectedRows != 1) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    public List<BoardGame> queryBoardGame() {
        try {
            ResultSet results = queryAllBoardGames.executeQuery();

            List<BoardGame> boardGames = new ArrayList<>();

            while (results.next()) {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    @SuppressWarnings("Duplicates")
    public boolean insertBoardGames(BoardGame boardGame, int quont) {
        try {
            insertIntoBoardGames.setInt(1, boardGame.getId());
            insertIntoBoardGames.setString(2, boardGame.getTitle());
            insertIntoBoardGames.setInt(3, boardGame.getMinPlayers());
            insertIntoBoardGames.setInt(4, boardGame.getMaxPlayers());
            insertIntoBoardGames.setDouble(5, boardGame.getPrice());
            insertIntoBoardGames.setInt(6, boardGame.getQuantity());
            insertIntoBoardGames.setInt(7, quont);

            deleteARequest.setString(1, "boardgames");
            deleteARequest.setString(2, boardGame.getTitle());

            deleteARequest.executeUpdate();
            int affectedRows = insertIntoBoardGames.executeUpdate();
            if (affectedRows != 1) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }

    public boolean insertRequest(Request request) {
        try {
            insertIntoRequests.setInt(1, request.getId());
            insertIntoRequests.setInt(2, request.getClientid());
            insertIntoRequests.setString(3, request.getItemName());
            insertIntoRequests.setString(4, request.getItemAuthor());
            insertIntoRequests.setString(5, request.getProduct());
            int affectedRows = insertIntoRequests.executeUpdate();
            if (affectedRows != 1) {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public List<Request> queryRequest() {
        try {
            ResultSet results = queryAllRequests.executeQuery();

            List<Request> requests = new ArrayList<>();

            while (results.next()) {
                Request request = new Request();
                request.setId(results.getInt(INDEX_REQUESTS_ID));
                request.setClientid(results.getInt(INDEX_REQUESTS_CLIENTID));
                request.setProduct(results.getString(INDEX_REQUESTS_TYPE));
                request.setItemName(results.getString(INDEX_REQUESTS_ITEM_NAME));
                request.setItemAuthor(results.getString(INDEX_REQUESTS_ITEM_AUTHOR));

                requests.add(request);
            }
            return requests;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @SuppressWarnings("Duplicates")
    public List<Book> searchByTitle(String title) {
        try {
            searchByPartOfTitle.setString(1, "%" + title + "%");
            ResultSet results = searchByPartOfTitle.executeQuery();
            List<Book> books = new ArrayList<>();

            while (results.next()) {
                Book book = new Book();

                book.setId(results.getInt(INDEX_BOOK_ID));
                book.setTitle(results.getString(INDEX_BOOK_TITLE));
                book.setAuthor(results.getString(INDEX_BOOK_AUTHOR));
                book.setPrice(results.getDouble(INDEX_BOOK_PRICE));

                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

    @SuppressWarnings("Duplicates")
    public List<Book> searchByAuthor(String author) {
        try {
            searchByAuthor.setString(1, author);
            ResultSet results = searchByAuthor.executeQuery();
            List<Book> books = new ArrayList<>();
            while (results.next()) {
                Book book = new Book();

                book.setId(results.getInt(INDEX_BOOK_ID));
                book.setTitle(results.getString(INDEX_BOOK_TITLE));
                book.setAuthor(results.getString(INDEX_BOOK_AUTHOR));
                book.setPrice(results.getDouble(INDEX_BOOK_PRICE));

                books.add(book);
            }
            return books;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
    @SuppressWarnings("Duplicates")
    public List<BoardGame> searchBoardGameByTitle(String title){
        try{
            searchBoardGameByTitle.setString(1, "%" + title + "%");
            ResultSet results = searchBoardGameByTitle.executeQuery();
            List<BoardGame> boardGames = new ArrayList<>();

            while(results.next()){
                BoardGame boardgame = new BoardGame();

                boardgame.setId(results.getInt(INDEX_BOARDGAMES_ID));
                boardgame.setTitle(results.getString(INDEX_BOARDGAMES_TITLE));
                boardgame.setMinPlayers(results.getInt(INDEX_BOARDGAMES_MINPLAYERS));
                boardgame.setMaxPlayers(results.getInt(INDEX_BOARDGAMES_MAXPLAYERS));
                boardgame.setPrice(results.getInt(INDEX_BOARDGAMES_PRICE));
                boardGames.add(boardgame);
            }
            return boardGames;
            }catch (SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @SuppressWarnings("Duplicates")
    public List<BoardGame> searchByNumOfPlayers(int numPlayers) {
        try {
            searchByNumOfPlayers.setInt(1, numPlayers);
            ResultSet results = searchByNumOfPlayers.executeQuery();
            List<BoardGame> boardGames = new ArrayList<>();

            while (results.next()) {
                BoardGame boardgame = new BoardGame();

                boardgame.setId(results.getInt(INDEX_BOARDGAMES_ID));
                boardgame.setTitle(results.getString(INDEX_BOARDGAMES_TITLE));
                boardgame.setMinPlayers(results.getInt(INDEX_BOARDGAMES_MINPLAYERS));
                boardgame.setMaxPlayers(results.getInt(INDEX_BOARDGAMES_MAXPLAYERS));
                boardgame.setPrice(results.getInt(INDEX_BOARDGAMES_PRICE));
                boardGames.add(boardgame);
            }
            return boardGames;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    @SuppressWarnings("Duplicates")
    public void buyABook(int id, int quantity){
        try{

            buyABook.setInt(1, quantity);
            buyABook.setInt(2, id);

            buyABook.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    @SuppressWarnings("Duplicates")
    public void buyAnEBook(int id, int quantity){
        try{

            buyAnEbook.setInt(1, quantity);
            buyAnEbook.setInt(2, id);

            buyAnEbook.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    @SuppressWarnings("Duplicates")
    public void buyABoardGame(int id, int quantity){
        try{

            buyABoardGame.setInt(1, quantity);
            buyABoardGame.setInt(2, id);

            buyABoardGame.executeUpdate();

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }





}







