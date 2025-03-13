package com.shivpro.cardservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepo extends JpaRepository<Card, String> {
}
