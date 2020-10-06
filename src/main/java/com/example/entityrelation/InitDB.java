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

            // Team
            Team team = new Team("teamA");
            em.persist(team);

            Team team2 = new Team("teamB");
            em.persist(team2);

            Team team3 = new Team("teamC");
            em.persist(team3);

            //Member
            Member member = new Member("testA1",20,"부산","부산순환로1011","111-11", team);
            em.persist(member);

            Member member2 = new Member("testA2",32,"청주","청주순환로1220","860-21", team);
            em.persist(member2);

            Member member3 = new Member("testB1",28,"서울","서울순환로2231","432-10", team2);
            em.persist(member3);

            Member member4 = new Member("testB2",21,"대구","대구순환로7231","722-73", team2);
            em.persist(member4);

            //Delivery
            Delivery delivery = new Delivery(DeliveryStatus.READY);
            em.persist(delivery);

            Delivery delivery2 = new Delivery(DeliveryStatus.COMP);
            em.persist(delivery2);

            Delivery delivery3 = new Delivery(DeliveryStatus.PROC);
            em.persist(delivery3);

            Delivery delivery4 = new Delivery(DeliveryStatus.PROC);
            em.persist(delivery4);


            //Order
            Order order = new Order(member,delivery,LocalDateTime.now());
            em.persist(order);

            Order order2 = new Order(member2,delivery2,LocalDateTime.now());
            em.persist(order2);

            Order order3 = new Order(member3,delivery3,LocalDateTime.now());
            em.persist(order3);

            Order order4 = new Order(member4,delivery4,LocalDateTime.now());
            em.persist(order4);


            //Book
            Book book1 = new Book("BookA", 10000, 100, "BookAAuthor", "BookA10001");
            em.persist(book1);

            Album album1 = new Album("AlbumA", 150000, 50, "AlbumAArtist", "AlbumAEtc");
            em.persist(album1);

            Movie movie1 = new Movie("MovieA", 200000, 25, "MovieADirector", "MovieAActor" );
            em.persist(movie1);

            //OrderItem
            OrderItem orderItem = new OrderItem(order, book1.getPrice(), book1, 1);
            em.persist(orderItem);

            OrderItem orderItem2 = new OrderItem(order, movie1.getPrice(), movie1, 1);
            em.persist(orderItem2);

            OrderItem orderItem3 = new OrderItem(order2, album1.getPrice(), album1, 1);
            em.persist(orderItem3);

            OrderItem orderItem4 = new OrderItem(order2, album1.getPrice(), book1, 1);
            em.persist(orderItem4);

            OrderItem orderItem5 = new OrderItem(order3, album1.getPrice(), movie1, 1);
            em.persist(orderItem5);

            OrderItem orderItem6 = new OrderItem(order4, album1.getPrice(), album1, 1);
            em.persist(orderItem6);

            //Category
            Category category = new Category("CategoryA");
            em.persist(category);

            Category category2 = new Category("CategoryB");
            em.persist(category2);

            Category category3 = new Category("CategoryC");
            em.persist(category3);

            //CategoryItem
            CategoryItem categoryItem = new CategoryItem(category, book1);
            em.persist(categoryItem);

            CategoryItem categoryItem2 = new CategoryItem(category, album1);
            em.persist(categoryItem2);

            CategoryItem categoryItem3 = new CategoryItem(category2, movie1);
            em.persist(categoryItem3);
        }


        public void dbInit2() {

          /*  Team team = new Team("teamB");
            em.persist(team);

            Member member2 = new Member("testB",35,"아산", "순천향로1913", "331-9", team);
            em.persist(member2);

            Delivery delivery = new Delivery(DeliveryStatus.READY);
            em.persist(delivery);

            Order order3 = new Order(member2, delivery, LocalDateTime.now() );
            em.persist(order3);

            Book book2 = new Book("BookB", 12000, 90, "BookBAuthor", "BookB10001" );
            em.persist(book2);

            Album album2 = new Album("AlbumB", 151000, 40, "AlbumBArtist","AlbumBEtc" );
            em.persist(album2);

            Movie movie2 = new Movie("MovieB", 220000, 20, "MovieBActor", "MovieBDirector");
            em.persist(movie2);

            OrderItem orderItem3 = new OrderItem(order3, movie2.getPrice(), movie2, 1);
            em.persist(orderItem3);

            Category category3 = new Category("CategoryC");
            em.persist(category3);

            CategoryItem categoryItem4 = new CategoryItem(category3, book2);
            em.persist(categoryItem4);

            CategoryItem categoryItem5 = new CategoryItem(category3, album2);
            em.persist(categoryItem5);

            CategoryItem categoryItem6 = new CategoryItem(category3, movie2);
            em.persist(categoryItem6);

            CategoryItem categoryItem7 = new CategoryItem(category3, album2);
            em.persist(categoryItem7);*/

        }

    }
}
