package com.fakecompany.micro.image.util;

import com.fakecompany.micro.image.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

public class ObjectTypeConverter {

    public static String image2Base64(MultipartFile image){
        byte[] imageBytes = new byte[0];
        try {
            imageBytes = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);

        return imageBase64;
    }


    public static Image mappingImage(String imageId, Integer personId, MultipartFile imagePart){
        Image image = new Image();
        image.setId(imageId);
        image.setImage(image2Base64(imagePart));
        image.setPersonId(personId);
        return image;
    }





}
