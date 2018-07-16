package com.linkwee.xoss.zookeeper;

import java.io.Serializable;

public final class JobSettings implements Serializable {
	private static final long serialVersionUID = 5500320006716055082L;
	private String jobName;
	private String jobType;
	private String jobClass;
	private int shardingTotalCount;
	private String cron;
	private String shardingItemParameters;
	private String jobParameter;
	private boolean monitorExecution;
	private int processCountIntervalSeconds;
	private int concurrentDataProcessThreadCount;
	private int fetchDataCount;
	private boolean streamingProcess;
	private int maxTimeDiffSeconds;

	private int monitorPort = -1;
	private boolean failover;
	private boolean misfire;
	private String jobShardingStrategyClass;
	private String description;
	private String scriptCommandLine;

	public JobSettings(String jobName) {
		this.jobName = jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public void setJobClass(String jobClass) {
		this.jobClass = jobClass;
	}

	public void setShardingTotalCount(int shardingTotalCount) {
		this.shardingTotalCount = shardingTotalCount;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public void setShardingItemParameters(String shardingItemParameters) {
		this.shardingItemParameters = shardingItemParameters;
	}

	public void setJobParameter(String jobParameter) {
		this.jobParameter = jobParameter;
	}

	public void setMonitorExecution(boolean monitorExecution) {
		this.monitorExecution = monitorExecution;
	}

	public void setProcessCountIntervalSeconds(int processCountIntervalSeconds) {
		this.processCountIntervalSeconds = processCountIntervalSeconds;
	}

	public void setConcurrentDataProcessThreadCount(int concurrentDataProcessThreadCount) {
		this.concurrentDataProcessThreadCount = concurrentDataProcessThreadCount;
	}

	public void setFetchDataCount(int fetchDataCount) {
		this.fetchDataCount = fetchDataCount;
	}

	public void setStreamingProcess(boolean streamingProcess) {
		this.streamingProcess = streamingProcess;
	}

	public void setMaxTimeDiffSeconds(int maxTimeDiffSeconds) {
		this.maxTimeDiffSeconds = maxTimeDiffSeconds;
	}

	public void setMonitorPort(int monitorPort) {
		this.monitorPort = monitorPort;
	}

	public void setFailover(boolean failover) {
		this.failover = failover;
	}

	public void setMisfire(boolean misfire) {
		this.misfire = misfire;
	}

	public void setJobShardingStrategyClass(String jobShardingStrategyClass) {
		this.jobShardingStrategyClass = jobShardingStrategyClass;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setScriptCommandLine(String scriptCommandLine) {
		this.scriptCommandLine = scriptCommandLine;
	}

	public String getJobName() {
		return this.jobName;
	}

	public String getJobType() {
		return this.jobType;
	}

	public String getJobClass() {
		return this.jobClass;
	}

	public int getShardingTotalCount() {
		return this.shardingTotalCount;
	}

	public String getCron() {
		return this.cron;
	}

	public String getShardingItemParameters() {
		return this.shardingItemParameters;
	}

	public String getJobParameter() {
		return this.jobParameter;
	}

	public boolean isMonitorExecution() {
		return this.monitorExecution;
	}

	public int getProcessCountIntervalSeconds() {
		return this.processCountIntervalSeconds;
	}

	public int getConcurrentDataProcessThreadCount() {
		return this.concurrentDataProcessThreadCount;
	}

	public int getFetchDataCount() {
		return this.fetchDataCount;
	}

	public boolean isStreamingProcess() {
		return this.streamingProcess;
	}

	public int getMaxTimeDiffSeconds() {
		return this.maxTimeDiffSeconds;
	}

	public int getMonitorPort() {
		return this.monitorPort;
	}

	public boolean isFailover() {
		return this.failover;
	}

	public boolean isMisfire() {
		return this.misfire;
	}

	public String getJobShardingStrategyClass() {
		return this.jobShardingStrategyClass;
	}

	public String getDescription() {
		return this.description;
	}

	public String getScriptCommandLine() {
		return this.scriptCommandLine;
	}
}
