package com.javatechie.saga.payment.repository;

import com.javatechie.saga.payment.entity.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance,Integer> {
}
