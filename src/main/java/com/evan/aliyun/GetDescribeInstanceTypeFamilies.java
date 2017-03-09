package com.evan.aliyun;

import java.util.List;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.DescribeInstanceTypeFamiliesRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeInstanceTypeFamiliesResponse;
import com.aliyuncs.ecs.model.v20140526.DescribeInstanceTypeFamiliesResponse.InstanceTypeFamily;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @Description: 查询【阿里云】的实例规格族列表
 * @author: zhousp
 * @date:   2016年12月7日 上午11:45:50
 */
public class GetDescribeInstanceTypeFamilies {

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
		DescribeInstanceTypeFamiliesRequest describe = new DescribeInstanceTypeFamiliesRequest();
		IClientProfile profile = DefaultProfile.getProfile(regionId,accessKeyId, accessKeySecret);
		IAcsClient client = new DefaultAcsClient(profile);
		try {
			DescribeInstanceTypeFamiliesResponse response = client.getAcsResponse(describe);
			List<InstanceTypeFamily> instanceTypeFamilyList = response.getInstanceTypeFamilies();
			for(InstanceTypeFamily instanceTypeFamily : instanceTypeFamilyList){
				
				if("ecs-1".equals(instanceTypeFamily.getGeneration())){
					System.out.println(instanceTypeFamily.getInstanceTypeFamilyId());
				}
				
//				System.out.println("===实例规格族 ID=="+instanceTypeFamily.getInstanceTypeFamilyId());
//				System.out.println("===实例规格族所属代数=="+instanceTypeFamily.getGeneration());
//				System.out.println();
			}
			
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
}
