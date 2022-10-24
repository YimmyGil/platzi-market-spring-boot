package com.platzi.market.web.controller;

import com.platzi.market.domain.Purchase;
import com.platzi.market.domain.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(compraService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{purchase}")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("purchase") int purchase){
        return compraService.getByClient(purchase)
                .map(compra -> new ResponseEntity<>(compra, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase){
            return new ResponseEntity<>(compraService.save(purchase),HttpStatus.CREATED);
    }
}
