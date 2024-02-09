package org.fasttrackit.homework18.model;

import lombok.Builder;
import lombok.With;

import java.util.UUID;

@With
@Builder(toBuilder = true)
public record Transaction(
            String id,
            String product,
            String type,
            Double amount

    ) {
}

