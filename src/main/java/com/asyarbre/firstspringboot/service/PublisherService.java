package com.asyarbre.firstspringboot.service;

import com.asyarbre.firstspringboot.domain.Publisher;
import com.asyarbre.firstspringboot.dto.*;

public interface PublisherService {
    void createPublisher(PublisherCreateRequestDto publisherCreateRequestDto);

    void updatePublisher(String id, PublisherUpdateRequestDto publisherUpdateRequestDto);

    Publisher findPublisher(String publisherId);

    ResultPageResponseDto<PublisherListResponseDto> findPublisherList(
            Integer pages,
            Integer limit,
            String sortBy,
            String direction,
            String publisherName
    );

    PublisherResponseDto constructPublisherResponseDto(Publisher publisher);
}
