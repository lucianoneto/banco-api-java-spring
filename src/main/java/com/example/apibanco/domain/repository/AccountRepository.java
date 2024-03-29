package com.example.apibanco.domain.repository;

import com.example.apibanco.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findById(Long id);

    List<Account> findAccountByClientManagerId(Long id);

}
