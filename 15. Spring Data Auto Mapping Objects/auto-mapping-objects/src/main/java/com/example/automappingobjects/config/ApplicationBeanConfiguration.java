package com.example.automappingobjects.config;

import com.example.automappingobjects.data.entities.Employee;
import com.example.automappingobjects.services.dto.EmployeeViewDto;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Here is the configuration of our beans

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {

        // make custom mapping when the field name in the dto class is not written correctly and
        // cannot be auto mapped instead of cityName the name of the field is address
        // modelMapper sets null to address field in the dto class if the name is not correct

        ModelMapper modelMapper = new ModelMapper();

//        TypeMap<Employee, EmployeeViewDto> typeMap = modelMapper.createTypeMap(Employee.class, EmployeeViewDto.class);
        // Source                   , Destination
//        typeMap.addMappings(m -> m.map(src -> src.getCity().getName(), EmployeeViewDto::setAddress));

        // The Converter converts fields
        // From mappingContext I can access source and destination
        Converter<String, String> stringConverter = new Converter<String, String>() {
            @Override
            public String convert(MappingContext<String, String> mappingContext) {
                return mappingContext.getSource() == null ? null : mappingContext.getSource().toLowerCase();
            }
        };

                      // Source, Destination
        PropertyMap<Employee, EmployeeViewDto> propertyMap = new PropertyMap<Employee, EmployeeViewDto>() {
            @Override
            protected void configure() {
                // map() refers to the destination and sets to it an address from the source
                map().setAddress(source.getCity().getName());
                using(stringConverter).map(source.getFirstName()).setFirstName(null);
            }
        };

        //  Error Cannot map final type java.lang.String.
        //When we use propertyMap we cannot use methods of strings numbers or others like
        // map().setAddress(source.getCity().getName().toLowerCase()); we should use Converter

        modelMapper.addMappings(propertyMap);
        return modelMapper;
    }
}

// When we use automatic mapping from the modelMapper.map() method
/*
@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
 */

/*

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {

        // make custom mapping when the field name in the dto class is not written correctly and
        // cannot be auto mapped instead of cityName the name of the field is address

        ModelMapper modelMapper = new ModelMapper();
                    // Source, Destination
        PropertyMap<Employee, EmployeeViewDto> propertyMap = new PropertyMap<Employee, EmployeeViewDto>() {
            @Override
            protected void configure() {
                // map() refers to the destination and sets to it an address from the source
                map().setAddress(source.getCity().getName());
            }
        };

        modelMapper.addMappings(propertyMap);
        return modelMapper;
    }
}
 */

/*

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {

        // make custom mapping when the field name in the dto class is not written correctly and
        // cannot be auto mapped instead of cityName the name of the field is address

        ModelMapper modelMapper = new ModelMapper();

        TypeMap<Employee, EmployeeViewDto> typeMap = modelMapper.createTypeMap(Employee.class, EmployeeViewDto.class);
        // Source                   , Destination
        typeMap.addMappings(m -> m.map(src -> src.getCity().getName(), EmployeeViewDto::setAddress));

                      // Source, Destination
//        PropertyMap<Employee, EmployeeViewDto> propertyMap = new PropertyMap<Employee, EmployeeViewDto>() {
//            @Override
//            protected void configure() {
//                // map() refers to the destination and sets to it an address from the source
//                map().setAddress(source.getCity().getName());
//            }
//        };

//        modelMapper.addMappings(propertyMap);
        return modelMapper;
    }
}
 */

/*
// Add converter to modelMapper
@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {

        // make custom mapping when the field name in the dto class is not written correctly and
        // cannot be auto mapped instead of cityName the name of the field is address

        ModelMapper modelMapper = new ModelMapper();

//        TypeMap<Employee, EmployeeViewDto> typeMap = modelMapper.createTypeMap(Employee.class, EmployeeViewDto.class);
        // Source                   , Destination
//        typeMap.addMappings(m -> m.map(src -> src.getCity().getName(), EmployeeViewDto::setAddress));

        // The Converter converts fields
        // From mappingContext I can access source and destination
        Converter<String, String> stringConverter = new Converter<String, String>() {
            @Override
            public String convert(MappingContext<String, String> mappingContext) {
                return mappingContext.getSource() == null ? null : mappingContext.getSource().toLowerCase();
            }
        };

                      // Source, Destination
        PropertyMap<Employee, EmployeeViewDto> propertyMap = new PropertyMap<Employee, EmployeeViewDto>() {
            @Override
            protected void configure() {
                // map() refers to the destination and sets to it an address from the source
                map().setAddress(source.getCity().getName());
            }
        };

        //  Error Cannot map final type java.lang.String.
        //When we use propertyMap we cannot use methods of strings numbers or others like
        // map().setAddress(source.getCity().getName().toLowerCase()); we should use Converter

        modelMapper.addConverter(stringConverter);
        modelMapper.addMappings(propertyMap);
        return modelMapper;
    }
}
 */

/*
@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper() {

        // make custom mapping when the field name in the dto class is not written correctly and
        // cannot be auto mapped instead of cityName the name of the field is address

        ModelMapper modelMapper = new ModelMapper();

//        TypeMap<Employee, EmployeeViewDto> typeMap = modelMapper.createTypeMap(Employee.class, EmployeeViewDto.class);
        // Source                   , Destination
//        typeMap.addMappings(m -> m.map(src -> src.getCity().getName(), EmployeeViewDto::setAddress));

        // The Converter converts fields
        // From mappingContext I can access source and destination
        Converter<String, String> stringConverter = new Converter<String, String>() {
            @Override
            public String convert(MappingContext<String, String> mappingContext) {
                return mappingContext.getSource() == null ? null : mappingContext.getSource().toLowerCase();
            }
        };

                      // Source, Destination
        PropertyMap<Employee, EmployeeViewDto> propertyMap = new PropertyMap<Employee, EmployeeViewDto>() {
            @Override
            protected void configure() {
                // map() refers to the destination and sets to it an address from the source
                map().setAddress(source.getCity().getName());
//                using(stringConverter).map().setFirstName(source.getFirstName());
                using(stringConverter).map(source.getFirstName()).setFirstName(null);
            }
        };

        //  Error Cannot map final type java.lang.String.
        //When we use propertyMap we cannot use methods of strings numbers or others like
        // map().setAddress(source.getCity().getName().toLowerCase()); we should use Converter

        modelMapper.addMappings(propertyMap);
        return modelMapper;
    }
}
 */