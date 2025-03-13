package com.shivpro.cardservice.controller;

import com.shivpro.cardservice.repo.Card;
import com.shivpro.cardservice.repo.CardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/card")
public class CardController {

    @Autowired
    private CardRepo cardRepo;

    @PostMapping
    public ResponseEntity<ApiResponse<Card>> save(@RequestBody Card card){
        Card saved = cardRepo.save(card);
        ApiResponse<Card> response = new ApiResponse<>(true,saved,"saved");
        return ResponseEntity.created(URI.create("/card/" + saved.getCardNumber()))
                .body(response);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<Card>>> cards(){
        List<Card> cardList = cardRepo.findAll();
        if (!cardList.isEmpty()) {
            ApiResponse<List<Card>> response = new ApiResponse<>(true,cardList,"data found");
            return ResponseEntity.ok(response);
        }
        return new ResponseEntity<>(new ApiResponse<>(false,null,"data not found"),HttpStatus.OK);
    }

}
