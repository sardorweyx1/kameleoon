package com.kameleoon.task.repository;

import com.kameleoon.task.entity.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    boolean existsByText(String text);

    @Query(value = "SELECT id FROM quote ORDER BY id DESC LIMIT ?1",nativeQuery = true)
    List<Long> getLast10NewQuotesId(Integer id);

    @Query(value = "SELECT * FROM quote ORDER BY score DESC LIMIT ?1",nativeQuery = true)
    List<Quote> getTop10(Integer top);

    @Query(value = "SELECT * FROM quote ORDER BY score ASC LIMIT ?1",nativeQuery = true)
    List<Quote> getFlop10(Integer top);


    @Query(value = "select * from Quote q order by q.created_at desc limit ?1",nativeQuery = true)
    Optional<Quote> lastQuote(Integer l);
}
