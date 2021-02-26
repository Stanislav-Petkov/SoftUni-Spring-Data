package demos.hibernate.model;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Student {
    // Auto generated in database
    private int id;


    // makes the field argument of the required constructor
    @NonNull
    private String name;
    private Date registrationDate = new Date();
}
