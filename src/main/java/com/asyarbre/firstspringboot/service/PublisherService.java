package com.asyarbre.firstspringboot.service;

import com.asyarbre.firstspringboot.dto.PublisherCreateRequestDto;
import com.asyarbre.firstspringboot.dto.PublisherListResponseDto;
import com.asyarbre.firstspringboot.dto.PublisherUpdateRequestDto;
import com.asyarbre.firstspringboot.dto.ResultPageResponseDto;

public interface PublisherService {
    void createPublisher(PublisherCreateRequestDto publisherCreateRequestDto);

    void updatePublisher(String id, PublisherUpdateRequestDto publisherUpdateRequestDto);

    ResultPageResponseDto<PublisherListResponseDto> findPublisherList(
            Integer pages,
            Integer limit,
            String sortBy,
            String direction,
            String publisherName
    );
}
