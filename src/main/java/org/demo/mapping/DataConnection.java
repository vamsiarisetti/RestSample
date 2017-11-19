package org.demo.mapping;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ED_DATA_CONNECTION")
public class DataConnection  implements Serializable{


	private long dataConnId;
	private String dataConnUrl;
	private String dataConnProviderName;
	private String dataConnCredentials;
	private int dataConnRetryMax;

	public DataConnection() {
	}

	public DataConnection(String dataConnUrl, String dataConnProviderName, String dataConnCredentials, int dataConnRetryMax) {
		super();
		this.dataConnUrl = dataConnUrl;
		this.dataConnProviderName = dataConnProviderName;
		this.dataConnCredentials = dataConnCredentials;
		this.dataConnRetryMax = dataConnRetryMax;
	}

	@Id
	@Column(name = "dataConnId")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dataConn_seq")
	@SequenceGenerator(name = "dataConn_seq", sequenceName = "dataConn_dataConnId_seq",allocationSize=1)
	public long getDataConnId() {
		return dataConnId;
	}

	public void setDataConnId(long dataConnId) {
		this.dataConnId = dataConnId;
	}

	public String getDataConnUrl() {
		return dataConnUrl;
	}

	public void setDataConnUrl(String dataConnUrl) {
		this.dataConnUrl = dataConnUrl;
	}

	public String getDataConnProviderName() {
		return dataConnProviderName;
	}

	public void setDataConnProviderName(String dataConnProviderName) {
		this.dataConnProviderName = dataConnProviderName;
	}

	public String getDataConnCredentials() {
		return dataConnCredentials;
	}

	public void setDataConnCredentials(String dataConnCredentials) {
		this.dataConnCredentials = dataConnCredentials;
	}

	public int getDataConnRetryMax() {
		return dataConnRetryMax;
	}

	public void setDataConnRetryMax(int dataConnRetryMax) {
		this.dataConnRetryMax = dataConnRetryMax;
	}

	@Override
	public String toString() {
		return "DataConnection [dataConnId=" + dataConnId + ", dataConnUrl=" + dataConnUrl + ", dataConnProviderName="
				+ dataConnProviderName + ", dataConnCredentials=" + dataConnCredentials + ", dataConnRetryMax="
				+ dataConnRetryMax + "]";
	}
}