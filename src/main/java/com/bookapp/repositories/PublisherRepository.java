package com.bookapp.repositories;

import java.util.List;

import com.bookapp.models.Publisher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    public List<Publisher> findByNameContaining(String keyword);
}
