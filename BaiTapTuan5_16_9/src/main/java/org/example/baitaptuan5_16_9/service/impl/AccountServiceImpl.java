package org.example.baitaptuan5_16_9.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.baitaptuan5_16_9.entity.Account;
import org.example.baitaptuan5_16_9.repository.AccountRepository;
import org.example.baitaptuan5_16_9.service.IAccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Optional<Account> findById(int id) {
        return accountRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public Page<Account> search(String fullName, String userName, Pageable pageable) {
        return accountRepository.findByFullNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(fullName, userName, pageable);
    }
}
