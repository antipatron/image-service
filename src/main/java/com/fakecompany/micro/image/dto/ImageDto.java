package com.fakecompany.micro.image.dto;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ImageDto {

    private String id;
    private String imageUrl;
    private String imageName;
    @NotNull
    private Integer personId;
}
