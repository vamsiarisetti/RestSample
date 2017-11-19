package org.demo.mapping;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ED_PATTERNS")
public class Patterns implements Serializable {

	private long patternId;
	
	//fk from ED_INPUT_DATA.inputDataId;
	//fk from JC_INTERNAL_CONTEXT.internalContextId;
	private InputData inputData;
	//private InternalContext internalCtxt;

	private String patternName;
	private String patternEffectiveStartDt;
	private String patternEffectiveEndDt;
	private String patternStatus;
	public Patterns(InputData inputData, String patternName, String patternEffectiveStartDt,
			String patternEffectiveEndDt, String patternStatus) {
		super();
		this.inputData = inputData;
		this.patternName = patternName;
		this.patternEffectiveStartDt = patternEffectiveStartDt;
		this.patternEffectiveEndDt = patternEffectiveEndDt;
		this.patternStatus = patternStatus;
	}

	@Id
	@Column(name = "patternId")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patterns_seq")
	@SequenceGenerator(name = "patterns_seq", sequenceName = "patterns_patrnId_seq",allocationSize=1)
	public long getPatternId() {
		return patternId;
	}
	public void setPatternId(long patternId) {
		this.patternId = patternId;
	}

	@OneToOne
	@JoinColumn(name = "inputDataId")
	public InputData getInputData() {
		return inputData;
	}
	public void setInputData(InputData inputData) {
		this.inputData = inputData;
	}
	public String getPatternName() {
		return patternName;
	}
	public void setPatternName(String patternName) {
		this.patternName = patternName;
	}
	public String getPatternEffectiveStartDt() {
		return patternEffectiveStartDt;
	}
	public void setPatternEffectiveStartDt(String patternEffectiveStartDt) {
		this.patternEffectiveStartDt = patternEffectiveStartDt;
	}
	public String getPatternEffectiveEndDt() {
		return patternEffectiveEndDt;
	}
	public void setPatternEffectiveEndDt(String patternEffectiveEndDt) {
		this.patternEffectiveEndDt = patternEffectiveEndDt;
	}
	public String getPatternStatus() {
		return patternStatus;
	}
	public void setPatternStatus(String patternStatus) {
		this.patternStatus = patternStatus;
	}
	@Override
	public String toString() {
		return "Patterns [patternId=" + patternId + ", inputData=" + inputData + ", patternName=" + patternName
				+ ", patternEffectiveStartDt=" + patternEffectiveStartDt + ", patternEffectiveEndDt="
				+ patternEffectiveEndDt + ", patternStatus=" + patternStatus + "]";
	}
}