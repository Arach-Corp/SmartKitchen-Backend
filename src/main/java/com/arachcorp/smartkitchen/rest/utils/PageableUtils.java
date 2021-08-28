package com.arachcorp.smartkitchen.rest.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public abstract class PageableUtils {

    public static Pageable createPageable(int page, int perPage) {
        if (perPage == 0) {
            perPage = Integer.MAX_VALUE;
        }
        return PageRequest.of(page, perPage);
    }

}
