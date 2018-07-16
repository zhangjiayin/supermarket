package com.linkwee.xoss.zookeeper;

import com.dangdang.ddframe.job.internal.storage.JobNodePath;

public final class ConfigurationNode
{
  private static final String ROOT = "config";
  static final String JOB_TYPE = "config/jobType";
  static final String JOB_CLASS = "config/jobClass";
  static final String SHARDING_TOTAL_COUNT = "config/shardingTotalCount";
  static final String CRON = "config/cron";
  static final String SHARDING_ITEM_PARAMETERS = "config/shardingItemParameters";
  static final String JOB_PARAMETER = "config/jobParameter";
  static final String MONITOR_EXECUTION = "config/monitorExecution";
  static final String PROCESS_COUNT_INTERVAL_SECONDS = "config/processCountIntervalSeconds";
  static final String CONCURRENT_DATA_PROCESS_THREAD_COUNT = "config/concurrentDataProcessThreadCount";
  static final String FETCH_DATA_COUNT = "config/fetchDataCount";
  static final String STREAMING_PROCESS = "config/streamingProcess";
  static final String MAX_TIME_DIFF_SECONDS = "config/maxTimeDiffSeconds";
  static final String FAILOVER = "config/failover";
  static final String MISFIRE = "config/misfire";
  static final String JOB_SHARDING_STRATEGY_CLASS = "config/jobShardingStrategyClass";
  static final String DESCRIPTION = "config/description";
  static final String MONITOR_PORT = "config/monitorPort";
  static final String SCRIPT_COMMAND_LINE = "config/scriptCommandLine";
  private final JobNodePath jobNodePath;
  
  public ConfigurationNode(String jobName)
  {
    this.jobNodePath = new JobNodePath(jobName);
  }
  
  public boolean isShardingTotalCountPath(String path)
  {
    return this.jobNodePath.getFullPath("config/shardingTotalCount").equals(path);
  }
  
  public boolean isMonitorExecutionPath(String path)
  {
    return this.jobNodePath.getFullPath("config/monitorExecution").equals(path);
  }
  
  public boolean isFailoverPath(String path)
  {
    return this.jobNodePath.getFullPath("config/failover").equals(path);
  }
  
  public boolean isCronPath(String path)
  {
    return this.jobNodePath.getFullPath("config/cron").equals(path);
  }
}
