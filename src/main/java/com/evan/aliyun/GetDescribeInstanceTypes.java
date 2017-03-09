package com.evan.aliyun;

import java.util.List;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.DescribeInstanceTypesRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeInstanceTypesResponse;
import com.aliyuncs.ecs.model.v20140526.DescribeInstanceTypesResponse.InstanceType;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @Description: 查询【阿里云】的实例资源规格列表
 * @author: zhousp
 * @date:   2016年12月7日 上午11:45:50
 */
public class GetDescribeInstanceTypes {

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
		DescribeInstanceTypesRequest describe = new DescribeInstanceTypesRequest();
		IClientProfile profile = DefaultProfile.getProfile(regionId,accessKeyId, accessKeySecret);
		IAcsClient client = new DefaultAcsClient(profile);
		try {
			DescribeInstanceTypesResponse response = client.getAcsResponse(describe);
			List<InstanceType> instanceTypeList = response.getInstanceTypes();
			
			for(InstanceType instanceType : instanceTypeList){
//				System.out.println("实例规格的ID:"+instanceType.getInstanceTypeId());
////				System.out.println("CPU的内核数目:"+instanceType.getCpuCoreCount());
////				System.out.println("内存大小，单位GB:"+instanceType.getMemorySize());
//				System.out.println("实例规格族:"+instanceType.getInstanceTypeFamily());
//				System.out.println();
				
				System.out.println("'"+instanceType.getInstanceTypeId()+"','"+instanceType.getInstanceTypeFamily()+"'");
			}
			
			
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
}
