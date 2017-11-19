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
@Table(name = "ED_INPUT_FIELDS")
public class InputFields implements Serializable {
	private long inputFieldId;
	
	//fk from ED_INPUT_DATA.inputDataId
	//private long inputDataId;
	private InputData inputData;

	private String inputDataFieldName;
	private String inputDataFieldType;
	private String inputDataFieldCategory;

	public InputFields(InputData inputData, String inputDataFieldName, String inputDataFieldType,
			String inputDataFieldCategory) {
		super();
		this.inputData = inputData;
		this.inputDataFieldName = inputDataFieldName;
		this.inputDataFieldType = inputDataFieldType;
		this.inputDataFieldCategory = inputDataFieldCategory;
	}

	@Id
	@Column(name = "inputFieldId")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inputFlds_seq")
	@SequenceGenerator(name = "inputFlds_seq", sequenceName = "inputFlds_inputFldId_seq",allocationSize=1)
	public long getInputFieldId() {
		return inputFieldId;
	}
	public void setInputFieldId(long inputFieldId) {
		this.inputFieldId = inputFieldId;
	}

	@OneToOne
	@JoinColumn(name = "inputDataId")
	public InputData getInputData() {
		return inputData;
	}
	public void setInputData(InputData inputData) {
		this.inputData = inputData;
	}
	public String getInputDataFieldName() {
		return inputDataFieldName;
	}
	public void setInputDataFieldName(String inputDataFieldName) {
		this.inputDataFieldName = inputDataFieldName;
	}
	public String getInputDataFieldType() {
		return inputDataFieldType;
	}
	public void setInputDataFieldType(String inputDataFieldType) {
		this.inputDataFieldType = inputDataFieldType;
	}
	public String getInputDataFieldCategory() {
		return inputDataFieldCategory;
	}
	public void setInputDataFieldCategory(String inputDataFieldCategory) {
		this.inputDataFieldCategory = inputDataFieldCategory;
	}
	@Override
	public String toString() {
		return "InputFields [inputFieldId=" + inputFieldId + ", inputData=" + inputData + ", inputDataFieldName="
				+ inputDataFieldName + ", inputDataFieldType=" + inputDataFieldType + ", inputDataFieldCategory="
				+ inputDataFieldCategory + "]";
	}
}