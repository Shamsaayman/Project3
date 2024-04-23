package com.example.project3.Controller;

import com.example.project3.Model.Account;
import com.example.project3.Model.User;
import com.example.project3.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity createAccount(@Valid @RequestBody Account account) {
        accountService.createAccount(account);
        return ResponseEntity.status(200).body("Account created");
    }

    @PutMapping("/activate/{accountId}")
    public ResponseEntity activateAccount(@PathVariable Integer accountId) {
         accountService.activateAccount(accountId);
        return ResponseEntity.status(200).body("Account activated");
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity getAccountDetails(@PathVariable Integer accountId) {
        return ResponseEntity.status(200).body(accountService.viewAccountDetails(accountId));
    }

    @GetMapping("/getusers")
    public ResponseEntity getUserAccounts(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(accountService.getUserAccounts(user.getId()));
    }

    @PostMapping("/deposit/{accountId}/{amount}")
    public ResponseEntity depositMoney(@PathVariable Integer accountId, @PathVariable  Integer amount) {
        accountService.depositMoney(accountId, amount);
        return ResponseEntity.status(200).body("Money deposited successfully");
    }

    @PostMapping("/withdraw/{accountId}/{amount}")
    public ResponseEntity withdrawMoney(@PathVariable Integer accountId, @PathVariable  Integer amount) {
        accountService.withdrawMoney(accountId, amount);
        return ResponseEntity.status(200).body("Money withdrawn successfully");
    }

    @PostMapping("/transfer/{senderAccountId}/{receiverAccountId}/{amount}")
    public ResponseEntity transferFunds(@PathVariable Integer senderAccountId, @PathVariable Integer receiverAccountId,@PathVariable  Integer amount) {
        accountService.transferFunds(senderAccountId, receiverAccountId, amount);
        return ResponseEntity.status(200).body("Funds transferred successfully");
    }

    @PutMapping("/block/{accountId}")
    public ResponseEntity blockAccount(@PathVariable Integer accountId) {
        accountService.blockAccount(accountId);
        return ResponseEntity.status(200).body("Account blocked successfully");
    }
}
