package com.example.entityrelation;

import com.example.entityrelation.domain.*;
import com.example.entityrelation.domain.item.Album;
import com.example.entityrelation.domain.item.Book;
import com.example.entityrelation.domain.item.Movie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {

//            Member member = new Member();
//            member.setName("testA");
//            member.setAddress(new Address("서울", "남부순환로1430", "860-1"));

            Member member = new Member("testA","서울","남부순환로1430","860-1");
            em.persist(member);


//            Delivery delivery = new Delivery();
//            delivery.setStatus(DeliveryStatus.READY);

            Delivery delivery = new Delivery(DeliveryStatus.READY);
            em.persist(delivery);

//            Delivery delivery2 = new Delivery();
//            delivery2.setStatus(DeliveryStatus.COMP);
//            em.persist(delivery2);

            Delivery delivery2 = new Delivery(DeliveryStatus.COMP);
            em.persist(delivery2);

//            Order order = new Order();
//            order.setMember(member);
//            order.setDelivery(delivery);
//            order.setOrderDate(LocalDateTime.now());


            Order order = new Order(member,delivery,LocalDateTime.now());
            em.persist(order);

   /*         Order order2 = new Order();
            order2.setMember(member);
            order2.setDelivery(delivery2);
            order2.setOrderDate(LocalDateTime.now());
            em.persist(order2);*/

            Order order2 = new Order(member,delivery2,LocalDateTime.now());
            em.persist(order2);

          /*  Book book1 = new Book();
            book1.setName("BookA");
            book1.setPrice(10000);
            book1.setStockQuantity(100);
            book1.setAuthor("BookAAuthor");
            book1.setIsbn("BookA10001");
            em.persist(book1);*/

            Book book1 = new Book("BookA", 10000, 100, "BookAAuthor", "BookA10001");
            em.persist(book1);

//            Album album1 = new Album();
//            album1.setName("AlbumA");
//            album1.setPrice(150000);
//            album1.setStockQuantity(50);
//            album1.setArtist("AlbumAArtist");
//            album1.setEtc("AlbumAEtc");
//            em.persist(album1);

            Album album1 = new Album("AlbumA", 150000, 50, "AlbumAArtist", "AlbumAEtc");
            em.persist(album1);

//            Movie movie1 = new Movie();
//            movie1.setName("MovieA");
//            movie1.setPrice(200000);
//            movie1.setStockQuantity(25);
//            movie1.setActor("MovieAActor");
//            movie1.setDirector("MovieADirector");
//            em.persist(movie1);

            Movie movie1 = new Movie("MovieA", 200000, 25, "MovieADirector", "MovieAActor" );
            em.persist(movie1);


//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrder(order);
//            orderItem.setOrderPrice(book1.getPrice());
//            orderItem.setItem(book1);
//            orderItem.setCount(1);
//            em.persist(orderItem);

            OrderItem orderItem = new OrderItem(order, book1.getPrice(), book1, 1);
            em.persist(orderItem);

//            OrderItem orderItem2 = new OrderItem();
//            orderItem2.setOrder(order);
//            orderItem2.setOrderPrice(movie1.getPrice());
//            orderItem2.setItem(movie1);
//            orderItem2.setCount(1);
//            em.persist(orderItem2);

            OrderItem orderItem2 = new OrderItem(order, movie1.getPrice(), movie1, 1);
            em.persist(orderItem);

//            Category category = new Category();
//            category.setName("CategoryA");
//            em.persist(category);

            Category category = new Category("CategoryA");
            em.persist(category);

//            Category category2 = new Category();
//            category2.setName("CategoryB");
//            em.persist(category2);

            Category category2 = new Category("CategoryB");
            em.persist(category2);

//            CategoryItem categoryItem = new CategoryItem();
//            categoryItem.setCategory(category);
//            categoryItem.setItem(book1);
//            em.persist(categoryItem);

            CategoryItem categoryItem = new CategoryItem(category, book1);
            em.persist(categoryItem);

//            CategoryItem categoryItem2 = new CategoryItem();
//            categoryItem2.setCategory(category);
//            categoryItem2.setItem(album1);
//            em.persist(categoryItem2);

            CategoryItem categoryItem2 = new CategoryItem(category, album1);
            em.persist(categoryItem2);


    /*        CategoryItem categoryItem3 = new CategoryItem();
            categoryItem3.setCategory(category2);
            categoryItem3.setItem(movie1);
            em.persist(categoryItem3);*/

            CategoryItem categoryItem3 = new CategoryItem(category2, movie1);
            em.persist(categoryItem3);
        }


        public void dbInit2() {

//            Member member2 = new Member();
//            member2.setName("testB");
//            member2.setAddress(new Address("아산", "순천향로1913", "331-9"));
            Member member2 = new Member("testB","아산", "순천향로1913", "331-9");
            em.persist(member2);

//            Delivery delivery = new Delivery();
//            delivery.setStatus(DeliveryStatus.READY);
//            em.persist(delivery);
            Delivery delivery = new Delivery(DeliveryStatus.READY);
            em.persist(delivery);

//            Order order3 = new Order();
//            order3.setMember(member2);
//            order3.setDelivery(delivery);
//            order3.setOrderDate(LocalDateTime.now());
//            em.persist(order3);

            Order order3 = new Order(member2, delivery, LocalDateTime.now() );
            em.persist(order3);


//            Book book2 = new Book();
//            book2.setName("BookB");
//            book2.setPrice(12000);
//            book2.setStockQuantity(90);
//            book2.setAuthor("BookBAuthor");
//            book2.setIsbn("BookB10001");
//            em.persist(book2);

            Book book2 = new Book("BookB", 12000, 90, "BookBAuthor", "BookB10001" );
            em.persist(book2);

//            Album album2 = new Album();
//            album2.setName("AlbumB");
//            album2.setPrice(151000);
//            album2.setStockQuantity(40);
//            album2.setArtist("AlbumBArtist");
//            album2.setEtc("AlbumBEtc");
//            em.persist(album2);

            Album album2 = new Album("AlbumB", 151000, 40, "AlbumBArtist","AlbumBEtc" );
            em.persist(album2);

//            Movie movie2 = new Movie();
//            movie2.setName("MovieB");
//            movie2.setPrice(220000);
//            movie2.setStockQuantity(20);
//            movie2.setActor("MovieBActor");
//            movie2.setDirector("MovieBDirector");
//            em.persist(movie2);

            Movie movie2 = new Movie("MovieB", 220000, 20, "MovieBActor", "MovieBDirector");
            em.persist(movie2);


//            OrderItem orderItem3 = new OrderItem();
//            orderItem3.setOrder(order3);
//            orderItem3.setOrderPrice(movie2.getPrice());
//            orderItem3.setItem(movie2);
//            orderItem3.setCount(1);
//            em.persist(orderItem3);

            OrderItem orderItem3 = new OrderItem(order3, movie2.getPrice(), movie2, 1);
            em.persist(orderItem3);

//            Category category3 = new Category();
//            category3.setName("CategoryC");
//            em.persist(category3);
//
            Category category3 = new Category("CategoryC");
            em.persist(category3);

//            CategoryItem categoryItem4 = new CategoryItem();
//            categoryItem4.setCategory(category3);
//            categoryItem4.setItem(book2);
//            em.persist(categoryItem4);

            CategoryItem categoryItem4 = new CategoryItem(category3, book2);
            em.persist(categoryItem4);

//            CategoryItem categoryItem5 = new CategoryItem();
//            categoryItem5.setCategory(category3);
//            categoryItem5.setItem(album2);
//            em.persist(categoryItem5);

            CategoryItem categoryItem5 = new CategoryItem(category3, album2);
            em.persist(categoryItem5);

//            CategoryItem categoryItem6 = new CategoryItem();
//            categoryItem6.setCategory(category3);
//            categoryItem6.setItem(movie2);
//            em.persist(categoryItem6);

            CategoryItem categoryItem6 = new CategoryItem(category3, movie2);
            em.persist(categoryItem6);

//            CategoryItem categoryItem7 = new CategoryItem();
//            categoryItem7.setCategory(category3);
//            categoryItem7.setItem(album2);
//            em.persist(categoryItem7);

            CategoryItem categoryItem7 = new CategoryItem(category3, album2);
            em.persist(categoryItem7);

        }

    }
}
