package com.marcosoft.jiraslamanagment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject {
    private OperationStatus status;
    private String message;
}

