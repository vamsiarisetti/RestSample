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
@Table(name = "ED_JOB_STATUS")
public class JobStatus implements Serializable {

	private long detectionJobId;

	// fk from ED_INPUT_DATA.inputDataId
	// private long inputDataId;
	private InputData inputData;

	private String detectionJobType;
	private String detectionJobStatus;
	private long detectionJobProcessId;
	private String detectionJobStartDt;
	private String detectionJobEndDt;
	private String detectionJobMessage;

	public JobStatus(String detectionJobType, String detectionJobStatus,
			long detectionJobProcessId, String detectionJobStartDt, String detectionJobEndDt,
			String detectionJobMessage, InputData inputData) {
		super();
		this.detectionJobType = detectionJobType;
		this.detectionJobStatus = detectionJobStatus;
		this.detectionJobProcessId = detectionJobProcessId;
		this.detectionJobStartDt = detectionJobStartDt;
		this.detectionJobEndDt = detectionJobEndDt;
		this.detectionJobMessage = detectionJobMessage;
		this.inputData = inputData;
	}

	@Id
	@Column(name = "detectionJobId")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobSts_seq")
	@SequenceGenerator(name = "jobSts_seq", sequenceName = "jobsts_detectionId_seq",allocationSize=1)
	public long getDetectionJobId() {
		return detectionJobId;
	}

	public void setDetectionJobId(long detectionJobId) {
		this.detectionJobId = detectionJobId;
	}

	@OneToOne
	@JoinColumn(name = "inputDataId")
	public InputData getInputData() {
		return inputData;
	}

	public void setInputData(InputData inputData) {
		this.inputData = inputData;
	}

	public String getDetectionJobType() {
		return detectionJobType;
	}

	public void setDetectionJobType(String detectionJobType) {
		this.detectionJobType = detectionJobType;
	}

	public String getDetectionJobStatus() {
		return detectionJobStatus;
	}

	public void setDetectionJobStatus(String detectionJobStatus) {
		this.detectionJobStatus = detectionJobStatus;
	}

	public long getDetectionJobProcessId() {
		return detectionJobProcessId;
	}

	public void setDetectionJobProcessId(long detectionJobProcessId) {
		this.detectionJobProcessId = detectionJobProcessId;
	}

	public String getDetectionJobStartDt() {
		return detectionJobStartDt;
	}

	public void setDetectionJobStartDt(String detectionJobStartDt) {
		this.detectionJobStartDt = detectionJobStartDt;
	}

	public String getDetectionJobEndDt() {
		return detectionJobEndDt;
	}

	public void setDetectionJobEndDt(String detectionJobEndDt) {
		this.detectionJobEndDt = detectionJobEndDt;
	}

	public String getDetectionJobMessage() {
		return detectionJobMessage;
	}

	public void setDetectionJobMessage(String detectionJobMessage) {
		this.detectionJobMessage = detectionJobMessage;
	}

	@Override
	public String toString() {
		return "JobStatus [detectionJobId=" + detectionJobId + ", inputData=" + inputData + ", detectionJobType="
				+ detectionJobType + ", detectionJobStatus=" + detectionJobStatus + ", detectionJobProcessId="
				+ detectionJobProcessId + ", detectionJobStartDt=" + detectionJobStartDt + ", detectionJobEndDt="
				+ detectionJobEndDt + ", detectionJobMessage=" + detectionJobMessage + "]";
	}
}