package com.domain.berita.repository;

import com.domain.berita.model.Berita;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BeritaRepository extends JpaRepository<Berita, Long> {
    Page<Berita> findAll(Pageable pageable);

    @Query("SELECT b FROM Berita b WHERE LOWER(b.judul) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(b.konten) LIKE LOWER(CONCAT('%', :search, '%')) OR LOWER(b.penulis) LIKE LOWER(CONCAT('%', :search, '%'))")

    Page<Berita> searchByKeyword(String search, Pageable pageable);

}
