package com.evan.aliyun;

import java.util.List;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.DescribeImagesRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeImagesResponse;
import com.aliyuncs.ecs.model.v20140526.DescribeImagesResponse.Image;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * @Description: 查询【阿里云】的可用镜像
 * @author: zhousp
 * @date:   2016年12月7日 上午11:45:50
 */
public class GetDescribeImages {

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
		DescribeImagesRequest describe = new DescribeImagesRequest();
		
		describe.setPageSize(100);
		IClientProfile profile = DefaultProfile.getProfile(regionId,accessKeyId, accessKeySecret);
		IAcsClient client = new DefaultAcsClient(profile);
		try {
			DescribeImagesResponse response = client.getAcsResponse(describe);
			List<Image> imageList = response.getImages();
			System.out.println("========imageList==="+imageList.size());
			for(Image image : imageList){
				System.out.println("镜像编码:"+image.getImageId());
				System.out.println("操作系统类型:"+image.getOSType());
				System.out.println("操作系统平台:"+image.getPlatform());
				System.out.println("镜像的名称:"+image.getImageName());
				System.out.println("操作系统的显示名称:"+image.getOSName());
				System.out.println("=================================");
				if("centos7u2_64_40G_cloudinit_20160728.raw".equals(image.getImageId())){
					
				}
			}
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
}
