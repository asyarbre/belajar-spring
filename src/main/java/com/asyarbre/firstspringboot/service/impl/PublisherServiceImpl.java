package com.asyarbre.firstspringboot.service.impl;

import com.asyarbre.firstspringboot.domain.Publisher;
import com.asyarbre.firstspringboot.dto.PublisherCreateRequestDto;
import com.asyarbre.firstspringboot.dto.PublisherListResponseDto;
import com.asyarbre.firstspringboot.dto.PublisherUpdateRequestDto;
import com.asyarbre.firstspringboot.dto.ResultPageResponseDto;
import com.asyarbre.firstspringboot.exception.BadRequestException;
import com.asyarbre.firstspringboot.repository.PublisherRepository;
import com.asyarbre.firstspringboot.service.PublisherService;
import com.asyarbre.firstspringboot.utils.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Publisher findPublisher(String publisherId) {
        return publisherRepository.findBySecureId(publisherId).orElseThrow(() -> new BadRequestException("Publisher not found"));
    }

    @Override
    public ResultPageResponseDto<PublisherListResponseDto> findPublisherList(Integer pages, Integer limit, String sortBy, String direction, String publisherName) {
        publisherName = publisherName == null ? "%" : "%" + publisherName + "%";
        Sort sort = Sort.by(new Sort.Order(PaginationUtil.getSortBy(direction), sortBy));
        Pageable pageable = PageRequest.of(pages, limit, sort);
        Page<Publisher> pageResult = publisherRepository.findByNameLikeIgnoreCase(publisherName, pageable);
        List<PublisherListResponseDto> publisherListResponses = pageResult.stream().map((publisher) -> {
            PublisherListResponseDto responseDto = new PublisherListResponseDto();
            responseDto.setPublisherId(publisher.getSecureId());
            responseDto.setPublisherName(publisher.getName());
            responseDto.setCompanyName(publisher.getCompanyName());
            return responseDto;
        }).collect(Collectors.toList());

        return PaginationUtil.createResultPageDto(
                publisherListResponses,
                pageResult.getTotalElements(),
                pageResult.getTotalPages()
        );
    }
}
