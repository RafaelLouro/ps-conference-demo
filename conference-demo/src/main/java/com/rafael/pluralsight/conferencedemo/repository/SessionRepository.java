package com.rafael.pluralsight.conferencedemo.repository;

import com.rafael.pluralsight.conferencedemo.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
