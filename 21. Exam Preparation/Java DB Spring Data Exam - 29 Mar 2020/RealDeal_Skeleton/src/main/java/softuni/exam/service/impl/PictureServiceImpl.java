package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dtos.jsons.PictureImportDto;
import softuni.exam.models.entities.Picture;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;


//https://www.youtube.com/watch?v=Upw7ph_Tyis&feature=emb_title&ab_channel=SoftwareUniversity%28SoftUni%29

//1:46

@Service
public class PictureServiceImpl implements PictureService {
    private static final String PICTURES_PATH =
            "src/main/resources/files/json/pictures.json";
    private final PictureRepository pictureRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final CarRepository carRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, ModelMapper modelMapper,
                              Gson gson, ValidationUtil validationUtil, CarRepository carRepository) {
        this.pictureRepository = pictureRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.carRepository = carRepository;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return String.join("", Files.readAllLines(Path.of(PICTURES_PATH)));
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();

        PictureImportDto[] pictureImportDtos = this.gson
                .fromJson(this.readPicturesFromFile(), PictureImportDto[].class);
        for (PictureImportDto pictureImportDto : pictureImportDtos) {
            // If byName returns false, the entity is not unique and we go
            // to the else
            Optional<Picture> byName = this.pictureRepository
                    .findByName(pictureImportDto.getName());
            if (this.validationUtil.isValid(pictureImportDto) &&
                    byName.isEmpty()) {
                Picture picture = this.modelMapper.map(pictureImportDto, Picture.class);
                picture.setCar(this.carRepository.getOne(pictureImportDto.getCar()));

                this.pictureRepository.saveAndFlush(picture);
                sb.append(String
                        .format("Successfully import picture - %s\n", pictureImportDto.getName()));
            } else {
                sb.append("Invalid picture\n");
            }
        }
        return sb.toString();
    }
}
