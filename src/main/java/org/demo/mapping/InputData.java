package org.demo.mapping;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
@Table(name = "ED_INPUT_DATA")
public class InputData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// pk
	private long inputDataId;

	// fk from ED_DATA_CONNECTION.dataConnId
	//private long inputDataConnId;
	private DataConnection dataConnection;
	
	// fk from ED_OUTPUT_DATA.outputDataId
	//private long outputDataId;
	private OutputData outputData;

	private String inputDataDisplayName;
	private String inputDataSql;

	@Id
	@Column(name = "inputDataId")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inputdata_seq")
	@SequenceGenerator(name = "inputdata_seq", sequenceName = "inputdata_inputDataId_seq",allocationSize=1)
	public long getInputDataId() {
		return inputDataId;
	}
	public void setInputDataId(long inputDataId) {
		this.inputDataId = inputDataId;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dataConnId")
	public DataConnection getDataConnection() {
		return dataConnection;
	}
	public void setDataConnection(DataConnection dataConnection) {
		this.dataConnection = dataConnection;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "outputDataId")
	public OutputData getDataOutput() {
		return outputData;
	}
	public void setDataOutput(OutputData outputData) {
		this.outputData = outputData;
	}
	public String getInputDataDisplayName() {
		return inputDataDisplayName;
	}
	public void setInputDataDisplayName(String inputDataDisplayName) {
		this.inputDataDisplayName = inputDataDisplayName;
	}
	public String getInputDataSql() {
		return inputDataSql;
	}
	public void setInputDataSql(String inputDataSql) {
		this.inputDataSql = inputDataSql;
	}
	@Override
	public String toString() {
		return "InputData [inputDataId=" + inputDataId + ", dataConnection=" + dataConnection + ", outputData="
				+ outputData + ", inputDataDisplayName=" + inputDataDisplayName + ", inputDataSql=" + inputDataSql
				+ "]";
	}
}