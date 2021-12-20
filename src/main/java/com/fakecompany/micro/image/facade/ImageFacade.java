package com.fakecompany.micro.image.facade;



import com.fakecompany.micro.image.dto.ImageDto;
import com.fakecompany.micro.image.mapper.ImageMapper;
import com.fakecompany.micro.image.model.Image;
import com.fakecompany.micro.image.service.ImageService;
import com.fakecompany.micro.image.util.ObjectTypeConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Service
@Transactional
public class ImageFacade {

    private ImageService imageService;
    private ImageMapper imageMapper;

    public ImageFacade(ImageService imageService, ImageMapper imageMapper) {
        this.imageService = imageService;
        this.imageMapper = imageMapper;
    }

    public ImageDto createImage(ImageDto imageDto, MultipartFile imagePart){
        imageDto.setImage(ObjectTypeConverter.image2Base64(imagePart));
        return imageMapper.toDto(imageService.createImage(imageMapper.toEntity(imageDto)));
    }

    public ImageDto editImage(ImageDto imageDto, MultipartFile imagePart){
        Image imageEdit = imageService.findById(imageDto.getId());
        imageEdit.setImage(ObjectTypeConverter.image2Base64(imagePart));
        imageEdit.setPersonId(imageDto.getPersonId());

        ImageDto imageDtoEdit = imageMapper.toDto(imageService.editImage(imageEdit));

        return imageDtoEdit;
    }

    public void deleteImage(String imageId){
        imageService.deleteImage(imageId);

    }

    public List<ImageDto> findAll(){
        return imageMapper.toDto(imageService.findAll());
    }

}
