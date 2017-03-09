package com.evan.aliyun;

import java.util.List;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.DescribeInstancesRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeInstancesResponse;
import com.aliyuncs.ecs.model.v20140526.DescribeInstancesResponse.Instance;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @Description: 查询【阿里云】的实例列表
 * @author: zhousp
 * @date:   2016年12月7日 上午11:45:50
 */
public class GetInstances {

	public static void main(String[] args) {

		String regionId = "cn-shenzhen";  //地域
		String accessKeyId = "LTAIeOcBXzHxu6bv";  //accessKeyId
		String accessKeySecret = "UMFQBbUlAwTV4uTLR5FX6LEckZF6Jy";  //accessKeySecret
		getInstance(regionId,accessKeyId,accessKeySecret);
	}

	/**
	 * @param regionId : 地域
	 * @param accessKeyId  ： 访问阿里云的accessKeyId
	 * @param accessKeySecret ： 访问阿里云的accessKeySecret
	 */
	public static void getInstance(String regionId,String accessKeyId,String accessKeySecret) {
		DescribeInstancesRequest describe = new DescribeInstancesRequest();
		IClientProfile profile = DefaultProfile.getProfile(regionId,accessKeyId, accessKeySecret);
		IAcsClient client = new DefaultAcsClient(profile);
		try {
			
			DescribeInstancesResponse response = client.getAcsResponse(describe);
			
			System.out.println("实例总个数:"+response.getTotalCount()); //实例总个数
			List<Instance>  instanceList = response.getInstances();  //返回实例的信息
			
			for(Instance instance : instanceList){
				System.out.println("实例ID:" + instance.getInstanceId());
				System.out.println("实例的显示名称:" + instance.getInstanceName());
				System.out.println("实例的描述:" + instance.getDescription());
				System.out.println("镜像ID:" + instance.getImageId());
				System.out.println("实例所属地域ID:" + instance.getRegionId());
				System.out.println("实例资源规格:" + instance.getInstanceType());
				System.out.println("实例规格族:" + instance.getInstanceTypeFamily());
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
