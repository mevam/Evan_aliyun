package com.evan.aliyun;

import java.util.List;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.DescribeImageSharePermissionRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeImageSharePermissionResponse;
import com.aliyuncs.ecs.model.v20140526.DescribeImagesResponse.Image;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @Description: 查询【阿里云】的镜像共享
 * @author: zhousp
 * @date:   2016年12月7日 上午11:45:50
 */
public class GetDescribeImageSharePermission {

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
		DescribeImageSharePermissionRequest describe = new DescribeImageSharePermissionRequest();
		IClientProfile profile = DefaultProfile.getProfile(regionId,accessKeyId, accessKeySecret);
		
		IAcsClient client = new DefaultAcsClient(profile);
		try {
			describe.setImageId("centos7u2_64_40G_cloudinit_20160728.raw");
			DescribeImageSharePermissionResponse response = client.getAcsResponse(describe);
			System.out.println("镜像的 ID:"+response.getImageId());
			System.out.println("镜像所属地域 Id:"+response.getRegionId());
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
}
