package com.asyarbre.firstspringboot.service.impl;

import com.asyarbre.firstspringboot.domain.Publisher;
import com.asyarbre.firstspringboot.dto.PublisherCreateRequestDto;
import com.asyarbre.firstspringboot.dto.PublisherUpdateRequestDto;
import com.asyarbre.firstspringboot.exception.BadRequestException;
import com.asyarbre.firstspringboot.repository.PublisherRepository;
import com.asyarbre.firstspringboot.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    @Override
    public void createPublisher(PublisherCreateRequestDto publisherCreateRequestDto) {
        Publisher publisher = new Publisher();
        publisher.setName(publisherCreateRequestDto.getName());
        publisher.setCompanyName(publisherCreateRequestDto.getCompanyName());
        publisher.setAddress(publisherCreateRequestDto.getAddress());

        publisherRepository.save(publisher);
    }

    @Override
    public void updatePublisher(String publisherId, PublisherUpdateRequestDto publisherUpdateRequestDto) {
        Publisher publisher = publisherRepository.findBySecureId(publisherId).orElseThrow(() -> new BadRequestException("Publisher not found"));
        if (publisherUpdateRequestDto.getName() != null) {
            publisher.setName(publisherUpdateRequestDto.getName());
        }
        if (publisherUpdateRequestDto.getCompanyName() != null) {
            publisher.setCompanyName(publisherUpdateRequestDto.getCompanyName());
        }
        if (publisherUpdateRequestDto.getAddress() != null) {
            publisher.setAddress(publisherUpdateRequestDto.getAddress());
        }

        publisherRepository.save(publisher);
    }
}
