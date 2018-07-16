package com.linkwee.xoss.zookeeper;

public class ZkClientTest {
	
	public static void main(String[] args) throws Exception {
		CuratorZookeeperClient client = CuratorZookeeperClient.getInstance("120.76.163.28:2181");
		for(String s:client.getChildren("/dd-job")){
			System.out.println("==========JOB============/dd-job/"+s);
			System.out.println(client.getChildren("/dd-job/"+s));
			for(String node:client.getChildren("/dd-job/"+s+"/config")){
				System.out.println("/dd-job/"+s+"/config/"+node);
				System.out.println(client.read("/dd-job/"+s+"/config/"+node));
			}
		}
		//构造一个Job并添加或者修改
		JobSettings job = new JobSettings("modifyOrgFeeRatioJob");
		job.setCron("40 */1 * * * ?");
		job.setDescription("机构佣金修改定时任务");
		
		client.close();
	}
}
