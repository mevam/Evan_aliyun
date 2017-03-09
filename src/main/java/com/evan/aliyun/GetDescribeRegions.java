package com.evan.aliyun;

import java.util.List;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.DescribeRegionsRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeRegionsResponse;
import com.aliyuncs.ecs.model.v20140526.DescribeRegionsResponse.Region;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @Description: 查询可用地域列表
 * @author: zhousp
 * @date:   2016年12月7日 下午5:50:38
 */
public class GetDescribeRegions {

	public static void main(String[] args) {
		String regionId = "cn-shenzhen";  //地域
		String accessKeyId = "LTAIeOcBXzHxu6bv";  //accessKeyId
		String accessKeySecret = "UMFQBbUlAwTV4uTLR5FX6LEckZF6Jy";  //accessKeySecret
		getDescribeRegions(regionId,accessKeyId,accessKeySecret);
	}

	/**
	 * @param regionId : 地域
	 * @param accessKeyId  ： 访问阿里云的accessKeyId
	 * @param accessKeySecret ： 访问阿里云的accessKeySecret
	 */
	public static void getDescribeRegions(String regionId,String accessKeyId,String accessKeySecret) {
		DescribeRegionsRequest describe = new DescribeRegionsRequest();
		IClientProfile profile = DefaultProfile.getProfile(regionId,accessKeyId, accessKeySecret);
		IAcsClient client = new DefaultAcsClient(profile);
		try {
			DescribeRegionsResponse response = client.getAcsResponse(describe);
			
			List<Region> regionList = response.getRegions();  //返回实例的信息
			
			for(Region region : regionList){
				System.out.println("Region ID:" + region.getRegionId());
				System.out.println("Region名称:" + region.getLocalName());
				System.out.println("====================================");
			} 
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
}
