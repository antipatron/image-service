package com.fakecompany.micro.image.mapper;


import com.fakecompany.common.dto.ImageDto;
import com.fakecompany.micro.image.model.Image;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper extends EntityMapper<ImageDto, Image>{
}
