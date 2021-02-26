package softuni.library.models.dtos.xmls;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "characters")
@XmlAccessorType(XmlAccessType.FIELD)
public class CharacterSeedRootDto {

    @XmlElement(name = "character")
    private List<CharacterSeedDto> characters;

    public CharacterSeedRootDto() {
    }

    public List<CharacterSeedDto> getCharacters() {
        return characters;
    }

    public void setCharacters(List<CharacterSeedDto> characters) {
        this.characters = characters;
    }
}
