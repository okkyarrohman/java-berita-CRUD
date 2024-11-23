package com.domain.berita.controller;

import com.domain.berita.model.Berita;
import com.domain.berita.service.BeritaService;
import com.domain.berita.service.FileStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/berita")
public class BeritaController {
    private final BeritaService beritaService;
    private final FileStorageService fileStorageService;

    public BeritaController(BeritaService beritaService, FileStorageService fileStorageService) {
        this.beritaService = beritaService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping
    public List<Berita> getAllBerita() {
        return beritaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Berita> getBeritaById(@PathVariable Long id) {
        Berita berita = beritaService.findById(id);
        return berita != null ? ResponseEntity.ok(berita) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Berita> createBerita(
            @Validated @RequestPart("judul") String judul, // Field 'judul' sebagai String
            @Validated @RequestPart("konten") String konten, // Field 'konten' sebagai String
            @Validated @RequestPart("penulis") String penulis, // Field 'penulis' sebagai String
            @RequestPart("file") MultipartFile file) { // Field 'file' sebagai file upload

        // Membuat objek 'Berita' baru
        Berita berita = new Berita();
        berita.setJudul(judul);
        berita.setKonten(konten);
        berita.setPenulis(penulis);

        // Menyimpan file jika ada
        if (file != null && !file.isEmpty()) {
            String fileName = fileStorageService.storeFile(file); // Menyimpan file dan mendapatkan nama file
            berita.setGambar(fileName); // Menyimpan nama file ke field 'gambar'
        }

        // Menyimpan berita ke database
        Berita savedBerita = beritaService.save(berita);

        // Mengembalikan response dengan status 201 Created
        return new ResponseEntity<>(savedBerita, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Berita> updateBerita(
            @PathVariable Long id,
            @Validated @RequestPart("berita") Berita updatedBerita,
            @RequestPart(value = "file", required = false) MultipartFile file) {

        Berita existingBerita = beritaService.findById(id);
        if (existingBerita != null) {
            existingBerita.setJudul(updatedBerita.getJudul());
            existingBerita.setKonten(updatedBerita.getKonten());
            existingBerita.setPenulis(updatedBerita.getPenulis());

            if (file != null && !file.isEmpty()) {
                String fileName = fileStorageService.storeFile(file);
                existingBerita.setGambar(fileName);
            }

            return ResponseEntity.ok(beritaService.save(existingBerita));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBerita(@PathVariable Long id) {
        beritaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
