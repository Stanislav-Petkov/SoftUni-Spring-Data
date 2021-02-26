package softuni.library.config;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate>{
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}

//public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {
//    @Override
//    public LocalDateTime unmarshal(String s) throws Exception {
//        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//    }
//
//    @Override
//    public String marshal(LocalDateTime localDateTime) throws Exception {
//        return localDateTime.toString();
//    }
//}