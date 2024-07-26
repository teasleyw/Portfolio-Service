package com.FrostMilano.Portfolio.repositories;


import com.FrostMilano.Portfolio.entites.Poem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PoemRepository extends JpaRepository<Poem, Long> {

}
