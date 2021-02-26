package softuni.library.models.dtos.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "libraries")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibraryRootSeedDto {

    @XmlElement(name = "library")
    private List<LibrarySeedDto> libraries;

    public LibraryRootSeedDto() {
    }

    public List<LibrarySeedDto> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<LibrarySeedDto> libraries) {
        this.libraries = libraries;
    }
}
