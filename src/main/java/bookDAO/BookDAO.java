package bookDAO;

import book.Book;
import book.Genre;
import util.UtilDatabase;

import java.sql.*;

// ADD BOOK
public class BookDAO {
    public void addBook(Book b1){
        String sql= "INSERT INTO books (title,author,genre,price,published_Year,Created_at) VALUES(?,?,?,?,?,?) ";
        try (Connection connection= UtilDatabase.getConnection();
             PreparedStatement preparedStatement= connection.prepareStatement(sql);
        ){
            preparedStatement.setString(1, b1.getTitle());
            preparedStatement.setString(2, b1.getAuthor());
            preparedStatement.setString(3,b1.getGenre().name());// to change to String
            preparedStatement.setDouble(4,b1.getPrice());
            preparedStatement.setInt(5,b1.getPublishedYear());
            preparedStatement.setTimestamp(6,b1.getCreatedAt());
            preparedStatement.executeUpdate();
            System.out.println("BOOK ADDED");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    // READ BOOK
    public void getAllBooks()throws Exception{
        String sql= "SELECT* FROM books";

        try (Connection connection=UtilDatabase.getConnection();
              PreparedStatement preparedStatement=connection.prepareStatement(sql);
             ResultSet resultSet=preparedStatement.executeQuery())
        {
            while (resultSet.next()){
              int id=resultSet.getInt("id");
              String title=resultSet.getString("title");
              String author=resultSet.getString("author");
              Genre genre = Genre.valueOf(resultSet.getString("genre"));
              double price=resultSet.getDouble("price");
              int publishedYear=resultSet.getInt("published_year");
              Timestamp createdAt=resultSet.getTimestamp("created_at");

                Book book=new Book(id,title,author,genre,price,publishedYear,createdAt);

                System.out.println(book.getTitle()+"  "+ book.getAuthor()+ " "+ book.getGenre()+" "+ book.getPrice()+" "+ book.getPublishedYear()+ "  ");
                    }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    //UPDATE BOOK
    public void updateBook(Book book)throws Exception{
        String sql="UPDATE books SET title=?, author=?, genre=?, price=?, published_year=? ,created_at=? WHERE id =? ";

        try (Connection connection = UtilDatabase.getConnection();
             PreparedStatement preparedStatement= connection.prepareStatement(sql)
        ){

            preparedStatement.setString(1,book.getTitle());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setString(3,book.getGenre().name());
            preparedStatement.setDouble(4,book.getPrice());
            preparedStatement.setInt(5,book.getPublishedYear());
            preparedStatement.setTimestamp(6,book.getCreatedAt());
            preparedStatement.setInt(7,book.getId());

            int rows= preparedStatement.executeUpdate();
            if (rows>0){
                System.out.println("book updated");
            }else {
                System.out.println("book not updated");
            }

           // preparedStatement.executeUpdate();
          //  System.out.println(" BOOK UPDATED");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    //DELETE BOOK
    public void deleteBook(int id)throws Exception {
        String sql=" DELETE FROM books WHERE id=?";
        try (Connection connection=UtilDatabase.getConnection();
             PreparedStatement preparedStatement=connection.prepareStatement(sql);
                ){preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println(" BOOK DELETED");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
