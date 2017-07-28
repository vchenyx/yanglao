package com.socket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.common.util.JSONUtil;
import com.common.util.global.BufferDataInit;
import com.common.util.global.DefalutRedisKey;
import com.common.util.map.CoordinateConversion;
import com.common.util.map.LatLngPoint;
import com.jinkun.main.beans.AAlarmInfo;
import com.jinkun.main.beans.LLocationTrackInfo;

public class GpsAioReadHandler implements CompletionHandler<Integer, ByteBuffer>{
	
	private static final Logger log = LoggerFactory.getLogger(GpsAioReadHandler.class);
	
	//用于读取半包消息和发送应答  
    private AsynchronousSocketChannel channel;  
    private int bufSize;
    public GpsAioReadHandler(AsynchronousSocketChannel channel,int bufSize) {  
            this.channel = channel;  
            this.bufSize = bufSize;
    }  
    
	SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//打印接收的数据
	private void printReciviveBuf(byte[] byt){
		String msg="";
		for(int i=0;i<byt.length;i++){
			msg += (byt[i]&0xff)<16?("0"+Integer.toHexString(byt[i]&0xff).toUpperCase()):Integer.toHexString(byt[i]&0xff).toUpperCase();
			msg += " ";
		}
		log.info("receive_data:"+msg);
	}
	//接收的数据转义
	private byte[] recevieTransform(byte[] buf,int len){
		int count = 0;
		// 转义字符 碰见7D01 转化为7E
		byte[] TempData = new byte[len];
		for (int i = 0; i < len;) {
			if ((0x7D == buf[i]) && (0x02 == buf[i + 1])) {
				TempData[count++] = 0x7E;
				i += 0x02;
			} else if ((0x7D == buf[i]) && (0x01 == buf[i + 1])) {
				TempData[count++] = 0x7D;
				i += 0x02;
			} else if ((0x7D == buf[i]) && (0x03 == buf[i + 1])) {
				TempData[count++] = 0x1A;
				i += 0x02;
			} else if ((0x7D == buf[i]) && (0x04 == buf[i + 1])) {
				TempData[count++] = 0x1B;
				i += 0x02;
			} else {
				TempData[count++] = buf[i++];
			}
		}
		return Arrays.copyOfRange(TempData, 0, count);
	}
	//转义后的数据显示十六进制
	private String[] bytesToHexStr(byte[] byt){
		String[] message = new String[byt.length];
		String msg="";
		int i=0;
		while(i < byt.length){
			if(i < 14 || i >= byt.length-2){
				message[i] = (byt[i]&0xff)<16?("0"+Integer.toHexString(byt[i]&0xff).toUpperCase()):Integer.toHexString(byt[i]&0xff).toUpperCase();
				msg += message[i]+" ";
				i++;
			}else{
				String messageLen = message[3] + message[4];
				Integer messageLe = Integer.valueOf(messageLen, 16);
				message[i] = new String(byt, 14, messageLe - 1);
				msg += message[i]+" ";
				i = byt.length-2;
			}
		}
		log.info("transform_data:"+msg);
		return message;
	}
	//信息验证
	private boolean checkMessage(byte[] TempData,String[] message){
		try{
			String messageId = message[1] + message[2];
			if("4F04".equalsIgnoreCase(messageId)){
				log.info("messageId:"+messageId);
				return false;
			}else{
				//向客户端回消息  
				byte[] sendData = sendData(TempData);
				doWrite(sendData);  
			}
			//消息长度
			String messageLen = message[3] + message[4];
			Integer messageLe = Integer.valueOf(messageLen, 16);
			if(messageLe.intValue() <= 0){
				log.info("messageLe:"+messageLe);
				return false;
			}
			//5~10是mac地址
			String macId = message[5]+":"+message[6]+":"+message[7]+":"+message[8]+":"+message[9]+":"+message[10];
			log.info("macId:"+macId);
			//11+12是消息流水号
			// 消息流水号
//			String serialNumber = message[11] + message[12];
			//标识符
			String sId = message[13];
			log.info("sId:"+sId);
			if("04".equals(sId)){
				//取消报警，直接修改缓存和数据库, 不再向下执行
				return false;
			}else if("02".equals(sId)){
				return false;
			}else if("03".equals(sId)){
				return false;
			}
			// 截取数据场信息0~255
			// 分隔字符串
			String[] infoArray = message[14].split(",");
			// 过滤掉无效信息
			if (infoArray.length < 12) {
				log.info("info_array.length:"+infoArray.length);
				return false;
			}
			// 校验位
//			String check = Integer.toHexString(TempData[len - 2]).toUpperCase();
			//针尾
			String backshank = Integer.toHexString(TempData[TempData.length - 1]).toUpperCase();
			if (!"7E".equalsIgnoreCase(backshank)) {
				log.info("backshank："+backshank);
				return false; 
			}
			return true;
		}catch (Exception e) {
			return false;
		}
	}
    //读取到消息后的处理  
	@SuppressWarnings("deprecation")
	@Override
	public void completed(Integer result, ByteBuffer echoBuffer) {
		 //flip操作  
        echoBuffer.flip();  
        byte[] buf = new byte[echoBuffer.remaining()];  
        echoBuffer.get(buf); 
        byte[] TempData = null; 
        String[] message = null;
        try {
//			System.out.println("远程地址：" + channel.getRemoteAddress());
        	//打印接收的数据
        	printReciviveBuf(buf);
        	//接收的包进行转义
        	TempData = recevieTransform(buf,buf.length);
        	//信息显示
        	message = bytesToHexStr(TempData);
        	
        	//信息验证
        	boolean flag = checkMessage(TempData,message);
        	
        	if(!flag){
        		return;
        	}
        	
        	//设备编号mac
        	String macId = message[5]+":"+message[6]+":"+message[7]+":"+message[8]+":"+message[9]+":"+message[10];
			
        	//00定位，01紧急求助
        	String sId = message[13];
        	
        	Integer isAlarm = 0;
        	if ("04".equals(sId)) {
        		isAlarm = 2;
        	}
        	if ("01".equals(sId)) {
        		isAlarm = 1;
        	}
        	
        	String[] infoArray = message[14].split(",");
			
			Integer positionMode = 0;
			// 解析定位模式
			if (infoArray[0].contains("GN")) {
				positionMode = 0;
			} else if (infoArray[0].contains("GP")) {
				positionMode = 1;
			} else if (infoArray[0].contains("BD")) {
				positionMode = 2;
			}
			
			// 定位时间 hhmmss.sss
//			String locationTime = message_array[1];
			
			// 有效标志
			if(infoArray[2].contains("A")){
				String valFlag = "室外";
				// 纬度 ddmm.mmmmmm
				String latitude = infoArray[3];
				String latitudeDegree = latitude.substring(0, 2);
				String latitudeFraction = latitude.substring(2);
				Double latitude_degree = Double.parseDouble(latitudeDegree);
				Double latitude_fraction = Double.parseDouble(latitudeFraction);
				// 北纬指示 N-北纬 S-南纬
//				String latitudeFlag = message_array[4];
				// 经度 dddmm.mmmmmm
				String longitude = infoArray[5];
				String longitudeDegree = longitude.substring(0, 3);
				String longitudeFraction = longitude.substring(3);
				Double longitude_degree = Double.parseDouble(longitudeDegree);
				Double longitude_fraction = Double.parseDouble(longitudeFraction);
				//GPS 纬度
				Double lat = latitude_degree + latitude_fraction / 60;
				//GPS 经度
				Double lng =  longitude_degree + longitude_fraction / 60;
				//百度地图使用坐标
				LatLngPoint bd_point = CoordinateConversion.wgs_bd_encrypts(lat, lng);
				//谷歌地图使用坐标
				LatLngPoint gg_point = CoordinateConversion.wgs_google_encrypts(lat, lng);
				// 东经指示 E-东经 W-西经
//				String longitudeFlag = message_array[6];
				// 地面速率
//				String speed = message_array[7];
				// 地面航向
//				String orientation = message_array[8];
				// 磁偏角 固定为空
				// String declinate = message_array[10];
				// 磁偏角方向 固定为E
//				String declinateOrientation = message_array[11];
				// 日期 ddmmyy
				// String locationDate = message_array[12];
				// 包尾
//				String frameTail = Integer.toHexString(buf[270]).toUpperCase() + "";
				
				//位置信息存到缓存和定位轨迹中
				
				//调用存储方法BufferDataInit.outdoorPositionInfoService.saveOrUpdatePassportPositionInfo(ppi);
				
				//储存历史轨迹
				LLocationTrackInfo track = new LLocationTrackInfo();
				track.setDeviceId(macId);
				track.setLatGps(lat);
				track.setLonGps(lng);
				track.setLatBD(bd_point.getLat());
				track.setLonBD(bd_point.getLng());
				track.setLatGG(gg_point.getLat());
				track.setLonGG(gg_point.getLng());
				track.setPosiTime(new Date().getTime());
				track.setPosiMode(positionMode);
				track.setInOutDoor(0);
				track.setIsAlarm(isAlarm);
				track.setIsValid(1);
				//把最新的数据存储到缓存中
				BufferDataInit.redisService.addToHash(DefalutRedisKey.peoplePositionKey, macId, JSONUtil.beanToStr(track));
				//在数据库中保存轨迹信息
				BufferDataInit.locationService.saveLocationTrackAsync(track);
				SOSNew(sId, track);// 调用报警解析方法
			}else{
				String valFlag = "室内";
				String hash = BufferDataInit.redisService.getHash(DefalutRedisKey.peoplePositionKey, macId);
				if (hash != null && !hash.equals("")) { //不是第一次上传信息 , 修改为室内, 更新时间
					LLocationTrackInfo strToBean = JSONUtil.strToBean(hash, LLocationTrackInfo.class);
					strToBean.setInOutDoor(1);
					strToBean.setIsValid(0);
					strToBean.setIsAlarm(isAlarm);
					strToBean.setPosiTime(new Date().getTime());
					SOSNew(sId, strToBean);
					BufferDataInit.redisService.addToHash(DefalutRedisKey.peoplePositionKey, macId, JSONUtil.beanToStr(strToBean));
				} else {// 第一次在室内 不用管
					
				}
			}
			
      	}catch (Exception e) {
        	log.info(e.getMessage()); 
		}finally{
			TempData = null;
			buf = null;
		}
	}

	@Override
	public void failed(Throwable exc, ByteBuffer attachment) {
		try {  
            this.channel.close();  
        } catch (IOException e) { 
        	 log.info(e.getMessage());  
        }  
	}
	
	
	/*
	 * 向客户端返回数据
	 */
	private byte[] sendData(byte[] TempData) {
		// 详细请参照GPS协议
		byte[] byt = new byte[18];
		byte[] byt_new = new byte[byt.length * 2];
		try {
			byte acheck = 0x00;
			for (int m = 0; m < 13; m++) {
				if (m == 1) {
					byt[m] = 0x5F;
					continue;
				}
				if (m == 2) {
					byt[m] = 0x02;
					continue;
				}
				if (m == 4) {
					byt[m] = 0x03;
					continue;
				}
				byt[m] = TempData[m];
			}
			byt[13] = TempData[11];
			byt[14] = TempData[12];
			byt[15] = 0x01;
			for (int i = 0x00; i < byt.length - 3; i++)
				acheck ^= byt[i + 1];
			byt[16] = acheck;
			byt[17] = 0x7E;
			// 先转译再发送 去掉帧头帧尾 碰见第一个0x7E改成0x7D 0x01 第二个改成0x7D 0x02
			// 依次类推
			int n = 0;
			int i = 0;
			byt_new[n] = byt[i];
			for (i = 1; i < byt.length - 1; i++) {
				if (byt[i] == 0x7E) {
					byt_new[++n] = 0x7D;
					byt_new[++n] = 0x02;
				} else if (byt[i] == 0x7D) {
					byt_new[++n] = 0x7D;
					byt_new[++n] = 0x01;
				} else if (byt[i] == 0x1A) {
					byt_new[++n] = 0x7D;
					byt_new[++n] = 0x03;
				} else if (byt[i] == 0x1B) {
					byt_new[++n] = 0x7D;
					byt_new[++n] = 0x04;
				} else {
					byt_new[++n] = byt[i];
				}
			}
			byt_new[++n] = byt[i];
		} catch (Exception e) {
			log.info(e.getMessage());
		} 
		return byt_new;
	}
	
	
	/*
	 * 向客户端发送消息
	 */
	public byte[] sendMessage(byte[] TempData,String message) {
		//详细请参照GPS协议
		try {
			byte[] mess_byt = message.getBytes("GBK");
			byte[] new_byt = new byte[15+mess_byt.length];
			new_byt[0] = 0x7E;
			new_byt[1] = 0x5F;
			new_byt[2] = 0x04;
			new_byt[3] = 0x00;
			new_byt[4] = Integer.valueOf(mess_byt.length).byteValue();
			new_byt[5] = TempData[5];
			new_byt[6] = TempData[6];
			new_byt[7] = TempData[7];
			new_byt[8] = TempData[8];
			new_byt[9] = TempData[9];
			new_byt[10] = TempData[10];
			
			new_byt[11] = TempData[11];
			new_byt[12] = TempData[12];
			
			int i=0;
			for(i=0;i<mess_byt.length;i++){
				new_byt[i+13] = mess_byt[i];
			}
			byte acheck = new_byt[1];
			for (int k = 1; k < new_byt.length - 3; k++)
				acheck ^= new_byt[k + 1];
			new_byt[i+13] = acheck;
			new_byt[i+14] = 0x7E;
			return Arrays.copyOfRange(new_byt, 0, 15+mess_byt.length);
		} catch (Exception e) {
			log.info(e.getMessage());
		} 
		return null;
	}
	// 解析报警方法
	@SuppressWarnings("unchecked")
	private void SOSNew(String sId, LLocationTrackInfo track) {
		String macId = track.getDeviceId();
		String alarm = BufferDataInit.redisService.getHash(DefalutRedisKey.peopleAlarmKey, macId);
		try{
			if("00".equals(sId))return;
			// 00 01 02 03 参照gps协议
			if("04".equals(sId)){//取消报警
				if (alarm != null && !alarm.equals("")) { // 取消报警,删除缓存数据,完善数据库报警信息
					BufferDataInit.redisService.delToHash(DefalutRedisKey.peopleAlarmKey, macId);
					BufferDataInit.locationService.updateAlarmInfo(track);
				} // 否则不用管
			} else { // 报警
				if (alarm != null && !alarm.equals("")) { // 之前报过警, 更新位置信息
					AAlarmInfo strToBean = JSONUtil.strToBean(alarm, AAlarmInfo.class);
					strToBean.setLat(track.getLatGG());
					strToBean.setLng(track.getLonGG());
					strToBean.setLatGps(track.getLatGps());
					strToBean.setLngGps(track.getLonGps());
					BufferDataInit.redisService.addToHash(DefalutRedisKey.peopleAlarmKey, macId, JSONUtil.beanToStr(strToBean));
					
				} else { // 之前未报警, 添加至缓存,更新到数据库
					AAlarmInfo saveAlarmInfo = BufferDataInit.locationService.saveAlarmInfo(track);
					BufferDataInit.redisService.addToHash(DefalutRedisKey.peopleAlarmKey, macId, JSONUtil.beanToStr(saveAlarmInfo));
				}
			}
			
		}catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	 //发送消息  
    public void doWrite(byte[] bytes) {  
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);  
        writeBuffer.put(bytes);  
        writeBuffer.flip();  
        //异步写数据 参数与前面的read一样  
        channel.write(writeBuffer, writeBuffer,new CompletionHandler<Integer, ByteBuffer>() {  
            @Override  
            public void completed(Integer result, ByteBuffer buffer) {  
                //如果没有发送完，就继续发送直到完成  
                if (buffer.hasRemaining()) {
                	channel.write(buffer, buffer, this);  
                }else{  
                    //创建新的Buffer  
                    ByteBuffer readBuffer = ByteBuffer.allocate(bufSize);  
                    //异步读  第三个参数为接收消息回调的业务Handler  
                    channel.read(readBuffer, readBuffer, new GpsAioReadHandler(channel,bufSize));  
                }  
            }  
            @Override  
            public void failed(Throwable exc, ByteBuffer attachment) {  
                try {  
                    channel.close();  
                } catch (IOException e) {
                	log.info(e.getMessage());
                }  
            }  
        });  
    }  

}
