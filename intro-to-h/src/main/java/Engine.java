import entities.*;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;

public class Engine implements Runnable {

    private final EntityManager entityManager;
    private final BufferedReader reader;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void run() {
        //Ex 2
//        this.removeObjectsEx();
        //Ex 3
//        try {
//            this.containsEmployeeEx();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        // Ex 4
//        this.getFirstNameOfEmployeeWithSalaryOver50000();
        // Ex 5
//        this.getEmployeesFromDepartment();
        // Ex 6
//        try {
//            this.addNewAddressToEmployee();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //Ex 7 e.size
//        this.addressesWithEmployeeCount();
        //Ex 8
//        try {
//            this.getEmployeesWithProject();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //Ex 9 setMaxResult(10)
//        this.findLatestTenProjects();
        // Ex 10
//        this.increaseSalaries();
        // Ex 11
//        try {
//            this.removeTown();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //Ex 12
//        try {
//            this.findEmployeesByFirstName();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //Ex 13 group by having
        this.getEmployeesMaxSalaries();
    }

    private void getEmployeesMaxSalaries() {
        List<Object[]> objects = this.entityManager.createQuery(
                "SELECT d.name, MAX(e.salary) " +
                        "FROM Employee AS e " +
                        "JOIN Department AS d on e.department.id = d.id " +
                        "GROUP BY e.department.id " +
                        "HAVING MAX(e.salary) > 70000 OR MAX(e.salary) < 30000 ",Object[].class)
                .getResultList();
        for (Object[] object : objects) {
            System.out.println(object[0] + " " + object[1]);
        }

        System.out.println();
    }

    private void findEmployeesByFirstName() throws IOException {
        System.out.println("Input :");
        String input = reader.readLine();
        this.entityManager.createQuery("SELECT e FROM Employee AS e " +
                "WHERE e.firstName LIKE :in",Employee.class)
                .setParameter("in",input + "%")
                .getResultStream()
                .forEach(e -> System.out.printf("%s %s - %s - ($%.02f)%n",
                        e.getFirstName(),e.getLastName(),e.getJobTitle(), e.getSalary()));
    }

    private void removeTown() throws IOException {
        System.out.println("Enter town: ");
        String townInput = reader.readLine();
        List<Employee> employees = this.entityManager.createQuery("SELECT e FROM Employee AS e " +
                "WHERE e.address.town.name = :name", Employee.class)
                .setParameter("name", townInput)
                .getResultList();

        Address newAddressToSet = this.entityManager.createQuery("SELECT a FROM Address AS a " +
                "WHERE a.id = 290", Address.class)
                .getSingleResult();

        // GET THE TOWN TO BE DELETED
        Town town = this.entityManager.createQuery("SELECT t FROM Town AS t " +
                "WHERE t.name = :to", Town.class)
                .setParameter("to",townInput)
                .getSingleResult();

        List<Address> addressesToBeChanged = this.entityManager.createQuery("SELECT a FROM Address AS a " +
                "WHERE a.town.id = :idOfTown", Address.class)
                .setParameter("idOfTown", town.getId())
                .getResultList();


        // Set another address id 290 on every employee of the 3 employees From town Sofia
        this.entityManager.getTransaction().begin();
        for (Employee employee : employees) {
            this.entityManager.detach(employee);
            employee.setAddress(newAddressToSet);
            this.entityManager.merge(employee);
        }
        this.entityManager.flush();

        for (Address address1 : addressesToBeChanged) {
            this.entityManager.detach(address1);
            this.entityManager.createQuery("DELETE FROM Address AS a WHERE a.id = :ad")
                    .setParameter("ad", address1.getId())
                    .executeUpdate();
            this.entityManager.flush();
        }

        // DELETE THE TOWN
        this.entityManager.createQuery("DELETE FROM Town AS t WHERE t.id = :to")
                .setParameter("to", town.getId())
                .executeUpdate();


        this.entityManager.flush();
        this.entityManager.getTransaction().commit();

        if (addressesToBeChanged.size() == 1) {
            System.out.println("1 address in " + townInput + " deleted ");
        } else if ((addressesToBeChanged.size() == 0)) {
            System.out.println("0 addresses in " + townInput + " deleted ");
        } else {
            System.out.println(addressesToBeChanged.size() + " address in " + townInput + " deleted ");
        }
    }


    private void increaseSalaries() {
        List<Employee> employees = this.entityManager.createQuery("SELECT e FROM Employee AS e" +
                " WHERE e.department.name " +
                "IN ('Engineering','Tool Design','Marketing','Information Service')", Employee.class)
                .getResultList();
        this.entityManager.getTransaction().begin();
        for (Employee employee : employees) {
            this.entityManager.detach(employee);
            employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12)));
            System.out.println(employee.getFirstName() + " " + employee.getLastName() +
                    String.format(" ($%.02f)", employee.getSalary()));
            this.entityManager.merge(employee);
        }
        this.entityManager.flush();
        this.entityManager.getTransaction().commit();
    }

    private void findLatestTenProjects() {
        this.entityManager.createQuery("SELECT p FROM Project AS p " +
                "ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultStream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .forEach(p -> System.out.printf("Project name: %s%n" +
                                "   Project Description: %s%n" +
                                "   Project Start Date:%s%n" +
                                "   Project End Date:%s%n",
                        p.getName(), p.getDescription(), p.getStartDate(), p.getEndDate()));
    }

    private void getEmployeesWithProject() throws IOException {
        System.out.println("Input Employee id: ");
        int idInput = Integer.parseInt(reader.readLine());
        Employee employee = this.entityManager.createQuery("SELECT e FROM Employee AS e " +
                "WHERE e.id = :id AND e.projects.size > 0", Employee.class)
                .setParameter("id", idInput)
                .getSingleResult();

        System.out.println(employee.getFirstName() + " " + employee.getLastName() + " - " +
                employee.getJobTitle());

        employee.getProjects()
                .stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .forEach(p -> System.out.println("       " + p.getName()));
    }

    // Ex 7
    private void addressesWithEmployeeCount() {
        this.entityManager.createQuery("SELECT a FROM Address AS a " +
                "ORDER BY a.employees.size DESC ", Address.class)
                .setMaxResults(10)
                .getResultStream()
                .forEach(a -> System.out.printf("%s, %s - %d employees%n",
                        a.getText(),
                        a.getTown().getName(),
                        a.getEmployees().size()));
    }

    private void addNewAddressToEmployee() throws IOException {
        System.out.println("Enter last name : ");
        String lastName = reader.readLine();

        Employee employee = this.entityManager
                .createQuery("SELECT e FROM Employee AS e " +
                        "WHERE e.lastName = :name", Employee.class)
                .setParameter("name", lastName)
                .getSingleResult();
        Address address = this.createNewAddress("Vitoshka 15");

        this.entityManager.getTransaction().begin();
        this.entityManager.detach(employee);
        employee.setAddress(address);
        this.entityManager.merge(employee);
        this.entityManager.flush();
        this.entityManager.getTransaction().commit();
        System.out.println();
    }

    private Address createNewAddress(String textContent) {
        Address address = new Address();
        address.setText(textContent);

        System.out.println();
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(address);
        this.entityManager.getTransaction().commit();
        System.out.println();
        return address;
    }

    //Ex 5
    private void getEmployeesFromDepartment() {
        this.entityManager
                .createQuery("SELECT e FROM Employee AS e " +
                        "WHERE e.department.name = 'Research and Development' " +
                        "ORDER BY e.salary ASC, " +
                        "e.id ASC", Employee.class)
                .getResultStream()
                .forEach(employee -> System.out.printf("%s %s from %s - $%.2f%n",
                        employee.getFirstName(),
                        employee.getLastName(),
                        employee.getDepartment().getName(),
                        employee.getSalary()));


    }

    //Ex 4
    private void getFirstNameOfEmployeeWithSalaryOver50000() {
        this.entityManager
                .createQuery("SELECT e FROM Employee AS e " +
                        "WHERE e.salary > 50000", Employee.class)
                .getResultStream()
                .forEach(e -> System.out.printf("%s%n", e.getFirstName()));
    }


    // Ex. 3
    private void containsEmployeeEx() throws IOException {
        System.out.println("Enter employee full name: ");
        String fullName = this.reader.readLine();

        try {
            Employee employee = this.entityManager.createQuery("SELECT e FROM Employee AS e " +
                    "WHERE concat(e.firstName , ' ', e.lastName) = :name", Employee.class)
                    .setParameter("name", fullName)
                    .getSingleResult();

            System.out.println("Yes");
        } catch (NoResultException e) {
            System.out.println("No");
        }
        System.out.println();
    }


    // Ex 2
    private void removeObjectsEx() {
        List<Town> towns = this.entityManager.createQuery("SELECT t FROM Town AS t" +
                " WHERE length(t.name) > 5", Town.class)
                .getResultList();
        this.entityManager.getTransaction().begin();
        towns.forEach(this.entityManager::detach);

        for (Town town : towns) {
            town.setName(town.getName().toLowerCase());
        }

        towns.forEach(this.entityManager::merge);
        this.entityManager.flush();
        this.entityManager.getTransaction().commit();
    }
}
