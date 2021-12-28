package com.fakecompany.micro.image.facade;

import com.fakecompany.common.dto.ImageDto;
import com.fakecompany.micro.image.exception.PersonJustOneImageException;
import com.fakecompany.micro.image.mapper.ImageMapper;
import com.fakecompany.micro.image.service.ImageService;
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

    public ImageDto createImage(Integer personId, MultipartFile imagePart){
        //TODO validar en el micro person si existe la persona.
        ImageDto imageDto = new ImageDto();
        if(hasImage(personId)){
            throw new PersonJustOneImageException("exception.person_just_one_image.image");
        }
        imageDto.setImageUrl((imagePart.getName()));//TODO reparar
        imageDto.setPersonId(personId);
        return imageMapper.toDto(imageService.createImage(imageMapper.toEntity(imageDto)));
    }

    public ImageDto createImage(ImageDto imageDto){
        //Solo aplica llamada desde micro person
        if(hasImage(imageDto.getPersonId())){
            throw new PersonJustOneImageException("exception.person_just_one_image.image");
        }

        imageDto.setImageUrl(imageDto.getImageUrl());
        imageDto.setImageName(imageDto.getImageName());
        imageDto.setPersonId(imageDto.getPersonId());
        return imageMapper.toDto(imageService.createImage(imageMapper.toEntity(imageDto)));
    }

    /*public ImageDto editImage(ImageDto imageDto, MultipartFile imagePart){
        Image imageEdit =imageService.findById(imageDto.getId());
        //TODO validar en el micro person si existe la persona.

        if (!imagePart.isEmpty()){
            //TODO comprobar que si tenga imagen en bd (restriccion es una persona una imagen)
            if(hasImage(imageDto.getPersonId())){
                if(imageComeOnBody(imageDto.getId())){
                    imageEdit = mappingImage(imageDto.getId(), imageDto.getPersonId(), imagePart);
                    imageEdit = imageService.editImage(imageEdit);
                }else{
                    throw new ImageNotComeBodyException("exception.not_come_body.image");
                }
            }else{
                imageEdit = imageService.createImage(mappingImage(imageDto.getId(),imageDto.getPersonId(), imagePart));
            }
        }

        return imageMapper.toDto(imageService.editImage(imageEdit));
    }*/

    public ImageDto editImage(ImageDto imageDto){

        return imageMapper.toDto(imageService.editImage(imageMapper.toEntity(imageDto)));
    }

    private boolean hasImage(Integer personId){
        return !imageService.findByPersonId(personId).getImageUrl().isEmpty();
    }


    public void deleteImage(String imageId){
        imageService.deleteImage(imageId);

    }

    public ImageDto findByPersonId(Integer personId){
        return imageMapper.toDto(imageService.findByPersonId(personId));
    }

    public List<ImageDto> findAll(){
        return imageMapper.toDto(imageService.findAll());
    }

}
