package com.saparov.app.repository;

import com.saparov.app.model.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
	Optional<Limit> findByClientAccountId(Long clientId);
}
