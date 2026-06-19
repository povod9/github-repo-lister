package com.povod9.task;

import lombok.Builder;

@Builder
public record ErrorMessage(
        int status,
        String message
) {
}
