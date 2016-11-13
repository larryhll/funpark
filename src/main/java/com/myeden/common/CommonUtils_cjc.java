package com.myeden.common;



import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;





/**
 * 公共工具类
 * 
 * @author germmy
 * 
 */
public class CommonUtils_cjc {
	
	/**
	 * 服务http地址
	 */
	private static String BASE_URI = "http://yunpian.com";
	/**
	 * 服务版本号
	 */
	private static String VERSION = "v1";
	/**
	 * 编码格式
	 */
	private static String ENCODING = "UTF-8";
	/**
	 * 查账户信息的http地址
	 */
	private static String URI_GET_USER_INFO = BASE_URI + "/" + VERSION + "/user/get.json";
	/**
	 * 通用发送接口的http地址
	 */
	private static String URI_SEND_SMS = BASE_URI + "/" + VERSION + "/sms/send.json";
	/**
	 * 模板发送接口的http地址
	 */
	private static String URI_TPL_SEND_SMS = BASE_URI + "/" + VERSION + "/sms/tpl_send.json";
	
	
	private static Log logger= LogFactory.getLog(CommonUtils_cjc.class);
	
	
	//  cookie 
	public void addToCookie(HttpServletResponse resposne, Cookie cookie){
		
		resposne.addCookie(cookie);
		
	}
	
	public static Cookie[] getAllCookies(HttpServletRequest request){
		return request.getCookies();
	}
	
	public static String getCookieValue(Cookie[] cookies, String names){
		String origiAuth=null;
		for(Cookie cookie: cookies){
			String name=cookie.getName();
			String value=cookie.getValue();
			if(name.equals(names)){
				origiAuth=value;
				return origiAuth;
			}	
		}
		
		return origiAuth;
	}
	
	
	public static String parseUserMobile(String data){
		
		String[] aa=data.split("\\|");
		if(aa.length>0){
			return aa[0];
		}
		
		return null;
	}
	
/*	public static String encoder64(String data){
		
		Base64Encoder encoder=new Base64Encoder();
		String auth=encoder.encode(data);
		String auth2=encoder.encode(auth+"&((iopc(");
		String auth3=encoder.encode(auth2+"$v");
		return data+"|"+auth3;
	}*/
	
	
	 public static void Copy(String oldPath, String newPath)    
	   {    
	          try     {    
	                  int     bytesum     =     0;    
	                  int     byteread     =     0;    
	                  File     oldfile     =     new     File(oldPath);    
	                  if     (oldfile.exists())     {      
	                          InputStream     inStream     =     new     FileInputStream(oldPath);     
	                          FileOutputStream     fs     =     new     FileOutputStream(newPath);    
	                          byte[]     buffer     =     new     byte[1444];    
	                          int     length;    
	                          while     (     (byteread     =     inStream.read(buffer))     !=     -1)     {    
	                                  bytesum     +=     byteread;        
	                                  //System.out.println(bytesum);    
	                                  fs.write(buffer,     0,     byteread);    
	                          }    
	                          inStream.close();    
	                  }    
	          }    
	          catch     (Exception     e)     {    
	                  System.out.println( "copy picture have error ! ");    
	                  e.printStackTrace();    
	          }    
	    }     
	
	
/*
	public static void convertToSmallPicture(String sourceFile, String smallFile) throws Exception{
		InputStream stream=new FileInputStream(sourceFile);
		BufferedImage bi=ImageIO.read(stream);
		BufferedImage thumbnail=Scalr.resize(bi,  Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT, 200, 200, Scalr.OP_ANTIALIAS);
		FileOutputStream outputStream = new FileOutputStream(smallFile);
		ImageIO.write(thumbnail, "jpg", outputStream);
		outputStream.flush();
		outputStream.close();
	}*/
	
	/** 常用日期格式_精确型:yyyy-MM-dd HH:mm:ss */
	public static final String FORMAT_PRECISION = "yyyy-MM-dd HH:mm:ss";
	
	
	/**
	 * 根据传入的字符串解密成  图片，声音
	 * author: Felix Han
	 * @param
	 * @return
	 * @throws Exception
	 * 
	 * 
	 */
	 /*public static boolean generateImg(String imgStr, String imgStoreFullPath) throws IOException {

	        if (imgStr == null) {
	            return false;
	        }
	        BASE64Decoder decoder=new BASE64Decoder();
	        byte[] b = decoder.decodeBuffer(imgStr);
	        for (int i = 0; i < b.length; i++) {
	            if (b[i] < 0) {
	                b[i]+=256;
	            }
	        }
	        //String imgFilePath = "C:\\222.jpg";
	        OutputStream out = new FileOutputStream(imgStoreFullPath);
	        out.write(b);
	        out.flush();
	        out.close();
	        return true;
	    }*/
	 
	 
	 
	/**
	 * 根据传入的httpServletRequest得到输入流，然后得到字符串
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * 
	 * 
	 */
	public static String parseJsonByRequest(HttpServletRequest request)
			throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				(ServletInputStream) request.getInputStream(), "UTF-8"));
		String line = null;
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		// System.out.println("接收到的串为:"+sb.toString());
		return sb.toString();
	}

	@SuppressWarnings("unused")
	public static String parseJsonWithByteByRequest(HttpServletRequest request)
			throws Exception {
		InputStream inputStream = request.getInputStream();
		final byte[] buffer = new byte[1024];
		final StringBuilder out = new StringBuilder();
		final Reader in = new InputStreamReader(inputStream, "UTF-8");

		inputStream.read(buffer);

		return out.toString();
	}

	public static String parseJsonWithParameterByRequest(
			HttpServletRequest request) throws Exception {
		String paraJson = request.getParameter("paraJson");
		return paraJson;
	}

	public static String parseJsonWithToStringByRequest(
			HttpServletRequest request) throws Exception {
		String result = request.toString();
		System.out.println("接收的request为：" + result);
		return result;
	}

	public static String parseJsonWithTZhiYueByRequest(
			HttpServletRequest request) throws Exception {
		String result = null;
		StringBuffer info = new StringBuffer();
		InputStream in = request.getInputStream();
		BufferedInputStream buf = new BufferedInputStream(in);
		byte[] buffer = new byte[1024];
		int iRead;
		while ((iRead = buf.read(buffer)) != -1)// 这段话要好好好理解下。
		{
			info.append(new String(buffer, 0, iRead, "UTF-8"));
		}
		
		result = info.toString();
		return result;
	}

	/**
	 * 判断一个字符串是否为空串
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static boolean isEmpty(String str){
		return null == str ? true : "".equalsIgnoreCase(str);
	}

	/**
	 * 传进来一个java.sql类型的日期，转化成字符串
	 * 
	 * @param date
	 * @throws Exception
	 */
	public static String convertDateToStr(Date date, String format)
			throws Exception {
		if (null != date) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			return simpleDateFormat.format(date);
		}
		return "";
	}
	
	/**
	 * 传进来一个字符串,将它转化成Date
	 * 
	 * @param
	 * @throws Exception
	 */
	public static java.util.Date parseStrToDate(String dateStr, String format)
			throws Exception {
		if (null != dateStr) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			return simpleDateFormat.parse(dateStr);
		}
		return null;
	}

	
	
	
	
	
	/**
	 * 传进来一个java.sql类型的日期，转化成字符串
	 * 
	 * @param date
	 * @throws Exception
	 */
	public static String convertDateToStr(java.util.Date date, String format)
			throws Exception {
		if (null != date) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
			return simpleDateFormat.format(date);
		}
		return "";
	}


	public static String getFilePath() throws Exception {
		String currentClassPath = CommonUtils_cjc.class.getResource("/")
				.getPath();
		String filePath = currentClassPath + "/application.properties";
		return filePath;
	}

	/**
	 * 获取应用的根路径，比如http://www.wandu.com/chebaobao/
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getImgePrefix(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
	}

	private static final double EARTH_RADIUS = 6378137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 传入经纬度计算距离，单位为km,保留2位小数
	 * 
	 * @param lng1
	 * @param lat1
	 * @param lng2
	 * @param lat2
	 * @return
	 */
	public static String calDistance(float lng1, float lat1, float lng2,
			float lat2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS / 1000;
		return String.format("%.2f", s);
	}

	/**
	 * 截取列表
	 * 
	 * @param list
	 * @param skip
	 * @param pageSize
	 * @return
	 */
	public static <T> List<T> getSubListPage(List<T> list, int skip,
			int pageSize) {
		if (list == null || list.isEmpty()) {
			return null;
		}
		int startIndex = (skip - 1) * pageSize;
		int endIndex = skip * pageSize;
		if (startIndex > endIndex || startIndex > list.size()) {
			return null;
		}
		if (endIndex > list.size()) {
			endIndex = list.size();
		}
		return list.subList(startIndex, endIndex);
	}

	private static double degrees(double d) {
		return d * (180 / Math.PI);
	}

	/**
	 * 获取四个顶点的list
	 * 
	 * @param lgt
	 * @param lat
	 * @param distance
	 * @return
	 */
	/*public static List<LatlgtPoint> getPointsList(double lgt, double lat,
			double distance) {

		List<LatlgtPoint> pointsList = new ArrayList<LatlgtPoint>();

		double dlng = 2 * Math.asin(Math.sin(distance / (2 * EARTH_RADIUS))
				/ Math.cos(rad(lat)));
		dlng = degrees(dlng);// 一定转换成角度数 原PHP文章这个地方说的不清楚根本不正确 后来lz又查了很多资料终于搞定了

		double dlat = distance / EARTH_RADIUS;
		dlat = degrees(dlat);// 一定转换成角度数

		// 左上角的顶点
		LatlgtPoint leftUpPoint = new LatlgtPoint();
		leftUpPoint.setLat(lat + dlat);
		leftUpPoint.setLgt(lgt - dlng);
		pointsList.add(leftUpPoint);

		// 左下角的顶点
		LatlgtPoint leftDownPoint = new LatlgtPoint();
		leftDownPoint.setLat(lat - dlat);
		leftDownPoint.setLgt(lgt - dlng);
		pointsList.add(leftDownPoint);

		// 右上角的顶点
		LatlgtPoint rightUpPoint = new LatlgtPoint();
		rightUpPoint.setLat(lat + dlat);
		rightUpPoint.setLgt(lgt + dlng);
		pointsList.add(rightUpPoint);

		// 右下角的顶点
		LatlgtPoint rightDownPoint = new LatlgtPoint();
		rightDownPoint.setLat(lat - dlat);
		rightDownPoint.setLgt(lgt + dlng);
		pointsList.add(rightDownPoint);

		return pointsList;

	}
*/
	/**
	 * 传入配置文件路径，
	 * @param lat
	 * @param lgt
	 * @param from
	 * @return
	 *//*
	public static LatlgtEntity convertLatToBaidu(String lat,
			String lgt, String from) {
		
		//返回的实体
		LatlgtEntity latLgtEntit=null;
		
		try {
			String filePath=getFilePath();
			// 从配置文件中获取百度ak
			String ak = PropertiesDAO.readValue(filePath, "baidu.ak");
			// 从配置文件中拿到读取百度API的超时时间
			int timeoutTime=Integer.valueOf(PropertiesDAO.readValue(filePath, "baiduapi.timeout"));
			
			HttpClient client = new HttpClient();

			StringBuffer stringBuffer = new StringBuffer();
//			stringBuffer.append("http://api.map.baidu.com/geoconv/v1/?")
//					.append("coords=").append(lat).append(",").append(lgt)
//					.append("&ak=").append(ak).append("&from=").append(from);

			stringBuffer.append("http://api.map.baidu.com/geoconv/v1/?")
			.append("coords=").append(lgt).append(",").append(lat)
			.append("&ak=").append(ak).append("&from=").append(from);
			
			//http://api.map.baidu.com/geoconv/v1/?coords=114.21892734521,29.575429778924;114.21892734521,29.575429778924&from=1&to=1&ak=你的密钥
			//DefaultHttpParams.getDefaultParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
			
			
			HttpMethod method = new GetMethod(stringBuffer.toString());// 使用get方式提交数据，由于无中文，所以编码无所谓，用默认的就好
			//method.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);

			client.getHttpConnectionManager().getParams().setConnectionTimeout(timeoutTime);
			
			client.executeMethod(method);
			// 打印结果页面
			String response = new String(method.getResponseBodyAsString());
			
			//response是个json串，解析它变成实体返回出去
			latLgtEntit=parseResponse(response);
			
			method.releaseConnection();
		} catch (HttpException e) {
			e.printStackTrace();
			throw new RuntimeException("网络连接不上");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException("字符集不支持");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("IO异常");
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("连接百度API失败，原因是:"+e.getMessage());
		}

		return latLgtEntit;
	}
*/
	/**
	 * 传入返回字符串，解析此字符串，将得到它里面包含的经度和纬度
	 * @param response
	 * @return
	 */
/*	private static LatlgtEntity parseResponse(String response){
		// 将获得的json字符串解析
		JSONObject jsonRootObject = JSONObject.fromObject(response);
		// 得到result节点
		JSONArray resultArr=jsonRootObject.getJSONArray("result");
		
		if(null!= resultArr && resultArr.size()>0){
			//这里只读一条，取第一条元素
			JSONObject resultObject=(JSONObject) resultArr.get(0);
			//将经纬度的位置修正正确modByGermmy start
			Float lgt=new Double(resultObject.getDouble("x")).floatValue();
			Float lat=new Double(resultObject.getDouble("y")).floatValue();
			//将经纬度的位置修正正确modByGermmy end
			//新建返回实体
			LatlgtEntity latLgtEntity=new LatlgtEntity();
			latLgtEntity.setLat(lat);
			latLgtEntity.setLgt(lgt);
			return latLgtEntity;
		}else{
			return null;
		}
		
		
	}*/
	
	
	/**
	 * 通过模板发送短信
	 * @param apikey apikey
	 * @param tpl_id　模板id
	 * @param tpl_value　模板变量值　
	 * @param mobile　接受的手机号
	 * @return json格式字符串
	 * @throws IOException 
	 */
	public static boolean  tplSendSms(String apikey, long tpl_id, String tpl_value, String mobile) throws IOException{
		HttpClient client = new HttpClient();
		NameValuePair[] nameValuePairs = new NameValuePair[4];
		nameValuePairs[0] = new NameValuePair("apikey", apikey);
		nameValuePairs[1] = new NameValuePair("tpl_id", String.valueOf(tpl_id));
		nameValuePairs[2] = new NameValuePair("tpl_value", tpl_value);
		nameValuePairs[3] = new NameValuePair("mobile", mobile);
		PostMethod method = new PostMethod(URI_TPL_SEND_SMS);
		method.setRequestBody(nameValuePairs);
		HttpMethodParams param = method.getParams();
		param.setContentCharset(ENCODING);
		client.executeMethod(method);
		
		//这里获取返回的JSON串，准备解析
		String respJson=method.getResponseBodyAsString();
		//将获得的json字符串解析
	/*	JSONObject jsonRootObject = JSONObject.fromObject(respJson);
		int respInt=jsonRootObject.getInt("code");
		if(respInt==0){
			return true;
		}else{
			//logger.warn("短信发送失败，失败的code是:"+respInt+"，明细是:"+jsonRootObject.getString("msg"));
			return false;
		}*/

	return true;
	}
	
	/**
	 * 传入文件名，获取到文件类型
	 * @param filePathName
	 * @return
	 */
	public static String getFileType(String filePathName){
		int index = filePathName.lastIndexOf(".");
		return filePathName.substring(index + 1, filePathName.length());
	}
	
	public final static String YYYYMMDDHHMMSSSSSSSS = "yyyyMMddHHmmssSSSSSS";
	public static String getDateNumFormat(){
		return Format(new java.util.Date(),YYYYMMDDHHMMSSSSSSSS);
	}
	
	public static String Format(java.util.Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}
	
	/**
	 * 保存文件
	 * @param originalFileName
	 * @param completeFilePath
	 * @param originalFile
	 * @return
	 * @throws Exception
	 */
	public static String saveFile(String originalFileName
			,String completeFilePath,
			File originalFile){
		String currentFileName="";
		String filetype = getFileType(originalFileName);
		currentFileName = getDateNumFormat()+"."+filetype;
		originalFile.renameTo(new File(completeFilePath +"\\"+ currentFileName));
		return "/upload/"+currentFileName;
	}
	
	
	public static double parseDouble(Object str) {
		return parseDouble(str, 0.0);
	}

	public static double parseDouble(Object str, double defaultValue) {
		String s = trim(str);
		Double i;
		try {
			i = Double.parseDouble(s);
		} catch (Exception e) {
			i = defaultValue;
			e.printStackTrace();
		}
		return i;
	}

	public static String trim(Object str) {
		if (str == null) {
			return "";
		} else {
			return str.toString().trim();
		}
	}
	
	
		// 获取当前季度 开始时间 
	   public  static java.util.Date getCurrentQuarterStartTime() { 
		   
		   SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd"); 
	        Calendar c = Calendar.getInstance(); 
	        int currentMonth = c.get(Calendar.MONTH) + 1; 
	        java.util.Date now = null; 
	        try { 
	            if (currentMonth >= 1 && currentMonth <= 3) 
	                c.set(Calendar.MONTH, 0); 
	            else if (currentMonth >= 4 && currentMonth <= 6) 
	                c.set(Calendar.MONTH, 3); 
	            else if (currentMonth >= 7 && currentMonth <= 9) 
	                c.set(Calendar.MONTH, 4); 
	            else if (currentMonth >= 10 && currentMonth <= 12) 
	                c.set(Calendar.MONTH, 9); 
	            c.set(Calendar.DATE, 1); 
	            now = shortSdf.parse(shortSdf.format(c.getTime())); 
	        } catch (Exception e) { 
	            e.printStackTrace(); 
	        } 
	        return now; 
	    } 
	
	
	   
	   // 获取当前季度的结束时间 
	    public static   java.util.Date getCurrentQuarterEndTime() { 
	    	
	    	 SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd"); 
	        Calendar c = Calendar.getInstance(); 
	        int currentMonth = c.get(Calendar.MONTH) + 1; 
	        java.util.Date now = null; 
	        try { 
	            if (currentMonth >= 1 && currentMonth <= 3) { 
	                c.set(Calendar.MONTH, 2); 
	                c.set(Calendar.DATE, 31); 
	            } else if (currentMonth >= 4 && currentMonth <= 6) { 
	                c.set(Calendar.MONTH, 5); 
	                c.set(Calendar.DATE, 30); 
	            } else if (currentMonth >= 7 && currentMonth <= 9) { 
	                c.set(Calendar.MONTH, 8); 
	                c.set(Calendar.DATE, 30); 
	            } else if (currentMonth >= 10 && currentMonth <= 12) { 
	                c.set(Calendar.MONTH, 11); 
	                c.set(Calendar.DATE, 31); 
	            } 
	            now = shortSdf.parse(shortSdf.format(c.getTime())); 
	        } catch (Exception e) { 
	            e.printStackTrace(); 
	        } 
	        return now; 
	    } 
	
	
	/**
	 * 得到java.util.Date类型时间
	 * 
	 * @param obj
	 *            String(yyyy-MM-dd),java.sql.Date,java.sql.Timestamp类型中的一种
	 * @param format
	 *            字符串日期格式
	 * @return java.util.Date
	 */
	public static java.util.Date getUtilDate(Object obj, String format) {
		if (obj == null)
			return null;
		DateFormat f = new SimpleDateFormat(format, Locale.CHINA);
		if (obj instanceof String) {
			try {
				return f.parse((String) obj);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} else if (obj instanceof Date) {
			return new java.util.Date(((Date) obj).getTime());
		} else if (obj instanceof java.sql.Timestamp) {
			return new java.util.Date(((java.sql.Timestamp) obj).getTime());
		}
		return null;
	}
	
	/**
	 * 得到String类型时间
	 * 
	 * @param obj
	 *            java.util.Date,java.sql.Date,java.sql.Timestamp类型中的一种
	 * @param format
	 *            字符串日期格式
	 * @return String yyyy-MM-dd
	 */
	public static String getStringDate(Object obj, String format) {
		if (obj == null)
			return null;
		DateFormat f = new SimpleDateFormat(format, Locale.CHINA);
		if (obj instanceof java.util.Date) {
			return f.format((java.util.Date) obj);
		} else if (obj instanceof Date) {
			return f.format((Date) obj);
		} else if (obj instanceof java.sql.Timestamp) {
			return f.format((java.sql.Timestamp) obj);
		}
		return obj.toString();
	}
	
	
	
	/**
	 * 判断传进来的日期是否是当月的第一天
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean isFirstDayOfMonth(java.util.Date date) {
		boolean rst=false;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		 int x = calendar.get(Calendar.DAY_OF_MONTH);
		 if(x==1){
			 rst=true;
		 }else{
			 rst=false;
		 }
        return rst;
	}
	
	
	/**
	 * 判断日期是否是1, 4, 7, 10 月份
	 */
	public static boolean isInFourMonthOfYear(java.util.Date date){
		boolean flag=false;
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		
		// 0: 1月份,  以此类推
		int x=calendar.get(Calendar.MONTH);
		if(x==0){
			flag=true;
		}else if(x==3){
			flag=true;
		}else if(x==6){
			flag=true;			
		}else if(x==9){
			flag=true;
		}
		
		
		return flag;
	}
	
	
	/**
	 * desc:获取当前月的第一天和最后一天的日期（java.util.Date）
	 * @throws Exception 
	 */
	public static java.util.Date getFirstCutMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		
        //获取当前月第一天：
        Calendar ccc = Calendar.getInstance();    
        ccc.add(Calendar.MONTH, 0);
        ccc.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        String first = format.format(ccc.getTime());
        //System.out.println("===============first:"+first);
        
        try {
			return parseStrToDate(first, "yyyy-MM-dd");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	/**
	 * desc:获取当前月最后一天的日期（java.util.Date）
	 * @throws Exception 
	 */
	public static java.util.Date getLastCutMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		
		   //获取当前月最后一天
        Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        String last = format.format(ca.getTime());
       // System.out.println("===============last:"+last);
		
        try {
			return parseStrToDate(last, "yyyy-MM-dd");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	/**
	 * 给指定的token发送指定的message
	 * @param pushToken
	 * @param message
	 * @return
	 * @throws IOException
	 */
	/*public static void  pushMessage(String pushToken,String message,String type){

		  *//**APNS推送需要的证书、密码、和设备的Token**//*
		String p12Path = CommonUtils_cjc.class.getResource("/")
				.getPath()+"/Certificates.p12";
        String  password = "123456";
        
        try {
            *//**设置参数，发送数据**//*
//            ApnsService service =APNS.newService().withCert(p12Path,password).withSandboxDestination().build();
        	ApnsService service =APNS.newService().withCert(p12Path,password).withProductionDestination().build();
            //APNS.newService().withc
            String payload=null;
            if(!CommonUtils_cjc.isEmpty(type)){
            	payload = APNS.newPayload().alertBody(message).badge(1).sound("default").customField("type", type).build();
            }else{
            	payload = APNS.newPayload().alertBody(message).badge(1).sound("default").customField("type", "").build();
            }
            service.push(pushToken, payload);
            logger.info("推送信息已发送！");
        } catch (Exception e) {
        	e.printStackTrace();
        	logger.error("推送消息出错了："+e.getMessage());
        }
	}*/
	
	
	/**
	 * 用苹果的APNS推送给用户
	 * @param pushToken
	 * @param message
	 */
//	private void pushByAPNS(String pushToken,String message){
//		CommonUtils_cjc.pushMessage(pushToken, message);
//	}
	
	
	/**
	 * 推送给终端用户或者工作人员
	 * @param
	 * @param pushType	1:用户，0：工作人员
	 * @param orderType	紧急救援；上门检测
	 * @param	location	事发地
	 */
	public static void pushBySMSForPaidan(String usermobile,String shopmobile,int pushType,String orderType,String location, String carno){
		//发送短信通知用户，检测员的手机号
		try {
			//1.拿到apikey
			String apiKey=PropertiesDAO.readValue(null, "sms.apikey");
			if(pushType==1){//用户
				//2.拿到模板id
				long templateId=Long.valueOf(PropertiesDAO.readValue(null, "sms.user.templateid"));
				//2.0 装配发送的模板值     #checkusermobile#=checkmobile&#company#=\u8F66\u5B9D\u5B9D
				String tpl_value=PropertiesDAO.readValue(null, "sms.user.templatecontent");
				//将里面的变量替换掉
				tpl_value=tpl_value.replace("theusermobile", usermobile)
						.replace("theordertype", orderType)
						.replace("thestaffmobile", shopmobile);
				CommonUtils_cjc.tplSendSms( apiKey, templateId,  tpl_value,  usermobile);
			}else{//其他情况发给工作人员
				//2.拿到模板id
				long templateId=Long.valueOf(PropertiesDAO.readValue(null, "sms.staff.templateid"));
				//2.0 装配发送的模板值     #checkusermobile#=checkmobile&#company#=\u8F66\u5B9D\u5B9D
				String tpl_value=PropertiesDAO.readValue(null, "sms.staff.templatecontent");
				//将里面的变量替换掉
				tpl_value=tpl_value.replace("thestaffmobile", shopmobile)
						.replace("theusermobile", usermobile)
						.replace("thecarno", carno)
						.replace("theordertype", orderType)
						.replace("thelocation", location);
				CommonUtils_cjc.tplSendSms( apiKey, templateId,  tpl_value,  shopmobile);
			}
		} catch (Exception e) {
			logger.warn("派单过程中，发送短信通知用户失败！"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 为订单确认准备的短信
	 * @param usermobile
	 * @param orderNo
	 * @param
	 */
	public static void pushBySMSForOrderConfirm(String usermobile,String orderNo){
		//发送短信通知用户
		try {
			//1.拿到apikey
			String apiKey=PropertiesDAO.readValue(null, "sms.apikey");
			//2.拿到模板id
			long templateId=Long.valueOf(PropertiesDAO.readValue(null, "sms.user.orderconfirm.templateid"));
			//2.0 装配发送的模板值     #checkusermobile#=checkmobile&#company#=\u8F66\u5B9D\u5B9D
			String tpl_value=PropertiesDAO.readValue(null, "sms.user.orderconfirm.templatecontent");
			//将里面的变量替换掉
			tpl_value=tpl_value.replace("theusermobile", usermobile)
					.replace("theorderno", orderNo);
			CommonUtils_cjc.tplSendSms( apiKey, templateId,  tpl_value,  usermobile);
		} catch (Exception e) {
			//logger.warn("订单确认过程中，发送短信通知用户失败！"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 为结算确认短信
	 * @param shopmobile
	 * @param date
	 * @param
	 */
	public static void pushBySMSForJiesuanApproved(String shopmobile,String date,Double jiesuanmoney){
		//发送短信通知用户
		try {
			//1.拿到apikey
			String apiKey=PropertiesDAO.readValue(null, "sms.apikey");
			//2.拿到模板id
			long templateId=Long.valueOf(PropertiesDAO.readValue(null, "sms.shop.jiesuanapproved.templateid"));
			//2.0 装配发送的模板值     #checkusermobile#=checkmobile&#company#=\u8F66\u5B9D\u5B9D
			String tpl_value=PropertiesDAO.readValue(null, "sms.shop.jiesuanapproved.templatecontent");
			//将里面的变量替换掉
			tpl_value=tpl_value.replace("theshopmobile", shopmobile)
					.replace("thedate", date)
					.replace("thejiesuan", String.valueOf(jiesuanmoney));
			CommonUtils_cjc.tplSendSms( apiKey, templateId,  tpl_value,  shopmobile);
		} catch (Exception e) {
			logger.warn("审批通过认过程中，发送短信通知商户失败！"+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * 将amr转化成mp3
	 * @param sourcePath
	 * @param targetPath
	 */
/*	public static void changeToMp3(String sourcePath, String targetPath) {
		File source = new File(sourcePath);
		File target = new File(targetPath);
		AudioAttributes audio = new AudioAttributes();
		Encoder encoder = new Encoder();

		audio.setCodec("libmp3lame");
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("mp3");
		attrs.setAudioAttributes(audio);

		try {
			encoder.encode(source, target, attrs);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InputFormatException e) {
			e.printStackTrace();
		} catch (EncoderException e) {
			e.printStackTrace();
		}
	}*/
	
	
	/**
	 * 获取随机数
	 * @return
	 */
	public static String getRamdomnum(){
		Double a=Math.random()*900000+100000;
		return String.valueOf(a.intValue());
		
	}
	
	
	/**
	 * 获取当前最新的jquery easy ui里面的日期格式
	 * @return
	 */
	public static String getJqueryTimeFormat(){
		return PropertiesDAO.readValue(null, "jqueryeasui.english.format");
	}
	
	/**
	 * 传入电话号码，判断此号码是否合法
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles){
        Pattern p = Pattern.compile("^((14[0-9])|(13[0-9])|(17[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches()+"---");
        return m.matches();
    }
	
	
	// 简体中文的编码范围从B0A1（45217）一直到F7FE（63486）
		private static int BEGIN = 45217;
		private static int END = 63486;

		// 按照声 母表示，这个表是在GB2312中的出现的第一个汉字，也就是说“啊”是代表首字母a的第一个汉字。
		// i, u, v都不做声母, 自定规则跟随前面的字母
		private static char[] chartable = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝', };

		// 二十六个字母区间对应二十七个端点
		// GB2312码汉字区间十进制表示
		private static int[] table = new int[27];

		// 对应首字母区间表
		private static char[] initialtable = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'h', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 't', 't', 'w', 'x', 'y', 'z', };

		// 初始化
		static {
			for (int i = 0; i < 26; i++) {
				table[i] = gbValue(chartable[i]);// 得到GB2312码的首字母区间端点表，十进制。
			}
			table[26] = END;// 区间表结尾
		}

		// ------------------------public方法区------------------------
		// 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串 最重要的一个方法，思路如下：一个个字符读入、判断、输出

		public static String cn2py(String SourceStr) {
			String Result = "";
			int StrLength = SourceStr.length();
			int i;
			try {
				for (i = 0; i < StrLength; i++) {
					Result += Char2Initial(SourceStr.charAt(i));
				}
			} catch (Exception e) {
				Result = "";
				e.printStackTrace();
			}
			return Result;
		}

		// ------------------------private方法区------------------------
		/**
		 * 输入字符,得到他的声母,英文字母返回对应的大写字母,其他非简体汉字返回 '0' 　　* 　　
		 */
		private static char Char2Initial(char ch) {
			// 对英文字母的处理：小写字母转换为大写，大写的直接返回
			if (ch >= 'a' && ch <= 'z') {
				return (char) (ch - 'a' + 'A');
			}
			if (ch >= 'A' && ch <= 'Z') {
				return ch;
			}
			// 对非英文字母的处理：转化为首字母，然后判断是否在码表范围内，
			// 若不是，则直接返回。
			// 若是，则在码表内的进行判断。
			int gb = gbValue(ch);// 汉字转换首字母
			if ((gb < BEGIN) || (gb > END))// 在码表区间之前，直接返回
			{
				return ch;
			}
			int i;
			for (i = 0; i < 26; i++) {// 判断匹配码表区间，匹配到就break,判断区间形如“[,)”
				if ((gb >= table[i]) && (gb < table[i + 1])) {
					break;
				}
			}
			if (gb == END) {// 补上GB2312区间最右端
				i = 25;
			}
			return initialtable[i]; // 在码表区间中，返回首字母
		}

		/**
		 * 取出汉字的编码 cn 汉字 　　
		 */
		private static int gbValue(char ch) {// 将一个汉字（GB2312）转换为十进制表示。
			String str = new String();
			str += ch;
			try {
				byte[] bytes = str.getBytes("GB2312");
				if (bytes.length < 2) {
					return 0;
				}
				return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
			} catch (Exception e) {
				return 0;
			}
		}
	
		
		/**
		 * desc:  产生  uuid
		 */
		public static String getNewUUID(){
			return UUID.randomUUID().toString();
		}
		
		/**
		 * 返回昨天的日期
		 * 
		 * @return
		 * @throws Exception
		 */
		public static String getYesterday(String format) throws Exception {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new java.util.Date());
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			return convertDateToStr(calendar.getTime(), format);
		}
		
		
}
