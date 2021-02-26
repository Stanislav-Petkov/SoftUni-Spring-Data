package com.example.demo.service.services;

import com.example.demo.data.entities.Phone;
import com.example.demo.data.repositories.PhoneRepository;
import com.example.demo.service.dtos.PhoneDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImpl implements PhoneService{

    private final PhoneRepository phoneRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PhoneServiceImpl(PhoneRepository phoneRepository, ModelMapper modelMapper) {
        this.phoneRepository = phoneRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(PhoneDto phoneDto) {
        this.phoneRepository.save(this.modelMapper.map(phoneDto, Phone.class));
    }
}
