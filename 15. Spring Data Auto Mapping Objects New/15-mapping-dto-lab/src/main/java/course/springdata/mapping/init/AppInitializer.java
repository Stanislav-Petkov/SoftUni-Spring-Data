package course.springdata.mapping.init;

import course.springdata.mapping.dto.EmployeeDto;
import course.springdata.mapping.dto.ManagerDto;
import course.springdata.mapping.entity.Address;
import course.springdata.mapping.entity.Employee;
import course.springdata.mapping.service.AddressService;
import course.springdata.mapping.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppInitializer implements CommandLineRunner {

    private final EmployeeService employeeService;
    private final AddressService addressService;

    @Autowired
    public AppInitializer(EmployeeService employeeService, AddressService addressService) {
        this.employeeService = employeeService;
        this.addressService = addressService;
    }

    @Override
    public void run(String... args) throws Exception {
        ModelMapper mapper = new ModelMapper();
        //Ex 1. Create address and employee and map it to EmployeeDto
        Address address1 = new Address("Bulgaria", "Sofia",
                "Grag Ignatiev 50");
        // Persist the address and get it with its id from the database
        address1 = addressService.addAddress(address1);

        Employee employee1 = new Employee("Ivan", "Petrov", 3500,
                LocalDate.of(1981, 5, 12),
                address1);
        employee1 = employeeService.addEmployee(employee1);
        EmployeeDto employeeDto = mapper.map(employee1, EmployeeDto.class);
        System.out.printf("EmployeeDto: %s%n", employeeDto);

        //Ex 2 TypeMapping mapping addresses and employees to ManagerDto with
        // EmployeeDtos as subordinates

        List<Address> addresses = List.of(
                new Address("Bulgaria", "Sofia", "ul. g.s. Rakovski, 50"),
                new Address("Bulgaria", "Sofia", "bul. Dondukov , 40"),
                new Address("Bulgaria", "Sofia", "ul. Hristo smirnenski, 40"),
                new Address("Bulgaria", "Sofia", "bul. Aleksander Malinov, 93a"),
                new Address("Bulgaria", "Sofia", "bul. Slivnica, 50"),
                new Address("Bulgaria", "Sofia", "ul. Angel Kanchev, 34")
        );
        // Persist the addresses in addressService::addAddress
        addresses = addresses.stream().map(addressService::addAddress).collect(Collectors.toList());

        List<Employee> employees = List.of(
                new Employee("Steve", "Adams", 5780, LocalDate.of(1982, 3, 12),
                        addresses.get(0)),
                new Employee("Stephen", "Petrov", 2760, LocalDate.of(1974, 5, 19),
                        addresses.get(1)),
                new Employee("Hristina", "Petrova", 3600, LocalDate.of(1989, 11, 12),
                        addresses.get(1)),
                new Employee("Diana", "Atanasova", 6790, LocalDate.of(1989, 12, 12),
                        addresses.get(2)),
                new Employee("Samuil", "Georgiev", 4780, LocalDate.of(1979, 2, 1),
                        addresses.get(3)),
                new Employee("Ivan", "Petrov", 3780, LocalDate.of(1985, 3, 12),
                        addresses.get(4)),
                new Employee("Slavi", "Petrov", 3960, LocalDate.of(1982, 3, 12),
                        addresses.get(5))
        );
        // Get the persisted employees
        List<Employee> created = employees.stream().map(employeeService::addEmployee).collect(Collectors.toList());

        created.get(1).setManager(created.get(0));
        created.get(2).setManager(created.get(0));

        created.get(4).setManager(created.get(3));
        created.get(5).setManager(created.get(3));
        created.get(6).setManager(created.get(3));

        // save the changes to the db
        List<Employee> updated = created.stream().map(employeeService::updateEmployee)
                .collect(Collectors.toList());

        // Fetch all managers and map them to ManagerDto
        // Use createTypeMap to create a new mapping from Employee to ManagerDto
        TypeMap<Employee, ManagerDto> managerTypeMap = mapper.createTypeMap(Employee.class, ManagerDto.class)
                .addMappings(m -> {
                    m.map(Employee::getSubordinates, ManagerDto::setEmployees);
                    m.map(src -> src.getAddress().getCity(), ManagerDto::setCity);
                    // Set which field in the destination dto will not be mapped
                    //m.skip(ManagerDto::setCity);
                });

        // There is already a created mapping on line 44
        //    EmployeeDto employeeDto = mapper.map(employee1, EmployeeDto.class);
        //    and we can get it now with getTypeMap
        mapper.getTypeMap(Employee.class,EmployeeDto.class).addMapping(
                src -> src.getAddress().getCity(),EmployeeDto::setCity
        );
        // Checks if there are fields in the ManagerDto that are not set
        mapper.validate();

        List<Employee> managers = employeeService.getAllManagers();
        List<ManagerDto> managerDtos = managers.stream().map(managerTypeMap::map).collect(Collectors.toList());
        managerDtos.forEach(System.out::println);

        System.out.println(" ------  Ex 3  employees born before 1990 with manager last name  -----");

        // ex 3 employees born before 1990 with manager last name
        TypeMap employeeMap2 = mapper.getTypeMap(Employee.class, EmployeeDto.class).addMapping(
                src -> src.getManager().getLastName(),EmployeeDto::setManagerLastName
        );

        List<Employee> employeesBefore1990 = employeeService.
                getAllEmployeesBornBefore(LocalDate.of(1990,1,1));
//        employeesBefore1990.forEach(System.out::println);
        employeesBefore1990.stream().map(employeeMap2::map).forEach(System.out::println);
    }
}
