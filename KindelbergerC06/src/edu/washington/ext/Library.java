package edu.washington.ext;

import java.util.ArrayList;
import java.util.List;

import edu.washington.ext.common.AbstractEmployee;

public class Library {
	
	private int libraryNumber;
	 private List<AbstractEmployee> staff;
	 private Librarian librarian;
	
	public Library(int libraryNumber) {
		this.libraryNumber = libraryNumber;
        staff = new ArrayList<AbstractEmployee>();
		
	}

	public int getLibraryNumber() {
		return libraryNumber;
	}
	
	public List<PayrollRecord> processPayroll() {
		List<PayrollRecord> payroll= new ArrayList<PayrollRecord>();
        double storeSales = 0;
        for (AbstractEmployee emp : staff) {
            PayrollRecord payRec;
            payRec = new PayrollRecord(emp.getName(), emp.calculatePay());
            payroll.add(payRec);
            storeSales += emp.getCurrentSales();
        }
        storeSales += librarian.getCurrentSales();
        librarian.setCurrentLibraryTotals(storeSales);
        PayrollRecord mgrPayRec;
        mgrPayRec = new PayrollRecord(librarian.getName(), librarian.calculatePay());
        payroll.add(mgrPayRec);

        return payroll;
	}
	
	public double getCurrentUsedBookSales() {
		 double storeSales = 0;
	        for (AbstractEmployee emp : staff) {
	            storeSales += emp.getCurrentSales();
	        }
	        storeSales += librarian.getCurrentSales();        
	        return storeSales;
	}
	
	public double getTotalCommissions() {
		double totalCommission = 0;
		double storeSales = 0;
        for (AbstractEmployee emp : staff) {
        	if (emp instanceof LibraryAssociate) {
        		storeSales += emp.getCurrentSales();
        		totalCommission += ((LibraryAssociate) emp).calculateCommission();
        	} else if (emp instanceof LibraryEmployee) {
        		storeSales += emp.getCurrentSales();
        	}        	
        }
        storeSales += librarian.getCurrentSales();
        librarian.setCurrentLibraryTotals(storeSales);
        totalCommission += librarian.calculateCommission();        
        return totalCommission;
	}
	
	public void addEmployee(AbstractEmployee employee) {
		 staff.add(employee);
	}
	
	public void setLibrarian(Librarian librarian) {
		this.librarian = librarian;
	}
	

}
