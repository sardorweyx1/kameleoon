package com.kameleoon.task.service;

import com.kameleoon.task.entity.Quote;
import com.kameleoon.task.entity.User;
import com.kameleoon.task.payload.*;
import com.kameleoon.task.repository.QuoteRepository;
import com.kameleoon.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;


    public Quote save(QuoteDto quoteDto) {
        boolean exists = quoteRepository.existsByText(quoteDto.getText());
        try {
            if (exists) throw new RuntimeException("Quote already exists");
            Optional<User> optionalUser = userRepository.findById(quoteDto.getUserId());
            if (optionalUser.isEmpty()) throw new RuntimeException("User is not found");
            Quote quote = Quote
                    .builder()
                    .text(quoteDto.getText())
                    .user(optionalUser.get())
                    .score(0)
                    .build();
            quoteRepository.save(quote);
            return quote;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ResponceGetAllQuote> getAll() {
        List<Quote> quoteList = quoteRepository.findAll();
        List<ResponceGetAllQuote> responseList = new ArrayList<>();
        for (Quote quote : quoteList) {
            Optional<User> user = userRepository.findById(quote.getUser().getId());
            if (user.isEmpty()) throw new RuntimeException("User not found");
            ResponceGetAllQuote response = ResponceGetAllQuote
                    .builder()
                    .id(quote.getId())
                    .text(quote.getText())
                    .score(quote.getScore())
                    .userName(user.get().getName())
                    .createdAt(quote.getCreatedAt())
                    .build();
            responseList.add(response);
        }
        return responseList;
    }


    public ResponseRandomQuote getRandom() {
        try {
            List<Long> last10NewQuotesId = quoteRepository.getLast10NewQuotesId(10);
            Random random = new Random();
            Long id = last10NewQuotesId.get(random.nextInt(last10NewQuotesId.size()));
            Optional<Quote> optionalQuote = quoteRepository.findById(id);
            if (optionalQuote.isEmpty()) throw new RuntimeException("Quote not found");
            Quote quote = optionalQuote.get();
            Optional<User> optionalUser = userRepository.findById(quote.getUser().getId());
            if(optionalUser.isEmpty()) throw new RuntimeException("User Not Found");
            User user = optionalUser.get();
            return ResponseRandomQuote
                    .builder()
                    .text(quote.getText())
                    .score(quote.getScore())
                    .userName(user.getName())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public VoteResponce voteQuote(Long id, String vote) {
        try {
            Optional<Quote> optionalQuote = quoteRepository.findById(id);
            if (optionalQuote.isEmpty()) throw new RuntimeException("Quote not found");
            Quote quote = optionalQuote.get();
            Integer score = quote.getScore();
            if (Objects.equals(vote, "-")) {
                if (score == 0) throw new RuntimeException("This quote has not score. You cann't vote");
                score -= 1;
            } else score += 1;
            quote.setScore(score);
            quoteRepository.save(quote);
            return VoteResponce
                    .builder()
                    .id(id)
                    .text(quote.getText())
                    .authorName(quote.getUser().getName())
                    .score(score)
                    .build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Responce10Quotes> getTop10() {
        try {
            List<Quote> top10 = quoteRepository.getTop10(10);
            return getResponce10Quotes(top10);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Responce10Quotes> getFlop10() {
        try {
            List<Quote> quoteList = quoteRepository.getFlop10(10);
            return getResponce10Quotes(quoteList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private List<Responce10Quotes> getResponce10Quotes(List<Quote> top10) {
        List<Responce10Quotes> responceList=new ArrayList<>();
        for (Quote quote : top10) {
            Responce10Quotes responce=Responce10Quotes
                    .builder()
                    .id(quote.getId())
                    .postedBy(quote.getUser().getName())
                    .score(quote.getScore())
                    .text(quote.getText())
                    .build();
            responceList.add(responce);
        }
        return responceList;
    }

    public Quote getLastQuote() {
        try {
            Optional<Quote> optionalQuote = quoteRepository.lastQuote(1);
            if(optionalQuote.isPresent()) return optionalQuote.get();
            throw new RuntimeException("Quote not found");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
