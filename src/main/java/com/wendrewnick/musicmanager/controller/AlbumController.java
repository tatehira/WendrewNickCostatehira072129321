package com.wendrewnick.musicmanager.controller;

import com.wendrewnick.musicmanager.dto.AlbumDTO;
import com.wendrewnick.musicmanager.service.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/albums")
@RequiredArgsConstructor
@Tag(name = "Albums", description = "Endpoints for managing Albums")
@SecurityRequirement(name = "bearerAuth")
public class AlbumController {

    private final AlbumService albumService;

    @Operation(summary = "List all albums", description = "Supports pagination and filtering by title")
    @GetMapping
    public ResponseEntity<Page<AlbumDTO>> getAllAlbums(
            @RequestParam(required = false) String title,
            Pageable pageable
    ) {
        return ResponseEntity.ok(albumService.findAll(title, pageable));
    }

    @Operation(summary = "Get album by ID")
    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable UUID id) {
        return ResponseEntity.ok(albumService.findById(id));
    }

    @Operation(summary = "Create a new album", description = "Upload image and data")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AlbumDTO> createAlbum(
            @RequestPart("data") @Valid AlbumDTO albumDTO,
            @RequestPart(value = "image", required = false) MultipartFile image
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(albumService.create(albumDTO, image));
    }

    @Operation(summary = "Delete an album")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable UUID id) {
        albumService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
