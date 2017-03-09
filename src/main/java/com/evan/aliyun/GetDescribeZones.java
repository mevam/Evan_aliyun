package com.evan.aliyun;

import java.util.List;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.DescribeZonesRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeZonesResponse;
import com.aliyuncs.ecs.model.v20140526.DescribeZonesResponse.Zone;
import com.aliyuncs.ecs.model.v20140526.DescribeZonesResponse.Zone.ResourcesInfo;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @Description: 查询可用区
 * @author: zhousp
 * @date:   2016年12月7日 下午6:04:40
 */
public class GetDescribeZones {

	public static void main(String[] args) {
		String regionId = "cn-shenzhen";  //地域
		String accessKeyId = "LTAIeOcBXzHxu6bv";  //accessKeyId
		String accessKeySecret = "UMFQBbUlAwTV4uTLR5FX6LEckZF6Jy";  //accessKeySecret
		getDescribeZones(regionId,accessKeyId,accessKeySecret);
	}

	/**
	 * @param regionId : 地域
	 * @param accessKeyId  ： 访问阿里云的accessKeyId
	 * @param accessKeySecret ： 访问阿里云的accessKeySecret
	 */
	public static void getDescribeZones(String regionId,String accessKeyId,String accessKeySecret) {
		DescribeZonesRequest describe = new DescribeZonesRequest();
		IClientProfile profile = DefaultProfile.getProfile(regionId,accessKeyId, accessKeySecret);
		IAcsClient client = new DefaultAcsClient(profile);
		try {
			DescribeZonesResponse response = client.getAcsResponse(describe);
			
			List<Zone> zoneList = response.getZones();  //获取可用区
			
			for(Zone zone : zoneList){
				System.out.println("可用区ID:" + zone.getZoneId());
				System.out.println("可用区本地语言名:" + zone.getLocalName());
				
				List<ResourcesInfo> resourcesInfoList = zone.getAvailableResources();  //可供创建的具体资源
				for(ResourcesInfo resourcesInfo : resourcesInfoList){
					System.out.println("是否 IO 优化:"+resourcesInfo.getIoOptimized());
					System.out.println("支持的网络类型:"+resourcesInfo.getNetworkTypes());
					System.out.println("支持的实例系列:"+resourcesInfo.getInstanceGenerations());
					System.out.println("支持的实例规格族:"+resourcesInfo.getInstanceTypeFamilies());
					System.out.println("支持创建的系统盘类型组成的数组:"+resourcesInfo.getSystemDiskCategories());
					System.out.println("支持创建的数据盘类型组成的数组:"+resourcesInfo.getDataDiskCategories());
				}
				
				System.out.println("====================================");
			} 
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
}
