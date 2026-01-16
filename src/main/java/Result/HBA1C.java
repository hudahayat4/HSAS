package Result;

import java.io.Serializable;

public class HBA1C extends Result implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String diabetesRiskLevel;
	private int HBa1cTreShold;
	
	public HBA1C() {
		super();
	}

	public String getDiabetesRiskLevel() {
		return diabetesRiskLevel;
	}

	public void setDiabetesRiskLevel(String diabetesRiskLevel) {
		this.diabetesRiskLevel = diabetesRiskLevel;
	}

	public int getHBa1cTreShold() {
		return HBa1cTreShold;
	}

	public void setHBa1cTreShold(int hBa1cTreShold) {
		HBa1cTreShold = hBa1cTreShold;
	}
	
	
	
	

}
