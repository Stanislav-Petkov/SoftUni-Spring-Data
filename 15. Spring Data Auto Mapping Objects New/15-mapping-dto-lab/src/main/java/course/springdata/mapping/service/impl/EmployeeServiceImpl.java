package course.springdata.mapping.service.impl;

import course.springdata.mapping.dao.EmployeeRepository;
import course.springdata.mapping.entity.Employee;
import course.springdata.mapping.exception.NonexistingEntityException;
import course.springdata.mapping.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public List<Employee> getAllManagers() {
        return employeeRepo.getManagers();
    }

    @Override
    public List<Employee> getAllEmployeesBornBefore(LocalDate toDate) {
        return employeeRepo.findAllByBirthdateBeforeOrderBySalaryDesc(toDate);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepo.findById(id).orElseThrow(
                () -> new NonexistingEntityException(
                        String.format("Employee with ID = %s does not exist.",id))
        );
    }

    @Override
    @Transactional
    public Employee addEmployee(Employee employee) {
        employee.setId(null);
        if(employee.getManager() != null){
            // If the employee has a manager, we should add the employee to his
            // manager subordinates list
            employee.getManager().getSubordinates().add(employee);
        }
        return employeeRepo.save(employee);
    }

    @Override
    @Transactional
    public Employee updateEmployee(Employee employee) {
        // If the entity exists it will be update, if not, there will be an exception
        Employee existing = getEmployeeById(employee.getId());
        Employee updated = employeeRepo.save(employee);
        if(existing.getManager() != null &&
                !existing.getManager().equals(employee.getManager())){
            // If the existing employee has a manager and this manager is
            // different from the new employee manager
            // Remove the existing employee from the subordinates of the manager
            // of the existing employee
            existing.getManager().getSubordinates().remove(existing);
        }
        if(updated.getManager() != null &&
                !updated.getManager().equals(existing.getManager())){
            updated.getManager().getSubordinates().add(updated);
        }
        return updated;
    }

    @Override
    @Transactional
    public Employee deleteEmployee(Long id) {
        Employee removed = getEmployeeById(id);
        if(removed.getManager() != null){
            removed.getManager().getSubordinates().remove(removed);
        }
        employeeRepo.deleteById(id);
        return removed;
    }

    @Override
    public long getEmployeeCount() {
        return employeeRepo.count();
    }
}
