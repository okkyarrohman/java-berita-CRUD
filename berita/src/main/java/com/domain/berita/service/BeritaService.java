package com.domain.berita.service;

import com.domain.berita.model.Berita;
import com.domain.berita.repository.BeritaRepository;
import com.domain.berita.response.PaginateResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import java.util.stream.Collectors;

@Service
public class BeritaService {
    @Value("${app.url}")
    private String baseUrl;

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

    public PaginateResponse<?> findPaginate(int page, int perPage, String search) {
        Pageable pageable = PageRequest.of(page - 1, perPage);
        Page<Berita> beritaPage = repository.findAll(pageable);

        if (search == null || search.isEmpty()) {

            beritaPage = repository.findAll(pageable);
        } else {

            beritaPage = repository.searchByKeyword(search, pageable);
        }

        var modifiedData = beritaPage.getContent().stream()
                .peek(berita -> {
                    if (berita.getGambar() != null) {
                        berita.setGambar(baseUrl + "/uploads/" + berita.getGambar());
                    }
                })
                .collect(Collectors.toList());

        String nextUrl = beritaPage.hasNext() ? baseUrl + "/api/berita?page=" + (page + 1) : null;
        String prevUrl = page > 1 ? baseUrl + "/api/berita?page=" + (page - 1) : null;

        PaginateResponse.Pagination pagination = new PaginateResponse.Pagination(
                page,
                perPage,
                beritaPage.hasNext() ? page + 1 : 0,
                beritaPage.getTotalPages(),
                (int) beritaPage.getTotalElements(),
                nextUrl,
                prevUrl);
        return new PaginateResponse<>(
                200,
                "Successfully Get Data",
                pagination,
                modifiedData);
    }
}
