package com.domain.berita.service;

import com.domain.berita.model.Berita;
import com.domain.berita.repository.BeritaRepository;
import com.domain.berita.response.PaginateResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map; // Tambahkan impor Map

@Service
public class BeritaService {
    private final BeritaRepository repository;

    public BeritaService(BeritaRepository repository) {
        this.repository = repository;
    }

    public List<Berita> findAll() {
        return repository.findAll();
    }

    public Berita findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Berita save(Berita berita) {
        return repository.save(berita);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public PaginateResponse<?> findPaginate(int page, int perPage) {
        // Menggunakan Spring Data Pageable untuk pagination
        Pageable pageable = PageRequest.of(page - 1, perPage);
        Page<Berita> beritaPage = repository.findAll(pageable);

        // Membuat response pagination
        PaginateResponse.Pagination pagination = new PaginateResponse.Pagination(
                page,
                perPage,
                beritaPage.hasNext() ? page + 1 : 0,
                beritaPage.getTotalPages(),
                (int) beritaPage.getTotalElements(),
                beritaPage.hasNext() ? "localhost:8000/api/berita?page=" + (page + 1) : null,
                page > 1 ? "localhost:8000/api/berita?page=" + (page - 1) : null);

        return new PaginateResponse<>(
                200,
                "Successfully Get Data",
                pagination,
                beritaPage.getContent(),
                Map.of( // Menggunakan Map.of untuk membuat map permission
                        "create", true,
                        "read", true,
                        "update", true,
                        "delete", true,
                        "export", true));
    }
}
