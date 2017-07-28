package com.common.util.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

public class CoordinateConversion {
private static final double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
    
    private static final double pi = 3.14159265358979324;
    private static final double a = 6378245.0;
    private static final double ee = 0.00669342162296594323;
    
    /**
     * gg_lat 纬度
     * gg_lon 经度
     * GCJ-02转换BD-09
     * Google地图经纬度转百度地图经纬度
     * 
     * 国外，google地图坐标即是gps坐标；国内，google地图坐标即是火星坐标
     * 国外，gsp转百度；国内，火星转百度
     * */
    public static LatLngPoint google_bd_encrypt(double gg_lat, double gg_lon){
    	if (outOfChina(gg_lat, gg_lon)) {
    		LatLngPoint point = wgs_gcj_encrypts(gg_lat,gg_lon);//gps转火星
    		return gcj_bd_encrypts(point.getLat(),point.getLng());
        }
        return gcj_bd_encrypts(gg_lat,gg_lon);//火星转百度
    }
    
    /**
     * wgLat 纬度
     * wgLon 经度
     * BD-09转换GCJ-02
     * 百度转google(火星坐标)
     * */
    public static LatLngPoint bd_google_encrypt(double bd_lat, double bd_lon){
    	LatLngPoint point=new LatLngPoint();
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;  
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);  
        double theta =Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);  
        double gg_lon = z * Math.cos(theta);  
        double gg_lat = z * Math.sin(theta);  
        point.setLat(gg_lat);
        point.setLng(gg_lon);
        return point;
    }
    
    
    
    /**
     * wgLat 纬度
     * wgLon 经度
     * WGS-84 到 GCJ-02 的转换（即 GPS 加偏）
     * gps转谷歌(中国：火星，国外：gps)
     * */
    public static LatLngPoint wgs_google_encrypts(double wgLat, double wgLon) {
        if (outOfChina(wgLat, wgLon)) {
        	LatLngPoint point = new LatLngPoint();
            point.setLat(wgLat);
            point.setLng(wgLon);
            return point;
        }
        return wgs_gcj_encrypts(wgLat,wgLon);
    }
    
    /**
     * wgLat 纬度
     * wgLon 经度
     * WGS-84 到 百度坐标 的转换
     * gps转百度
     * */
    public static LatLngPoint wgs_bd_encrypts(double wgLat, double wgLon) {
    	LatLngPoint point = wgs_gcj_encrypts(wgLat, wgLon);
    	return gcj_bd_encrypts(point.getLat(),point.getLng());
    }
    
    
    /**
     * wgLat 纬度
     * wgLon 经度
     * BD-09转换GCJ-02（即 GPS 加偏）
     * 火星转百度
     * */
    public static LatLngPoint gcj_bd_encrypts(double gg_lat, double gg_lon) {
    	 double x = gg_lon, y = gg_lat;
         double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
         double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi); 
         double bd_lon = z * Math.cos(theta) + 0.0065;
         double bd_lat = z * Math.sin(theta) + 0.006;
         LatLngPoint point  =new LatLngPoint();
         point.setLat(bd_lat);
         point.setLng(bd_lon);
         return point;
    }
    /**
     * wgLat 纬度
     * wgLon 经度
     * BD-09转换GCJ-02（即 GPS 加偏）
     * 百度转火星
     * */
    public static LatLngPoint bd_gcj_encrypts(double bd_lat, double bd_lon) {
    	LatLngPoint point=new LatLngPoint();
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;  
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);  
        double theta =Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);  
        double gg_lon = z * Math.cos(theta);  
        double gg_lat = z * Math.sin(theta);  
        point.setLat(gg_lat);
        point.setLng(gg_lon);
        return point;
    }
    /**
     * wgLat 纬度
     * wgLon 经度
     * WGS-84 到 GCJ-02 的转换（即 GPS 加偏）
     * gps转火星
     * */
    public static LatLngPoint wgs_gcj_encrypts(double wgLat, double wgLon) {
    	double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
    	double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
    	double radLat = wgLat / 180.0 * pi;
    	double magic = Math.sin(radLat);
    	magic = 1 - ee * magic * magic;
    	double sqrtMagic = Math.sqrt(magic);
    	dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
    	dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
    	double lat = wgLat + dLat;
    	double lon = wgLon + dLon;
    	LatLngPoint point = new LatLngPoint();
    	point.setLat(lat);
    	point.setLng(lon);
    	return point;
    }
    
    
    public static void transform(double wgLat, double wgLon, double[] latlng) {
        if (outOfChina(wgLat, wgLon)) {
            latlng[0] = wgLat;
            latlng[1] = wgLon;
            return;
        }
        double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
        double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
        double radLat = wgLat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        latlng[0] = wgLat + dLat;
        latlng[1] = wgLon + dLon;
    }

    private static boolean outOfChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347)
            return true;
        if (lat < 0.8293 || lat > 55.8271)
            return true;
        return false;
    }

    private static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    private static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0 * pi)) * 2.0 / 3.0;
        return ret;
    }
    
    public static void main(String[] args){
    	System.out.println("GPS："+31.094544353278795+","+121.44690799650303);
    	
    	LatLngPoint point = wgs_gcj_encrypts(31.09454435327879,121.44690799650303);
    	System.out.println("GPS转谷歌："+point.getLat()+","+point.getLng());
    	
		LatLngPoint point4 = wgs_bd_encrypts(31.094544353278795,121.44690799650303);
		System.out.println("GPS转百度："+point4.getLat()+","+point4.getLng());
		
		LatLngPoint point2 = google_bd_encrypt(point.getLat(),point.getLng());
		System.out.println("谷歌转百度："+point2.getLat()+","+point2.getLng());
		
		LatLngPoint point3 = bd_google_encrypt(point2.getLat(),point2.getLng());
		System.out.println("百度转谷歌："+point3.getLat()+","+point3.getLng());
	}
    
}
