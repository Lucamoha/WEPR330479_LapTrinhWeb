package org.example.baitaptuan5_16_9.repository;

import org.example.baitaptuan5_16_9.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    Page<Account> findByFullNameContainingIgnoreCaseOrUsernameContainingIgnoreCase(String fullName, String userName, Pageable pageable);
}
