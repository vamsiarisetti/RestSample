package org.demo.mapping;

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
@Table(name = "ED_OUTPUT_DATA")
public class OutputData {

	// pk
	private long outputDataId;

	// fk from ED_DATA_CONNECTION.dataConnId
	//private long outputDataConnId;
	private DataConnection dataConnection;

	private String outputDataDisplayName;
	private String outputDataTableName;

	public OutputData() {
	}

	public OutputData(String outputDataDisplayName, String outputDataTableName, DataConnection dataConnection) {
		super();
		this.outputDataDisplayName = outputDataDisplayName;
		this.outputDataTableName = outputDataTableName;
		this.dataConnection = dataConnection;
	}

	@Id
	@Column(name = "outputDataId")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "outputdata_seq")
	@SequenceGenerator(name = "outputdata_seq", sequenceName = "outputdata_outputdataId_seq",allocationSize=1)
	public long getOutputDataId() {
		return outputDataId;
	}

	public void setOutputDataId(long outputDataId) {
		this.outputDataId = outputDataId;
	}

	@OneToOne
	@JoinColumn(name = "dataConnId")
	public DataConnection getDataConnection() {
		return dataConnection;
	}

	public void setDataConnection(DataConnection dataConnection) {
		this.dataConnection = dataConnection;
	}

	public String getOutputDataDisplayName() {
		return outputDataDisplayName;
	}

	public void setOutputDataDisplayName(String outputDataDisplayName) {
		this.outputDataDisplayName = outputDataDisplayName;
	}

	public String getOutputDataTableName() {
		return outputDataTableName;
	}

	public void setOutputDataTableName(String outputDataTableName) {
		this.outputDataTableName = outputDataTableName;
	}

	@Override
	public String toString() {
		return "OutputData [outputDataId=" + outputDataId + ", dataConnection=" + dataConnection
				+ ", outputDataDisplayName=" + outputDataDisplayName + ", outputDataTableName=" + outputDataTableName
				+ "]";
	}
}