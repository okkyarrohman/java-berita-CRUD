package com.domain.berita.repository;

import com.domain.berita.model.Berita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeritaRepository extends JpaRepository<Berita, Long> {
    Page<Berita> findAll(Pageable pageable);
}
