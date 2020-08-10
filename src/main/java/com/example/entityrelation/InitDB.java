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
            Member member = new Member();
            member.setName("testA");
            member.setAddress(new Address("서울", "남부순환로1430","860-1"));
            em.persist(member);


            Delivery delivery = new Delivery();
            delivery.setStatus(DeliveryStatus.READY);
            em.persist(delivery);

            Delivery delivery2 = new Delivery();
            delivery2.setStatus(DeliveryStatus.COMP);
            em.persist(delivery2);


            Order order = new Order();
            order.setMember(member);
            order.setDelivery(delivery);
            order.setOrderDate(LocalDateTime.now());
            em.persist(order);

            Order order2 = new Order();
            order2.setMember(member);
            order2.setDelivery(delivery2);
            order2.setOrderDate(LocalDateTime.now());
            em.persist(order2);

            Book book1 = new Book();
            book1.setName("BookA");
            book1.setPrice(10000);
            book1.setStockQuantity(100);
            book1.setAuthor("BookAAuthor");
            book1.setIsbn("BookA10001");
            em.persist(book1);

            Album album1 = new Album();
            album1.setName("AlbumA");
            album1.setPrice(150000);
            album1.setStockQuantity(50);
            album1.setArtist("AlbumAArtist");
            album1.setEtc("AlbumAEtc");
            em.persist(album1);

            Movie movie1 = new Movie();
            movie1.setName("MovieA");
            movie1.setPrice(200000);
            movie1.setPrice(25);
            movie1.setActor("MovieAActor");
            movie1.setDirector("MovieADirector");
            em.persist(movie1);

        }

        public void dbInit2() {
            Member member2 = new Member();
            member2.setName("testB");
            member2.setAddress(new Address("아산", "순천향로1913","331-9"));
            em.persist(member2);

            Delivery delivery = new Delivery();
            delivery.setStatus(DeliveryStatus.READY);
            em.persist(delivery);

            Order order3 = new Order();
            order3.setMember(member2);
            order3.setDelivery(delivery);
            order3.setOrderDate(LocalDateTime.now());
            em.persist(order3);
        }
    }
}
