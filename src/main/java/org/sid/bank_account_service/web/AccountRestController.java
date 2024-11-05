package org.sid.bank_account_service.web;

import org.sid.bank_account_service.dto.BankAccountRequestDTO;
import org.sid.bank_account_service.dto.BankAccountResponseDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.mappers.AccountMapper;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.sid.bank_account_service.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")

public class AccountRestController {
    private BankAccountRepository bankAcoountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;

    public AccountRestController(BankAccountRepository bankAcoountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.bankAcoountRepository = bankAcoountRepository;
        this.accountService = accountService;
        this.accountMapper = accountMapper;
    }
    @GetMapping("/bankAccounts")
    public List<BankAccount> bankAccounts(){
        return bankAcoountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccount(@PathVariable String id){
        return bankAcoountRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Account %s not fount", id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO){

       return accountService.addAccount(requestDTO);

    }
    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id,@RequestBody BankAccount bankAccount){
        BankAccount account=bankAcoountRepository.findById(id).orElseThrow();
       if (bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
       if (bankAccount.getCreatedAt()!=null) account.setCreatedAt(new Date());
       if (bankAccount.getType()!=null) account.setType(bankAccount.getType());
       if (bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
       return bankAcoountRepository.save(account);

    }
    @DeleteMapping("/bankAccounts/{id}")
    public void deleteAccount(@PathVariable String id){
         bankAcoountRepository.deleteById(id);

    }
}
