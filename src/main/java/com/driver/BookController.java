package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

   HashMap<Integer,Book> books=new HashMap<>();
    private List<Book> bookList;
    private int id;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    //not ok
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        book.setId(this.id);
        this.id=this.id+1;
        bookList.add(book);

        return new ResponseEntity<Book>(book,HttpStatus.CREATED);
    }
    // get request /get-book-by-id/{id}
    // pass id as path variable
    // getBookById()
    //ok
    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Integer id) {
//        Book book=bookList.get(id-1);
        for (Book book : bookList) {
            if (book.getId() == id) {
                return new ResponseEntity<Book>(book, HttpStatus.OK);
            }
            //}
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()

    //ok
    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity deleteBookById(@PathVariable Integer id) {
        for(int i=0;i<bookList.size();i++){
            if(bookList.get(i).getId()==id){
                bookList.remove(i);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    // get request /get-all-books
    // getAllBooks()
    //ok
            @GetMapping("/get-all-books")
            public ResponseEntity<List<Book>>getAllBooks(){
              // List<Book> listOfBooks= new ArrayList<>();
              //  for(Book book: bookList){
                 //   listOfBooks.add(book);
               // }

                return new ResponseEntity<List<Book>>(bookList,HttpStatus.OK);
                }

    // delete request /delete-all-books
    // deleteAllBooks()
    //ok
    @DeleteMapping("/delete-all-books")
    public ResponseEntity deleteAllBooks() {
        bookList.clear();
        id=1;
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()
    @GetMapping("/get-books-by-author")
    public ResponseEntity getBooksByAuthor(@RequestParam String author){
        for(Book book: bookList){
            if(book.getAuthor().equals(author)){
                return new ResponseEntity<>(book,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()
    @GetMapping("/get-books-by-genre")
    public ResponseEntity getBooksByGenre(@RequestParam String genre){
        for(Book book:bookList){
            if(book.getGenre().equals(genre)){
                return new ResponseEntity<>(book,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}

