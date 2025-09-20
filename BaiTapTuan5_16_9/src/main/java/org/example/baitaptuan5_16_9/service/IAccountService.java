package org.example.baitaptuan5_16_9.service;

import org.example.baitaptuan5_16_9.entity.Account;
import org.example.baitaptuan5_16_9.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    Account save(Account account);
    Optional<Account> findById(int id);
    void deleteById(int id);
    Page<Account> findAll(Pageable pageable);
    Page<Account> search(String fullName, String userName, Pageable pageable);
}
