package com.example.wallet.Service;

import com.example.wallet.Entity.Wallet;
import com.example.wallet.Repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;


@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;
    public  Wallet getWallet(long id){
        Optional<Wallet> wallet = walletRepository.findById(id);
        if (wallet.isPresent()) {
            return wallet.get();
        }
        return null;
    }
    public Wallet create(Wallet wallet) {
        return walletRepository.save(wallet);
    }
    public Wallet update(Long id , Double balance){
        Wallet wallet = getWallet(id);
        if (Objects.isNull(wallet))return null;
        wallet.setBalance(balance);
        return walletRepository.save(wallet);
    }
}
