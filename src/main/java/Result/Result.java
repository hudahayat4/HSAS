package Result;

import java.sql.Date;
import java.io.Serializable;

public class Result implements Serializable {
	
	private static final long serialVersionUID = 1L;


	private int resultId;
	private int appointmentId;
	private Date resultDate;
	private String resultComment;
	
	//default constructor 
	public Result() {}
	
	//parameterized constructor 
	public Result( int resultId, int appointmentId,
	 Date resultDate, String resultComment) {
		this.setResultId(resultId);
		this.setAppointmentId(appointmentId);
		this.setResultDate(resultDate);
		this.setResultComment(resultComment);
	}

	public int getResultId() {
		return resultId;
	}

	public void setResultId(int resultId) {
		this.resultId = resultId;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Date getResultDate() {
		return resultDate;
	}

	public void setResultDate(Date resultDate) {
		this.resultDate = resultDate;
	}

	public String getResultComment() {
		return resultComment;
	}

	public void setResultComment(String resultComment) {
		this.resultComment = resultComment;
	}
	
}
