package com.domain.berita.repository;

import com.domain.berita.model.Berita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeritaRepository extends JpaRepository<Berita, Long> {
}
