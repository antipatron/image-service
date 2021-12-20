package com.fakecompany.micro.image.dto;


import javax.validation.constraints.NotNull;


public class ImageDto {

    private String id;
    private String image;
    @NotNull
    private Integer personId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }


}
