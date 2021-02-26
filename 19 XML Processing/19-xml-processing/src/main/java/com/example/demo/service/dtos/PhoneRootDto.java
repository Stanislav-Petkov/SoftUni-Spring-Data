package com.example.demo.service.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "phones")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneRootDto {

    // every single PhoneDto in the list has a phone tag in the xml
    @XmlElement(name = "phone")
    private List<PhoneDto> phoneDtoList;

    public PhoneRootDto() {
    }

    public List<PhoneDto> getPhoneDtoList() {
        return phoneDtoList;
    }

    public void setPhoneDtoList(List<PhoneDto> phoneDtoList) {
        this.phoneDtoList = phoneDtoList;
    }
}
