import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
//        task 2
//        this.removeObjectsEx();
//        this.removeObjectExWithUpdate();
//        task 3
//        try {
//            this.containsEmployeeEx();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        task 4
//        this.employeeWithSalaryOver50000();
//        task 5
//        this.employeesFromDepartment();
//        task 6
//        try {
//            addNewAddress();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        task 6
//        try {
//            this.addingNewAddressAndAddItToEmp();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        task 7
//         this.getAddressesWithEmployeeCount();
//        task 8
//        try {
//            this.getEmployeeWithProject();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        task 9
//        this.findLatestTenProjects();
//        task 10
//        this.increaseSalaries();

//        task 12
//        this.findEmployeeByFirstName();
//        task 13
//        this.employeesMaximumSalaries();
//        task 11
//        try {
//            this.removeTowns();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    /**
     * Task 13
     * Write a program that finds the max salary for each department. Filter the departments,
     * which max salaries are not in the range between 30000 and 70000
     */
    private void employeesMaximumSalaries() {
        this.entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.salary IN" +
                        " (SELECT MAX(e.salary) FROM Employee e" +
                        " GROUP BY e.department.id " +
                        " HAVING MAX(e.salary) < 30000 OR MAX(e.salary) > 70000) ", Employee.class)
                .getResultStream()
                .forEach(e -> System.out.printf("%s %.2f %n", e.getDepartment().getName(), e.getSalary()));
    }

    /**
     * Task 12 Find employee by first name
     * Write a program that finds all employees, whose first name starts with a pattern given as an input from
     * the console. Print their first and last names, their job title and salary.
     */
    private void findEmployeeByFirstName() {
        this.entityManager
                .createQuery("SELECT e FROM Employee AS e WHERE e.firstName LIKE 'SA%'", Employee.class)
                .getResultStream()
                .forEach(e -> System.out.printf("%s %s - %s ($%.2f)%n",
                        e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));
    }

    /**
     Task 11 Remove Towns
     Write a program that deletes a town, which name is given as an input.
     The program should delete all addresses
     that are in the given town. Print on the console the number of addresses that were deleted.
     */
    private void removeTowns() throws IOException {
        System.out.print("Input town name : ");
        String townNameInput = reader.readLine();
        this.entityManager.getTransaction().begin();

        // Get all employees from Sofia
        List<Employee> resultList = this.entityManager
                .createQuery("SELECT e FROM Employee e WHERE e.address.town.name = :townName", Employee.class)
                .setParameter("townName",townNameInput)
                .getResultList();

        // Set different address id for the employees from townNameInput
        this.entityManager.createQuery(" UPDATE Employee em SET em.address = 1 WHERE em IN :list")
                .setParameter("list", resultList)
                .executeUpdate();

        // Get the number of addresses with town townNameInput from addresses table
        List<Address> listOfTowns = this.entityManager
                .createQuery(" SELECT a FROM Address AS a WHERE a.town.name = :townName ", Address.class)
                .setParameter("townName", townNameInput)
                .getResultList();

        if (listOfTowns.size() <= 1) {
            System.out.printf("%d address in %s deleted", listOfTowns.size(), townNameInput);
        } else {
            System.out.printf("%d addresses in %s deleted", listOfTowns.size(), townNameInput);
        }

        // Delete all addresses with town townNameInput from addresses table
        this.entityManager
                .createQuery(" DELETE FROM Address AS a WHERE a.town = (SELECT t FROM Town t WHERE t.name = :townName) ")
                .setParameter("townName", townNameInput)
                .executeUpdate();

        // Delete the town townNameInput from the towns table
        this.entityManager
                .createQuery("DELETE FROM Town t WHERE t.name = :townName")
                .setParameter("townName", townNameInput)
                .executeUpdate();

        this.entityManager.getTransaction().commit();
    }

    /**
     * Task 10 Increase Salaries
     * Write a program that increases the salaries of all employees, who are in the Engineering,
     * Tool Design, Marketing or Information Services departments by 12%.
     * Then print the first name, the last name and the salary for the employees, whose salary was increased.
     */
    private void increaseSalaries() {
        this.entityManager.getTransaction().begin();
        this.entityManager
                .createQuery(
                        " UPDATE Employee e " +
                                " SET e.salary = e.salary * 1.12 WHERE e.department.id IN " +
                                "(SELECT d.id FROM Department d WHERE d.name IN " +
                                "('Engineering', 'Marketing', 'Tool Design', 'Information Services'))")
                .executeUpdate();
        this.entityManager.getTransaction().commit();

        this.entityManager
                .createQuery("SELECT e FROM Employee AS e " +
                                "WHERE e.department.id IN (SELECT d.id FROM Department d WHERE d.name IN " +
                                "('Engineering', 'Marketing', 'Tool Design', 'Information Services'))"
                        , Employee.class).getResultStream()
                .forEach(e -> System.out.printf("%s %s ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getSalary()));
    }


    /**
     * Task 9 Find Latest 10 Projects
     * Write a program that prints the last 10 started projects. Print their name, description,
     * start and end date and sort them by name lexicographically. For the output, check the format from the example.
     */
    private void findLatestTenProjects() {
        this.entityManager
                .createQuery(
                        " SELECT p FROM Project AS p " +
                                " ORDER BY p.startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultStream()
                .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                .forEach(p -> System.out.printf(
                        "Project name: %s %n" +
                                "      Project Description: %s %n" +
                                "      Project Start Date:%s %n" +
                                "      Project End Date: %s %n",
                        p.getName(),
                        p.getDescription(),
                        p.getStartDate(),
                        p.getEndDate()));
    }

    /**
     * Task 8 Get Employee with Project
     * Get an employee by his/her id. Print only his/her first name, last name, job title and projects
     * (only their names). The projects should be ordered by name (ascending).
     * The output should be printed in the format given in the example.
     */
    private void getEmployeeWithProject() throws IOException {
        int idInput = 83;
        this.entityManager
                .createQuery("SELECT e FROM Employee AS e " +
                        "WHERE e.id = :id", Employee.class)
                .setParameter("id", idInput)
                .getResultStream()
                .forEach(e -> System.out.printf("%s %s - %s%n",
                        e.getFirstName(),
                        e.getLastName(),
                        e.getJobTitle()));

        this.entityManager
                .createQuery("SELECT e FROM Employee AS e " +
                        "WHERE e.id = :id ", Employee.class)
                .setParameter("id", idInput)
                .getResultStream()
                .forEach(r -> r.getProjects()
                        .stream()
                        .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                        .forEach(p -> System.out.printf("    %s%n", p.getName())));
    }

    /**
     * 7.	Addresses with Employee Count
     * Find all addresses, ordered by the number of employees who live there (descending).
     * Take only the first 10 addresses and print their address text, town name and employee count.
     */
    private void getAddressesWithEmployeeCount() {
        this.entityManager
                .createQuery(
                        " SELECT e " +
                                " FROM Employee AS e " +
                                " GROUP BY e.address.id " +
                                "ORDER BY count(e.address.id) DESC ", Employee.class)
                .setMaxResults(10)
                .getResultStream()
                .forEach(e -> System.out.printf("%s, %s - %d employees%n",
                        e.getAddress().getText(),
                        e.getAddress().getTown().getName(),
                        e.getAddress().getEmployees().size()));
    }


    /**
     * Task 6 Adding a New Address and Updating Employee
     * Create a new address with text "Vitoshka 15". Set that address to an employee with a last name, given as an input.
     */
    private void addingNewAddressAndAddItToEmp() throws IOException {
        System.out.println("Enter emp last name: ");
        String lastName = this.reader.readLine();

        Employee employee = this.entityManager
                .createQuery("SELECT e FROM Employee e " +
                        "WHERE e.lastName = :name", Employee.class)
                .setParameter("name", lastName)
                .getSingleResult();
        System.out.println();
        Address address = this.createNewAddress("Vitoshka 15");

        this.entityManager.getTransaction().begin();
        this.entityManager.detach(employee);
        employee.setAddress(address);
        this.entityManager.merge(employee);
        this.entityManager.flush();
        this.entityManager.getTransaction().commit();
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

    /**
     * Task 6 Adding a New Address and Updating Employee
     * Create a new address with text "Vitoshka 15". Set that address to an employee with a last name, given as an input.
     */
    private void addNewAddress() throws IOException {
        Address address = new Address();
        address.setText("Vitoshka 15");
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(address);


        String name = reader.readLine();
        Employee employee = this.entityManager
                .createQuery("SELECT e FROM Employee AS e " +
                        " WHERE e.lastName = :lastName", Employee.class)
                .setParameter("lastName", name).getSingleResult();

        employee.setAddress(address);

        this.entityManager.getTransaction().commit();
    }

    /**
     * Task 5 Employees from Department
     * Extract all employees from the Research and Development department. Order them by salary (in ascending order),
     * then by id (in ascending order). Print only their first name, last name, department name and salary.
     */
    private void employeesFromDepartment() {
        List<Employee> employeeList = this.entityManager
                .createQuery(
                        "SELECT e" +
                                " FROM Employee AS e " +
                                " WHERE e.department.name = 'Research and Development' " +
                                " ORDER BY e.salary , e.id ", Employee.class)
                .getResultList();
        for (Employee employee : employeeList) {
            System.out.println(employee.getFirstName() + " " + employee.getLastName()
                    + " from " + employee.getDepartment().getName() + " - $" + employee.getSalary());
        }
    }

    /**
     * Task 4 Employees with salary over 50000
     * Write a program that gets the first name of all employees who have salary over 50 000.
     */
    private void employeeWithSalaryOver50000() {
        this.entityManager
                .createQuery("SELECT e FROM Employee AS e" +
                        " WHERE e.salary > 50000", Employee.class)
                .getResultStream()
                .forEach(e -> System.out.printf("%s%n", e.getFirstName()));
    }

    /**
     * Task 2 Remove Objects
     * Use the soft_uni database. Persist all towns from the database. Detach those whose name length is
     * more than 5 symbols. Then transform the names of all attached towns to lowercase and save them to the database.
     */
    private void removeObjectExWithUpdate() {
        this.entityManager.getTransaction().begin();
        this.entityManager
                .createQuery(
                        "UPDATE Town t" +
                                " SET t.name = LOWER(t.name)" +
                                " WHERE length(t.name) <= 5").executeUpdate();
        this.entityManager.getTransaction().commit();
    }

    /**
     * Task 3 Contains Employee
     * Use the soft_uni database. Write a program that checks if a given employee name is contained in the database.
     */
    private void containsEmployeeEx() throws IOException {
        System.out.println("Enter employee full name: ");
        String fullName = this.reader.readLine();

        try {
            Employee employee = this.entityManager
                    .createQuery("SELECT e FROM Employee AS e" +
                            " WHERE concat(e.firstName,' ',e.lastName) = :name", Employee.class)
                    .setParameter("name", fullName)
                    .getSingleResult();
            System.out.println("Yes");
        } catch (NoResultException nre) {
            System.out.println("No");
        }
    }


    /**
     * Task 2 Remove Objects
     * Use the soft_uni database. Persist all towns from the database. Detach those whose name length is
     * more than 5 symbols. Then transform the names of all attached towns to lowercase and save them to the database.
     */
    private void removeObjectsEx() {
        List<Town> townsLengthMoreThanFive = this.entityManager
                .createQuery("SELECT t FROM Town AS t WHERE length(t.name) > 5", Town.class)
                .getResultList();

        List<Town> townsLengthLessThanFive = this.entityManager
                .createQuery("SELECT t FROM Town AS t WHERE length(t.name) <= 5 ", Town.class)
                .getResultList();

        this.entityManager.getTransaction().begin();
        townsLengthMoreThanFive.forEach(t -> this.entityManager.detach(t));
        townsLengthLessThanFive.forEach(t -> this.entityManager.detach(t));

        for (Town t : townsLengthMoreThanFive) {
            t.setName(t.getName().toUpperCase());
        }
        for (Town t : townsLengthLessThanFive) {
            t.setName(t.getName().toLowerCase());
        }

        townsLengthMoreThanFive.forEach(t -> this.entityManager.merge(t));
        townsLengthLessThanFive.forEach(t -> this.entityManager.merge(t));
        this.entityManager.flush();
        this.entityManager.getTransaction().commit();
    }
}

