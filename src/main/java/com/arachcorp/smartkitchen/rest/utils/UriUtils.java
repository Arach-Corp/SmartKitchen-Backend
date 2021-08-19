package com.arachcorp.smartkitchen.rest.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public abstract class UriUtils {

    public static URI createFromCurrentRequest(String path, Object... objects){
        final URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(path)
                .buildAndExpand(objects)
                .toUri();

        return uri;
    }
}
