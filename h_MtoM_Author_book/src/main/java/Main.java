import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = config.buildSessionFactory();


        // --- CREATE ---
         insertData(sessionFactory); //for manual entry data
        insertAuthorsAndBooksConsole(sessionFactory); //by console input

       // --- READ ---
            fetchAuthors(sessionFactory);
            fetchBooks(sessionFactory);
          searchAuthorByName(sessionFactory);
           searchAuthorsByBook(sessionFactory);

        // --- UPDATE ---
         updateAuthorAddBook(sessionFactory, 1, "Fantastic Beasts");
        updateAuthorBooksConsole(sessionFactory);


        // --- DELETE ---
       deleteAuthor(sessionFactory, 2);
        deleteBookByAuthorOrBook(sessionFactory);

        sessionFactory.close();
   }

        // Insert1 Authors and Books Method 1
    static void insertData(SessionFactory sf) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Author a1 = new Author("J.K. Rowling");
        Author a2 = new Author("Stephen King");
        Author a3 = new Author("Agatha Christie");
        Author a4 = new Author("Neil Gaiman");

        Book b1 = new Book("Harry Potter");
        Book b2 = new Book("The Shining");
        Book b3 = new Book("Murder on the Orient Express");
        Book b4 = new Book("Good Omens");
        Book b5 = new Book("Fantastic Beasts");
        Book b6 = new Book("IT");

        // Relationships
        a1.getBooks().add(b1); // Rowling wrote Harry Potter
        a2.getBooks().add(b2); // King wrote The Shining
        a2.getBooks().add(b1); // King also linked to Harry Potter (example)

        a1.getBooks().add(b5);
        a2.getBooks().add(b6);
        a3.getBooks().add(b3);
        a4.getBooks().add(b4);

        // For bidirectional mapping (optional, if you want consistency)
        b1.getAuthors().add(a1);
        b1.getAuthors().add(a2);
        b2.getAuthors().add(a2);
        b3.getAuthors().add(a3);
        b4.getAuthors().add(a4);
        b5.getAuthors().add(a1);
        b6.getAuthors().add(a2);

        session.persist(a1);
        session.persist(a2);
        session.persist(a3);
        session.persist(a4);

        session.persist(b1);
        session.persist(b2);
        session.persist(b3);
        session.persist(b4);
        session.persist(b5);
        session.persist(b6);

        tx.commit();
        session.close();
        System.out.println("✅ Data Inserted!");
    }

        // Insert2 Authors and Books via Console Input
        static void insertAuthorsAndBooksConsole(SessionFactory sf) {
            Scanner sc = new Scanner(System.in);
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();

            System.out.print("Enter number of Authors to insert: ");
            int authorCount = sc.nextInt();
            sc.nextLine(); // consume newline

            for (int i = 1; i <= authorCount; i++) {
                System.out.print("Enter Author " + i + " name: ");
                String authorName = sc.nextLine();
                Author author = new Author(authorName);

                System.out.print("Enter number of books for " + authorName + ": ");
                int bookCount = sc.nextInt();
                sc.nextLine(); // consume newline

                for (int j = 1; j <= bookCount; j++) {
                    System.out.print("Enter Book " + j + " title: ");
                    String bookTitle = sc.nextLine();

                    // Check if book already exists in DB using HQL
                    Query<Book> query = session.createQuery("FROM Book b WHERE b.title = :title", Book.class);
                    query.setParameter("title", bookTitle);
                    Book book = query.uniqueResult();

                    if (book == null) {
                        book = new Book(bookTitle); // create new book if not exists
                    }

                    // Set ManyToMany relationship
                    author.getBooks().add(book);
                    book.getAuthors().add(author);

                    session.persist(book); // save or update book
                }

                session.persist(author); // save author
            }

            tx.commit();
            session.close();

            System.out.println("✅ Authors and Books inserted successfully!");
        }




    // Fetch all authors with their books
         static void fetchAuthors(SessionFactory sf) {
            Session session = sf.openSession();
            Transaction tx = session.beginTransaction();
            List<Author> authors = session.createQuery("FROM Author", Author.class).list();

            for (Author a : authors) {
                System.out.println("Author: " + a.getName());
                for (Book b : a.getBooks()) {  //Including books also
                    System.out.println("  -> Book: " + b.getTitle());
                }
            }
            tx.commit();
            session.close();

        }


    // Fetch all books with their authors
     static void fetchBooks(SessionFactory sf) {
        Session session = sf.openSession();
        List<Book> books = session.createQuery("FROM Book", Book.class).list();

        for (Book b : books) {
            System.out.println("Book: " + b.getTitle());
            for (Author a : b.getAuthors()) {   // Including Author also
                System.out.println("  -> Author: " + a.getName());
            }
        }
        session.close();
    }

    // Search Author by Name (Console Input)
    static void searchAuthorByName(SessionFactory sf) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Author Name to search: ");
        String authorName = sc.nextLine();

        Session session = sf.openSession();

        // HQL Query
        String hql = "FROM Author a WHERE a.name = :name";
        Query<Author> query = session.createQuery(hql, Author.class);
        query.setParameter("name", authorName);

        List<Author> authors = query.list();

        if (!authors.isEmpty()) {
            System.out.println("✅ Author found in database!");
            for (Author a : authors) {
                System.out.println("Author: " + a.getName());
                for (Book b : a.getBooks()) {
                    System.out.println("  -> Book: " + b.getTitle());
                }
            }
        } else {
            System.out.println("❌ Author not found in database!");
        }

        session.close();
    }


      // Search Authors by Book Title (Console Input)
    static void searchAuthorsByBook(SessionFactory sf) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Book Title to search its authors: ");
        String bookTitle = sc.nextLine();

        Session session = sf.openSession();

        // HQL Query
        String hql = "FROM Book b WHERE b.title = :title";
        Query<Book> query = session.createQuery(hql, Book.class);
        query.setParameter("title", bookTitle);

        Book book = query.uniqueResult();

        if (book != null) {
            System.out.println("✅ Book found: " + book.getTitle());
            System.out.println("Authors of this book:");
            for (Author a : book.getAuthors()) {
                System.out.println(" -> " + a.getName());
            }
        } else {
            System.out.println("❌ Book not found in database!");
        }

        session.close();
    }


    // Update: Add new book to an existing author
    static void updateAuthorAddBook(SessionFactory sf, int authorId, String bookTitle) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        Author author = session.get(Author.class, authorId);
        if (author != null) {
            Book newBook = new Book(bookTitle);
            author.getBooks().add(newBook);
            session.update(author);
            System.out.println("✅ Added new book '" + bookTitle + "' to " + author.getName());
        } else {
            System.out.println("❌ Author not found!");
        }

        tx.commit();
        session.close();
    }


    // Update2 Author by adding a new Book
    static void updateAuthorBooksConsole(SessionFactory sf) {
        Scanner sc = new Scanner(System.in);
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Author ID to update: ");
        int authorId = sc.nextInt();
        sc.nextLine(); // consume newline

    // Fetch the Author
        Author author = session.get(Author.class, authorId);

        if (author != null) {
            System.out.print("Enter Book title to add to " + author.getName() + ": ");
            String bookTitle = sc.nextLine();

            // Check if the Book already exists
            Query<Book> query = session.createQuery("FROM Book b WHERE b.title = :title", Book.class);
            query.setParameter("title", bookTitle);
            Book book = query.uniqueResult();

            if (book == null) {
                book = new Book(bookTitle); // Create new book if not exists
            }

            // Set ManyToMany relationship
            author.getBooks().add(book);
            book.getAuthors().add(author);

            // Persist changes
            session.persist(author);
            session.persist(book);

            tx.commit();
            System.out.println("✅ Book added/updated for Author: " + author.getName());
        } else {
            System.out.println("❌ Author not found with ID: " + authorId);
        }

        session.close();
    }



//    // Delete author by ID (HQL)
    static void deleteAuthor(SessionFactory sf, int authorId) {
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        String hql = "DELETE FROM Author WHERE id = :id";
        int deleted = session.createMutationQuery(hql)
                .setParameter("id", authorId)
                .executeUpdate();

        tx.commit();
        session.close();
        System.out.println("✅ Deleted Authors: " + deleted);
    }

    // Delete book by Author ID or Book ID
    static void deleteBookByAuthorOrBook(SessionFactory sf) {
        Scanner sc = new Scanner(System.in);
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter Author ID to delete their books (or 0 to skip): ");
        int authorId = sc.nextInt();
        sc.nextLine(); // consume newline

        if (authorId != 0) {
            // Try to fetch author
            Author author = session.get(Author.class, authorId);

            if (author != null) {
                // Delete all books of this author
                for (Book b : author.getBooks()) {
                    // Remove association with authors
                    b.getAuthors().remove(author);
                    session.delete(b); // delete book
                }
                tx.commit();
                System.out.println("✅ All books of Author '" + author.getName() + "' deleted!");
            } else {
                System.out.println("❌ Author not found with ID: " + authorId);
            }
        } else {
            // If no author id, delete by Book ID
            System.out.print("Enter Book ID to delete: ");
            int bookId = sc.nextInt();

            Book book = session.get(Book.class, bookId);
            if (book != null) {
                // Remove book from all associated authors
                for (Author a : book.getAuthors()) {
                    a.getBooks().remove(book);
                }
                session.delete(book);
                tx.commit();
                System.out.println("✅ Book '" + book.getTitle() + "' deleted successfully!");
            } else {
                System.out.println("❌ Book not found with ID: " + bookId);
            }
        }

        session.close();
    }
}

