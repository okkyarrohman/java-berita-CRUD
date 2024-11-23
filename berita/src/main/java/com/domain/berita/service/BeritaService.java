package com.domain.berita.service;

import com.domain.berita.model.Berita;
import com.domain.berita.repository.BeritaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
