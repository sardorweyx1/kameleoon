package com.kameleoon.task.controller;

import com.kameleoon.task.entity.Quote;
import com.kameleoon.task.payload.*;
import com.kameleoon.task.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/app/quote")
public class QuoteController {

    private final QuoteService quoteService;

    //add quote
    @PostMapping("/add")
    public ResponseEntity<?> save(@RequestBody QuoteDto quoteDto) {
        Quote quote = quoteService.save(quoteDto);
        return ResponseEntity.ok(quote);
    }

    //get all quotes
    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        List<ResponceGetAllQuote> list = quoteService.getAll();
        return ResponseEntity.ok(list);
    }

    //get random quote
    @GetMapping("/random")
    public ResponseEntity<?> getRandom() {
        ResponseRandomQuote randomQuote = quoteService.getRandom();
        return ResponseEntity.ok(randomQuote);
    }

    //get top 10 quotes
    @GetMapping("/top10")
    public ResponseEntity<?> getTop10() {
        List<Responce10Quotes> responce = quoteService.getTop10();
        return ResponseEntity.ok(responce);
    }

    //get last 10 quotes
    @GetMapping("/flop10")
    public ResponseEntity<?> getFlop10() {
        List<Responce10Quotes> responceList = quoteService.getFlop10();
        return ResponseEntity.ok(responceList);
    }

    //get last quote
    @GetMapping("/last")
    public ResponseEntity<?> getLastQuote(){
       Quote quote = quoteService.getLastQuote();
       return ResponseEntity.ok(quote);
    }

    //vote quote
    @PutMapping("/vote/{id}/{vote}")
    public ResponseEntity<?> voteQuote(@PathVariable Long id,
                                       @PathVariable String vote) {
        VoteResponce responce = quoteService.voteQuote(id, vote);
        return ResponseEntity.ok(responce);
    }

}
