package com.evan.aliyun;

import java.util.List;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.DescribeInstanceVncUrlRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeInstanceVncUrlResponse;
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
public class GetInstancesVncUrl {

	public static void main(String[] args) {

		String regionId = "cn-shenzhen";  //地域
		String accessKeyId = "LTAIeOcBXzHxu6bv";  //accessKeyId
		String accessKeySecret = "UMFQBbUlAwTV4uTLR5FX6LEckZF6Jy";  //accessKeySecret
		getInstanceVncUrl(regionId,accessKeyId,accessKeySecret);
	}

	/**
	 * @param regionId : 地域
	 * @param accessKeyId  ： 访问阿里云的accessKeyId
	 * @param accessKeySecret ： 访问阿里云的accessKeySecret
	 */
	public static void getInstanceVncUrl(String regionId,String accessKeyId,String accessKeySecret) {
		DescribeInstanceVncUrlRequest describe = new DescribeInstanceVncUrlRequest();
		IClientProfile profile = DefaultProfile.getProfile(regionId,accessKeyId, accessKeySecret);
		IAcsClient client = new DefaultAcsClient(profile);
		try {
			describe.setInstanceId("i-wz91elixq0wf9gvnvtw4");
			DescribeInstanceVncUrlResponse response = client.getAcsResponse(describe);
			System.out.println("===="+response.getVncUrl());
			
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
