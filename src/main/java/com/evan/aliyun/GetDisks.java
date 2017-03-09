package com.evan.aliyun;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.DescribeDisksRequest;
import com.aliyuncs.ecs.model.v20140526.DescribeDisksResponse;
import com.aliyuncs.ecs.model.v20140526.DescribeDisksResponse.Disk;
import com.aliyuncs.ecs.model.v20140526.DescribeInstancesResponse.Instance;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class GetDisks {

	public static void main(String[] args) {

		String regionId = "cn-shenzhen";  //地域
		String accessKeyId = "LTAIeOcBXzHxu6bv";  //accessKeyId
		String accessKeySecret = "UMFQBbUlAwTV4uTLR5FX6LEckZF6Jy";  //accessKeySecret
		getDiskByRegion(regionId,accessKeyId,accessKeySecret);
		
	}
	
	/**
	 * 根据地域信息查询其下所有云磁盘信息
	 * @param cloudAcc
	 * @param regionId
	 * @param aliyunDiskList
	 */
	public static void getDiskByRegion(String regionId,String accessKeyId,String accessKeySecret) {
		
		DescribeDisksRequest describe = new DescribeDisksRequest();
		
		String diskid = "[\"d-wz926x7s4nemcsecoxwq\",\"d-wz93bcs38txfdrq1v4qj\"]";
		
		describe.setDiskIds(diskid);
		
		IClientProfile profile = DefaultProfile.getProfile(regionId,accessKeyId, accessKeySecret);
		IAcsClient client = new DefaultAcsClient(profile);
		try {
			
			DescribeDisksResponse response = client.getAcsResponse(describe);
			
			List<Disk>  diskList = response.getDisks();  //返回实例的信息
			System.out.println("===磁盘数量=="+diskList.size());
			
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}

}
