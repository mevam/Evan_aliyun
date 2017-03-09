package com.evan.aliyun;

import java.util.List;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.rds.model.v20140815.DescribeTasksRequest;
import com.aliyuncs.rds.model.v20140815.DescribeTasksResponse;
import com.aliyuncs.rds.model.v20140815.DescribeTasksResponse.TaskProgressInfo;

/**
 * @Description: 查询【阿里云】的实例列表
 * @author: zhousp
 * @date:   2016年12月7日 上午11:45:50
 */
public class GetTasks {

	public static void main(String[] args) {

		String regionId = "cn-shenzhen";  //地域
		String accessKeyId = "LTAIeOcBXzHxu6bv";  //accessKeyId
		String accessKeySecret = "UMFQBbUlAwTV4uTLR5FX6LEckZF6Jy";  //accessKeySecret
		getTasks(regionId,accessKeyId,accessKeySecret);
	}

	/**
	 * @param regionId : 地域
	 * @param accessKeyId  ： 访问阿里云的accessKeyId
	 * @param accessKeySecret ： 访问阿里云的accessKeySecret
	 */
	public static void getTasks(String regionId,String accessKeyId,String accessKeySecret) {
		DescribeTasksRequest describe = new DescribeTasksRequest();
		IClientProfile profile = DefaultProfile.getProfile(regionId,accessKeyId, accessKeySecret);
		IAcsClient client = new DefaultAcsClient(profile);
		try {
			
			DescribeTasksResponse response = client.getAcsResponse(describe);
			System.out.println("===response=="+response);
			List<TaskProgressInfo> taskList = response.getItems();
			
			for(TaskProgressInfo task : taskList){
				System.out.println("getTaskId:==="+task.getTaskId());
				System.out.println("getDBName:==="+task.getDBName());
				System.out.println("getProgressInfo:==="+task.getProgressInfo());
				System.out.println("taskId:==="+task.getTaskAction());
			}
			
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			System.out.println("===1=="+e.getErrCode());
			System.out.println("===2=="+e.getErrMsg());
			System.out.println("===3=="+e.getMessage());
			System.out.println("===4=="+e.getErrorType());
			e.printStackTrace();
		}
	}
}
