package com.example.demo.services.impl;


import com.example.demo.domain.dtos.export.query1.CustomerOrderedExportDto;
import com.example.demo.domain.dtos.export.query1.CustomerOrderedExportRootDto;
import com.example.demo.domain.dtos.imports.CustomerImportDto;
import com.example.demo.domain.dtos.imports.CustomerImportRootDto;
import com.example.demo.domain.entities.Customer;
import com.example.demo.domain.repositories.CustomerRepository;
import com.example.demo.services.CustomerService;
import com.example.demo.utils.XmlParser;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final static String CUSTOMER_PATH = "src/main/resources/xmls/customers.xml";
    private final static String CUSTOMERS_ORDERED_PATH = "src/main/resources/xmls/exported/ordered-customers.xml";

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final XmlParser xmlParser;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               ModelMapper modelMapper, Gson gson, XmlParser xmlParser) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.xmlParser = xmlParser;
    }


    @Override
    public void seedCustomers() throws IOException, JAXBException {
        CustomerImportRootDto customerImportRootDto = this.xmlParser
                .parseXml(CustomerImportRootDto.class, CUSTOMER_PATH);
        List<CustomerImportDto> customers = customerImportRootDto.getCustomers();
        for (CustomerImportDto customerImportDto : customers) {
            Customer customer = this.modelMapper.map(customerImportDto, Customer.class);
            this.customerRepository.saveAndFlush(customer);
        }
    }

    @Override
    public void exportOrdered() throws JAXBException {
        List<CustomerOrderedExportDto> orderedCustomers = this.customerRepository.findAllAndSort()
                .stream()
                .map(c -> this.modelMapper.map(c, CustomerOrderedExportDto.class))
                .collect(Collectors.toList());
        CustomerOrderedExportRootDto rootDto =
                new CustomerOrderedExportRootDto();

        rootDto.setCustomers(orderedCustomers);
        this.xmlParser.exportXml(rootDto,CustomerOrderedExportRootDto.class,
                CUSTOMERS_ORDERED_PATH);
    }

//    @Override
//    public String orderedCustomers() {
//        Set<Customer> allByOrderByYoungDriverAscBirthDateAsc = this
//                .customerRepository.getAllByOrderByBirthDateAscYoungDriverAsc();
//        List<CustomerExportDto> toExport = new ArrayList<>();
//        for(Customer customer : allByOrderByYoungDriverAscBirthDateAsc){
//            CustomerExportDto customerExport = this.modelMapper.map(customer, CustomerExportDto.class);
//            toExport.add(customerExport);
//        }
//        return this.gson.toJson(toExport);
//    }
}
