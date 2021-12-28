package com.fakecompany.micro.image.controller;

import com.fakecompany.common.dto.ImageDto;
import com.fakecompany.common.util.StandardResponse;
import com.fakecompany.micro.image.facade.ImageFacade;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/image")
public class ImageController {

    private ImageFacade imageFacade;

    public ImageController(ImageFacade imageFacade) {
        this.imageFacade = imageFacade;
    }

    @PostMapping("/save-with-multipart")
    @ApiOperation(value = "Save image with multipart", response = ImageDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La petición fue procesada con éxito"),
            @ApiResponse(code = 400, message = "La petición es inválida"),
            @ApiResponse(code = 500, message = "Error del servidor al procesar la respuesta"),
    })
    public ResponseEntity<StandardResponse<ImageDto>> createImage(@RequestPart("image") MultipartFile image,
                                                                  @NotNull @RequestParam("personId") Integer personId){
        ImageDto imageDto1 = imageFacade.createImage(personId, image);
        Logger.getGlobal().log(Level.INFO, imageDto1.toString());

        return ResponseEntity.ok(new StandardResponse<>(
                StandardResponse.StatusStandardResponse.OK,
                "image.create.ok",
                imageDto1));
    }

    @PostMapping
    @ApiOperation(value = "Save image", response = ImageDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La petición fue procesada con éxito"),
            @ApiResponse(code = 400, message = "La petición es inválida"),
            @ApiResponse(code = 500, message = "Error del servidor al procesar la respuesta"),
    })
    public ResponseEntity<StandardResponse<ImageDto>> createImageNoMultipart(@RequestBody ImageDto imageDto){
        ImageDto imageDto1 = imageFacade.createImage(imageDto);
        Logger.getGlobal().log(Level.INFO, imageDto1.toString());

        return ResponseEntity.ok(new StandardResponse<>(
                StandardResponse.StatusStandardResponse.OK,
                "image.create.ok",
                imageDto1));
    }

    @PutMapping("/edit-with-multipart")
    @ApiOperation(value = "Edit image", response = ImageDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La petición fue procesada con éxito"),
            @ApiResponse(code = 400, message = "La petición es inválida"),
            @ApiResponse(code = 500, message = "Error del servidor al procesar la respuesta"),
    })
    public ResponseEntity<StandardResponse<ImageDto>> editImage(
            @Valid @RequestPart("imageDto") ImageDto imageDto, @RequestPart MultipartFile image){
        ImageDto imageDto1 = null;//imageFacade.editImage(imageDto, image);
        return ResponseEntity.ok(new StandardResponse<>(
                StandardResponse.StatusStandardResponse.OK,
                "image.edit.ok",
                imageDto1));
    }

    @PutMapping
    @ApiOperation(value = "Edit image", response = ImageDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La petición fue procesada con éxito"),
            @ApiResponse(code = 400, message = "La petición es inválida"),
            @ApiResponse(code = 500, message = "Error del servidor al procesar la respuesta"),
    })
    public ResponseEntity<StandardResponse<ImageDto>> editImageNoMultipart(@RequestBody ImageDto imageDto){
        ImageDto imageDto1 = imageFacade.editImage(imageDto);
        return ResponseEntity.ok(new StandardResponse<>(
                StandardResponse.StatusStandardResponse.OK,
                "image.edit.ok",
                imageDto1));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Delete image by id", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La petición fue procesada con éxito"),
            @ApiResponse(code = 400, message = "La petición es inválida"),
            @ApiResponse(code = 500, message = "Error del servidor al procesar la respuesta"),
    })
    public ResponseEntity<StandardResponse<String>> deleteImage(
            @RequestParam(name = "imageId")  String imageId){

        imageFacade.deleteImage(imageId);
        return ResponseEntity.accepted().body(new StandardResponse<>(StandardResponse.StatusStandardResponse.OK,"delete.image.ok"));

    }

    @GetMapping("/get-all")
    @ApiOperation(value = "Get all", response = ImageDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La petición fue procesada con éxito"),
            @ApiResponse(code = 400, message = "La petición es inválida"),
            @ApiResponse(code = 500, message = "Error del servidor al procesar la respuesta"),
    })
    public ResponseEntity<StandardResponse<List<ImageDto>>> findAll(){

        List<ImageDto> imageDtoList = imageFacade.findAll();
        return ResponseEntity.ok(new StandardResponse<>(
                StandardResponse.StatusStandardResponse.OK,
                imageDtoList));
    }

    @GetMapping("/get-by-id")
    @ApiOperation(value = "Get by id", response = ImageDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La petición fue procesada con éxito"),
            @ApiResponse(code = 400, message = "La petición es inválida"),
            @ApiResponse(code = 500, message = "Error del servidor al procesar la respuesta"),
    })
    public ResponseEntity<StandardResponse<ImageDto>> findByPersonId(@RequestParam("personId") Integer personId){

        ImageDto imageDto = imageFacade.findByPersonId(personId);
        return ResponseEntity.ok(new StandardResponse<>(
                StandardResponse.StatusStandardResponse.OK,
                imageDto));
    }

}
