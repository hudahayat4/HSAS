package appointment;

import java.sql.Date;
import java.sql.Timestamp;

public class appointment {
	private int appointmentID;
	private int packageID;
	private int customerID;
	private int staffID;
	private Date apptDate;
	private Timestamp apptTime;

	// Default Constructor
	public appointment() {}

	// Parameterized Constructor
	public appointment(int packageID, int customerID, int staffID, Date apptDate, Timestamp apptTime) {
		this.packageID = packageID;
		this.setCustomerID(customerID);
		this.setStaffID(staffID);
		this.apptDate = apptDate;
		this.apptTime = apptTime;
	}

	// GETTERS AND SETTERS (Required for DAO)
	public int getAppointmentID() { return appointmentID; }
	public void setAppointmentID(int appointmentID) { this.appointmentID = appointmentID; }

	public int getPackageID() { return packageID; }
	public void setPackageID(int packageID) { this.packageID = packageID; }

	public Date getApptDate() { return apptDate; }
	public void setApptDate(Date apptDate) { this.apptDate = apptDate; }

	public Timestamp getApptTime() { return apptTime; }
	public void setApptTime(Timestamp apptTime) { this.apptTime = apptTime; }

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getStaffID() {
		return staffID;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}
}