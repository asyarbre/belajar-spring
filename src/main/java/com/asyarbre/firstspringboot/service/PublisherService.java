package com.asyarbre.firstspringboot.service;

import com.asyarbre.firstspringboot.dto.PublisherCreateRequestDto;
import com.asyarbre.firstspringboot.dto.PublisherUpdateRequestDto;

public interface PublisherService {
    void createPublisher(PublisherCreateRequestDto publisherCreateRequestDto);

    void updatePublisher(String id, PublisherUpdateRequestDto publisherUpdateRequestDto);
}
