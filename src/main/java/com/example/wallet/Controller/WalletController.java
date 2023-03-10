package com.example.wallet.Controller;

import com.example.wallet.Entity.Wallet;
import com.example.wallet.Service.WalletService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@Controller
@RequestMapping("/wallet")
@AllArgsConstructor
public class WalletController {
    private WalletService walletService;
    public static final String WALLET_SERVICE="WalletService";
    @PostMapping("/save")
    public ResponseEntity<Wallet> create(@Valid @RequestBody Wallet wallet){
       Wallet walletSaved=  walletService.create(wallet);
        return  new ResponseEntity(walletSaved,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name=WALLET_SERVICE,fallbackMethod="getWallet")
    public ResponseEntity<Double> getBallance(@PathVariable long id){
        Wallet wallet = walletService.getWallet(id);
        if (Objects.isNull(wallet)) {
            return new ResponseEntity<>(0.0D,HttpStatus.OK);
        }
        return new ResponseEntity<>(wallet.getBalance(),HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Boolean> updateWallet(@RequestParam Long id,@RequestParam Double balance){
        Wallet wallet = walletService.update(id,balance);
        if (wallet == null) return new ResponseEntity<>(false,HttpStatus.OK);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }


}
