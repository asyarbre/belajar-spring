package com.asyarbre.firstspringboot.web;

import com.asyarbre.firstspringboot.dto.PublisherCreateRequestDto;
import com.asyarbre.firstspringboot.dto.PublisherListResponseDto;
import com.asyarbre.firstspringboot.dto.PublisherUpdateRequestDto;
import com.asyarbre.firstspringboot.dto.ResultPageResponseDto;
import com.asyarbre.firstspringboot.service.PublisherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@AllArgsConstructor
@RestController
public class PublisherResource {
    private final PublisherService publisherService;

    @PostMapping("/v1/publisher")
    public ResponseEntity<Void> createNewPublisher(@RequestBody @Valid PublisherCreateRequestDto publisherCreateRequestDto) {
        publisherService.createPublisher(publisherCreateRequestDto);
        return ResponseEntity.created(URI.create("/publisher")).build();
    }

    @PutMapping("/v1/publisher/{id}")
    public ResponseEntity<Void> updatePublisher(@PathVariable String id, @RequestBody @Valid PublisherUpdateRequestDto publisherUpdateRequestDto) {
        publisherService.updatePublisher(id, publisherUpdateRequestDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/publisher")
    public ResponseEntity<ResultPageResponseDto<PublisherListResponseDto>> findPublisherList(
            @RequestParam(name = "pages", defaultValue = "0") Integer pages,
            @RequestParam(name = "limit", defaultValue = "10") Integer limit,
            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(name = "direction", defaultValue = "asc") String direction,
            @RequestParam(name = "publisherName", required = false) String publisherName
    ) {
        return ResponseEntity.ok().body(publisherService.findPublisherList(pages, limit, sortBy, direction, publisherName));
    }
}
