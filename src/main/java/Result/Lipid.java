package Result;
import java.io.Serializable;

public class Lipid implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int resultID;
	private int hdlCholesterol;
	private String lipidPanelDetails;
	private int ldlCholesterol;
	
	public Lipid() {}
	
	public int getResultID() {
		return resultID;
	}
	public void setResultID(int resultID) {
		this.resultID = resultID;
	}
	public int getHdlCholesterol() {
		return hdlCholesterol;
	}
	public void setHdlCholesterol(int hdlCholesterol) {
		this.hdlCholesterol = hdlCholesterol;
	}
	public String getLipidPanelDetails() {
		return lipidPanelDetails;
	}
	public void setLipidPanelDetails(String lipidPanelDetails) {
		this.lipidPanelDetails = lipidPanelDetails;
	}
	public int getLdlCholesterol() {
		return ldlCholesterol;
	}
	public void setLdlCholesterol(int ldlCholesterol) {
		this.ldlCholesterol = ldlCholesterol;
	}
}
