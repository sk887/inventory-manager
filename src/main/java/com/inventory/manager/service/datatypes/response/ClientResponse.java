package com.inventory.manager.service.datatypes.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ClientResponse<T> {
    private Boolean isSuccess;
    private T  data;
}
