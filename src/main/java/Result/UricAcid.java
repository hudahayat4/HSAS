package Result;

import java.io.Serializable;

public class UricAcid implements Serializable {
	private static final long serialVersionUID = 1L;
	private int resultID;
	private String riskIndicator;
	private String UricLevelRange;
	
	public int getResultID() {
		return resultID;
	}
	public void setResultID(int resultID) {
		this.resultID = resultID;
	}
	public String getRiskIndicator() {
		return riskIndicator;
	}
	public void setRiskIndicator(String riskIndicator) {
		this.riskIndicator = riskIndicator;
	}
	public String getUricLevelRange() {
		return UricLevelRange;
	}
	public void setUricLevelRange(String uricLevelRange) {
		UricLevelRange = uricLevelRange;
	}
	
}
