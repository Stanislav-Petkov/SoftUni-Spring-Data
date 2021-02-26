//package course.springdata.codeFirstJoinedStrategy;
//
//import course.springdata.codefirst.entity.Vehicle;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//
//@Entity
//@Table(name = "cars")
//public class Car extends Vehicle {
//    private int seats;
//
//    public Car(){}
//
//    public Car(String model, BigDecimal price, String fuelType, int seats) {
//        super(model, price, fuelType);
//        this.seats = seats;
//    }
//
//    public Car(Long id, String model, BigDecimal price, String fuelType, int seats) {
//        super(id, model, price, fuelType);
//        this.seats = seats;
//    }
//
//    public int getSeats() {
//        return seats;
//    }
//
//    public void setSeats(int seats) {
//        this.seats = seats;
//    }
//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("Car{");
//        sb.append(super.toString());
//        sb.append("seats=").append(seats);
//        sb.append('}');
//        return sb.toString();
//    }
//}
