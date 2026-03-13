import book.Book;
import book.Genre;
import bookDAO.BookDAO;
import java.sql.Timestamp;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDAO bookDAO = new BookDAO();
        while (true) {
            System.out.println("=====BOOK MENU=====");
            System.out.println(" 1.   ADD BOOK   ");
            System.out.println(" 2.   SHOW ALL BOOKS  ");
            System.out.println(" 3.   UPDATE BOOK    ");
            System.out.println(" 4.   DELETE BOOK    ");
            System.out.println(" 5.   EXIT     ");
            System.out.println(" ===   ENTER YOUR CHOICE  === ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        Book book = new Book();
                        System.out.println(" ENTER ID ");
                        try {
                            book.setId(scanner.nextInt());
                        }catch (RuntimeException e){
                            System.out.println(e.getMessage());
                        }
                        scanner.nextLine();

                        System.out.println(" ENTER TITLE ");
                        book.setTitle(scanner.nextLine());

                        System.out.println(" ENTER AUTHOR");
                        book.setAuthor(scanner.nextLine());

                       System.out.println(" ENTER PRICE");
                       try {
                           book.setPrice(scanner.nextDouble());
                           scanner.nextLine();

                       }catch (RuntimeException e){
                           System.out.println("INVALID PRICE ");
                       }

                        System.out.println(" Available GENRES: ");
                        for (Genre g : Genre.values()) {
                            System.out.println("   " + g);
                        }

                        System.out.println("ENTER GENRE");
                        try {
                            book.setGenre(Genre.valueOf(scanner.nextLine()));
                        }catch (RuntimeException e){
                            System.out.println(" INVALID GENRE");
                        }

                        System.out.println(" ENTER Published YEAR");
                        book.setPublishedYear(scanner.nextInt());

                        book.setCreatedAt(new Timestamp(System.currentTimeMillis()));

                        System.out.println("BOOK ADDED SUCCESSFULLY ");

                        bookDAO.addBook(book);
                        break;

                    case 2:
                        bookDAO.getAllBooks();
                        break;

                    case 3:
                        Book updateBook =new Book();
                        System.out.println(" Enter book ID");
                        updateBook.setId(scanner.nextInt());
                        System.out.println("Enter new title");
                        updateBook.setTitle(scanner.nextLine());
                        System.out.println(" Enter new author");

                        System.out.println(" Enter Available genre");
                       for (Genre g : Genre.values()){
                           System.out.println("   "+ g);
                       }
                        System.out.println(" Enter new Genre");
                        updateBook.setGenre(Genre.valueOf(scanner.nextLine()));
                        System.out.println(" Enter new price: ");
                        updateBook.setPrice(scanner.nextDouble());
                        System.out.println(" Enter new published Year");
                        updateBook.setPublishedYear(scanner.nextInt());
                        updateBook.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                        bookDAO.updateBook(updateBook);
                        System.out.println(" BOOK UPDATED SUCCESSFULLY");
                        break;
                    case  4:
                        BookDAO deleteBook = new BookDAO();
                        System.out.println("Enter Book ID to delete : ");
                        int id=scanner.nextInt();
                       bookDAO.deleteBook(id);
                        System.out.println(" THE BOOK DELETED SUCCESSFULLY ");
                        break;
                    case  5:
                        System.out.println(" PROGRAM CLOSED ");
                        scanner.close();
                        return;

                    default:
                        System.out.println(" INVALID CHOICE");

                }


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }


    }
}