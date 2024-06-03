package com.FrostMilano.Portfolio.repositories;

import com.FrostMilano.Portfolio.entites.QandA;
import com.FrostMilano.Portfolio.entites.WorkHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QandARepository extends JpaRepository<QandA, Long> {
    List<QandA> findByUserId(Long userId);
}
