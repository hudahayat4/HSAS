package appointment;

import java.sql.Date;
import java.sql.Timestamp;

public class appointment {
	private int appointmentId;
	private int packageId;
	private int cusNRIC;
	private int NRIC;
	private Date apptDate;
	private Timestamp apptTime;

	// Default Constructor
	public appointment() {}

	// Parameterized Constructor
	public appointment(int packageId, int cusNRIC, int NRIC, Date apptDate, Timestamp apptTime) {
		this.packageId = packageId;
		this.cusNRIC = cusNRIC;
		this.NRIC = NRIC;
		this.apptDate = apptDate;
		this.apptTime = apptTime;
	}

	// GETTERS AND SETTERS (Required for DAO)
	public int getAppointmentId() { return appointmentId; }
	public void setAppointmentId(int appointmentId) { this.appointmentId = appointmentId; }

	public int getPackageId() { return packageId; }
	public void setPackageId(int packageId) { this.packageId = packageId; }

	public int getCusNRIC() { return cusNRIC; }
	public void setCusNRIC(int cusNRIC) { this.cusNRIC = cusNRIC; }

	public int getNRIC() { return NRIC; }
	public void setNRIC(int NRIC) { this.NRIC = NRIC; }

	public Date getApptDate() { return apptDate; }
	public void setApptDate(Date apptDate) { this.apptDate = apptDate; }

	public Timestamp getApptTime() { return apptTime; }
	public void setApptTime(Timestamp apptTime) { this.apptTime = apptTime; }
}