package com.asyarbre.firstspringboot.utils;

import com.asyarbre.firstspringboot.dto.ResultPageResponseDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PaginationUtil {
    public static <T> ResultPageResponseDto<T> createResultPageDto(List<T> list, Long totalElements, Integer pages) {
        ResultPageResponseDto<T> resultPageResponseDto = new ResultPageResponseDto<>();
        resultPageResponseDto.setData(list);
        resultPageResponseDto.setPage(pages);
        resultPageResponseDto.setElements(totalElements);
        return resultPageResponseDto;
    }

    public static Sort.Direction getSortBy(String sortBy) {
        if (sortBy.equals("asc")) {
            return Sort.Direction.ASC;
        } else {
            return Sort.Direction.DESC;
        }
    }
}
