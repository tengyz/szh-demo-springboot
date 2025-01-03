package com.sunny.boot.base.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponseDTO<T> implements Serializable {
    private static final long serialVersionUID = -275582248840137389L;
    private Long count;
    private Integer code;
    private String desc;
    private List<T> data;

}
