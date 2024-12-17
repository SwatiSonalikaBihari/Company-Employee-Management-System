package Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee {
	@Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private int EmpId;
  @Column(nullable=false)
  private String empName;
  @Column(unique=true)
  private String email;
  @Column(nullable=false)
  private int salary;
  @ManyToOne
  private Company company;
public int getEmpId() {
	return EmpId;
}
public void setEmpId(int empId) {
	EmpId = empId;
}
public String getEmpName() {
	return empName;
}
public void setEmpName(String empName) {
	this.empName = empName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getSalary() {
	return salary;
}
public void setSalary(int salary) {
	this.salary = salary;
}
public Company getCompany() {
	return company;
}
public void setCompany(Company company) {
	this.company = company;
}
  
}
