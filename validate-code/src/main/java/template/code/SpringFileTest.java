/**
 * 描述: 
 * SpringFileTest.java
 * 
 * @author qye.zheng
 *  version 1.0
 */
package template.code;

// 静态导入
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.hua.constant.Constant;
import com.hua.test.BaseTest;
import com.hua.util.ClassPathUtil;
import com.hua.util.ProjectUtil;


/**
 * 描述: 
 * 
 * @author qye.zheng
 * SpringFileTest
 */
/*
 * 
 * @SpringJUnit4ClassRunner 运行器负责拉起 spring 环境
 * @ContextConfiguration 指定 spring配置文件，若不指定，则使用默认配置.
 */
// for Junit 4.x
@WebAppConfiguration(value = "src/main/webapp")
@ContextConfiguration(locations = {
		"classpath:conf/xml/spring-bean.xml", 
		"classpath:conf/xml/spring-config.xml", 
		"classpath:conf/xml/spring-mvc.xml"
		})
@RunWith(SpringJUnit4ClassRunner.class)
public final class SpringFileTest extends BaseTest {

	/*
	配置方式1: 
	@WebAppConfiguration(value = "src/main/webapp")  
	@ContextConfiguration(locations = {
			"classpath:conf/xml/spring-bean.xml", 
			"classpath:conf/xml/spring-config.xml", 
			"classpath:conf/xml/spring-mvc.xml", 
			"classpath:conf/xml/spring-service.xml"
		})
	@RunWith(SpringJUnit4ClassRunner.class)  
	
	配置方式2: 	
	@WebAppConfiguration(value = "src/main/webapp")  
	@ContextHierarchy({  
		 @ContextConfiguration(name = "parent", locations = "classpath:spring-config.xml"),  
		 @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml")  
		}) 
	@RunWith(SpringJUnit4ClassRunner.class)  
	 */
	
	/**
	 * 而启动spring 及其mvc环境，然后通过注入方式，可以走完 spring mvc 完整的流程.
	 * 
	 */
	
	//@PathVariable
	
	//@Resource(type = WebApplicationContext.class)
	//@Autowired
	@Resource
    private WebApplicationContext webApplicationContext; 
	
	/**
	 * 引当前项目用其他项目之后，然后可以使用
	 * SpringJunitTest模板测试的其他项目
	 * 
	 * 可以使用所引用目标项目的所有资源
	 * 若引用的项目的配置与本地的冲突或无法生效，需要
	 * 将目标项目的配置复制到当前项目同一路径下
	 * 
	 */

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testUpload() {
		try {
			// 页面/服务 地址
			String url = "/api/file/upload/v2";
			// 请求构建器
			// get 方法
			//MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url);
			// post 方法
			MockMultipartHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.fileUpload(url);
			/*
			 * 设置请求参数
			 */
			requestBuilder.param("username", "admin");
			// 文件上传使用这种媒介类型
			requestBuilder.contentType(MediaType.MULTIPART_FORM_DATA_VALUE);
			// 文件对象
			String path = ClassPathUtil.getClassSubpath("/file/img/白熊_01.jpg", true);
			InputStream inputStream = new FileInputStream(path);
			MockMultipartFile file = new MockMultipartFile("file", "白熊_01.jpg", "", inputStream);
			requestBuilder.file(file);
			
			// 模拟 mvc 对象，设置 WebApplicationContext，然后构建 模拟mvc对象
			MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); 
			// mvc 结果
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			
			// 响应对象
			MockHttpServletResponse response = mvcResult.getResponse();
			// 获取字符串形式的响应内容
			String result = response.getContentAsString();
			
			System.out.println(result);
			
			// 异常对象
			//Exception exception = mvcResult.getResolvedException();
			
			
		} catch (Exception e) {
			log.error("testUpload =====> ", e);
		}
	}

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testUploads() {
		try {
			// 页面/服务 地址
			String url = "/api/file/upload/v3";
			// 请求构建器
			// get 方法
			//MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url);
			// post 方法
			MockMultipartHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.fileUpload(url);
			/*
			 * 设置请求参数
			 */
			requestBuilder.param("username", "admin");
			// 文件对象
			String path = ClassPathUtil.getClassSubpath("/file/img/白熊_01.jpg", true);
			InputStream inputStream = new FileInputStream(path);
			MockMultipartFile file = new MockMultipartFile("file1", "白熊_01.jpg", "", inputStream);
			requestBuilder.file(file);
			
			path = ClassPathUtil.getClassSubpath("/file/img/抱树小狗_01.jpg", true);
			inputStream = new FileInputStream(path);
			file = new MockMultipartFile("file2", "抱树小狗_01.jpg", "", inputStream);
			requestBuilder.file(file);
			
			// 模拟 mvc 对象，设置 WebApplicationContext，然后构建 模拟mvc对象
			MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); 
			// mvc 结果
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			
			// 响应对象
			MockHttpServletResponse response = mvcResult.getResponse();
			// 获取字符串形式的响应内容
			String result = response.getContentAsString();
			
			System.out.println(result);
			
			// 异常对象
			//Exception exception = mvcResult.getResolvedException();
			
			
		} catch (Exception e) {
			log.error("testUploads =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testUploads2() {
		try {
			// 页面/服务 地址
			String url = "/api/file/upload/v5";
			// 请求构建器
			// get 方法
			//MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url);
			// post 方法
			MockMultipartHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.fileUpload(url);
			/*
			 * 设置请求参数
			 */
			requestBuilder.param("username", "admin");
			String path = null;
			InputStream inputStream = null;
			MockMultipartFile file = null;
			// 文件对象
			
			// 第一部分
			path = ClassPathUtil.getClassSubpath("/file/img/白熊_01.jpg", true);
			inputStream = new FileInputStream(path);
			file = new MockMultipartFile("files1", "白熊_01.jpg", "", inputStream);
			requestBuilder.file(file);
			
			path = ClassPathUtil.getClassSubpath("/file/img/沉睡老虎.jpg", true);
			inputStream = new FileInputStream(path);
			file = new MockMultipartFile("files1", "沉睡老虎.jpg", "", inputStream);
			requestBuilder.file(file);
			
			// 第二部分
			path = ClassPathUtil.getClassSubpath("/file/img/抱树小狗_01.jpg", true);
			inputStream = new FileInputStream(path);
			file = new MockMultipartFile("files2", "抱树小狗_01.jpg", "", inputStream);
			requestBuilder.file(file);
			
			path = ClassPathUtil.getClassSubpath("/file/img/沉稳猩猩_01.jpg", true);
			inputStream = new FileInputStream(path);
			file = new MockMultipartFile("files2", "沉稳猩猩_01.jpg", "", inputStream);
			requestBuilder.file(file);
			
			// 模拟 mvc 对象，设置 WebApplicationContext，然后构建 模拟mvc对象
			MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); 
			
			// mvc 结果
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			
			// 响应对象
			MockHttpServletResponse response = mvcResult.getResponse();
			// 获取字符串形式的响应内容
			String result = response.getContentAsString();
			
			System.out.println(result);
			
			// 异常对象
			//Exception exception = mvcResult.getResolvedException();
			
			
		} catch (Exception e) {
			log.error("testUploads2 =====> ", e);
		}
	}		

	/**
	 * 
	 * 描述: 上传文件，同时携带其他参数
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testUpload6() {
		try {
			// 页面/服务 地址
			String url = "/api/file/upload/v6";
			// 请求构建器
			// get 方法
			//MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url);
			// post 方法
			MockMultipartHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.fileUpload(url);
			/*
			 * 设置请求参数
			 */
			requestBuilder.param("username", "admin");
			requestBuilder.param("password", "123456");
			
			// 文件对象
			String path = null;
			InputStream inputStream = null;
			MockMultipartFile file = null;
			// 第一部分
			path = ClassPathUtil.getClassSubpath("/file/img/白熊_01.jpg", true);
			inputStream = new FileInputStream(path);
			file = new MockMultipartFile("files1", "白熊_01.jpg", "", inputStream);
			requestBuilder.file(file);
			
			path = ClassPathUtil.getClassSubpath("/file/img/沉睡老虎.jpg", true);
			inputStream = new FileInputStream(path);
			file = new MockMultipartFile("files1", "沉睡老虎.jpg", "", inputStream);
			requestBuilder.file(file);
			
			// 第二部分
			path = ClassPathUtil.getClassSubpath("/file/img/抱树小狗_01.jpg", true);
			inputStream = new FileInputStream(path);
			file = new MockMultipartFile("files2", "抱树小狗_01.jpg", "", inputStream);
			requestBuilder.file(file);
			
			path = ClassPathUtil.getClassSubpath("/file/img/沉稳猩猩_01.jpg", true);
			inputStream = new FileInputStream(path);
			file = new MockMultipartFile("files2", "沉稳猩猩_01.jpg", "", inputStream);
			requestBuilder.file(file);
			
			// 模拟 mvc 对象，设置 WebApplicationContext，然后构建 模拟mvc对象
			MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); 
			
			// mvc 结果
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			
			// 响应对象
			MockHttpServletResponse response = mvcResult.getResponse();
			// 获取字符串形式的响应内容
			String result = response.getContentAsString();
			
			System.out.println(result);
			
			// 异常对象
			//Exception exception = mvcResult.getResolvedException();
			
		} catch (Exception e) {
			log.error("testUploads2 =====> ", e);
		}
	}		
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDownload1() {
		try {
			// 页面/服务 地址
			String url = "/api/file/download/v1";
			// 请求构建器
			// get 方法
			//MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url);
			// post 方法
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url);
			/*
			 * 设置请求参数
			 */
			//requestBuilder.param("username", "admin");
			
			// 模拟 mvc 对象，设置 WebApplicationContext，然后构建 模拟mvc对象
			MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); 
			// mvc 结果
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			
			// 响应对象
			MockHttpServletResponse response = mvcResult.getResponse();
			String filePath = ProjectUtil.getAbsolutePath("/doc/download/t1.png", true);
			File file = new File(filePath);
			OutputStream outputStream = new FileOutputStream(file);
			//OutputStream outputStream = new FileOutputStream(ProjectUtil.getAbsolutePath("/doc/download/t.png", true));
			//outputStream.write(response.getContentAsByteArray());
			//outputStream.flush();
			IOUtils.write(response.getContentAsByteArray(), outputStream);
			// 异常对象
			//Exception exception = mvcResult.getResolvedException();
			
			// 异常对象
			//Exception exception = mvcResult.getResolvedException();
			
			
		} catch (Exception e) {
			log.error("testDownload =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDownload2() {
		try {
			// 页面/服务 地址
			String url = "/api/file/download/v2";
			// 请求构建器
			// get 方法
			//MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url);
			// post 方法
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url);
			/*
			 * 设置请求参数
			 */
			//requestBuilder.param("username", "admin");
			
			// 模拟 mvc 对象，设置 WebApplicationContext，然后构建 模拟mvc对象
			MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); 
			// mvc 结果
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			
			// 响应对象
			MockHttpServletResponse response = mvcResult.getResponse();
			String filePath = ProjectUtil.getAbsolutePath("/doc/download/t2.png", true);
			File file = new File(filePath);
			OutputStream outputStream = new FileOutputStream(file);
			//OutputStream outputStream = new FileOutputStream(ProjectUtil.getAbsolutePath("/doc/download/t.png", true));
			//outputStream.write(response.getContentAsByteArray());
			//outputStream.flush();
			IOUtils.write(response.getContentAsByteArray(), outputStream);
			// 异常对象
			//Exception exception = mvcResult.getResolvedException();
			
			// 异常对象
			//Exception exception = mvcResult.getResolvedException();
			
			
		} catch (Exception e) {
			log.error("testDownload2 =====> ", e);
		}
	}	
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testDownload3() {
		try {
			// 页面/服务 地址
			String url = "/api/file/download/v3";
			// 请求构建器
			// get 方法
			//MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url);
			// post 方法
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url);
			/*
			 * 设置请求参数
			 */
			//requestBuilder.param("username", "admin");
			
			// 模拟 mvc 对象，设置 WebApplicationContext，然后构建 模拟mvc对象
			MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); 
			// mvc 结果
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			
			// 响应对象
			MockHttpServletResponse response = mvcResult.getResponse();
			String contentDispostion = response.getHeader("Content-Disposition");
			log.info("testDownload3 =====> contentDispostion = " + contentDispostion);
			String filename = contentDispostion.substring(contentDispostion.lastIndexOf("attachment;filename=") + "attachment;filename=".length());
			// URL 反编码
			filename =  java.net.URLDecoder.decode(filename, Constant.CHART_SET_UTF_8);
			log.info("testDownload3 =====> filename = " + filename);
			String filePath = ProjectUtil.getAbsolutePath("/doc/download/" + filename, true);
			File file = new File(filePath);
			OutputStream outputStream = new FileOutputStream(file);
			log.info("testDownload3 =====> 文件大小: " + response.getContentLengthLong());
			//OutputStream outputStream = new FileOutputStream(ProjectUtil.getAbsolutePath("/doc/download/t.png", true));
			//outputStream.write(response.getContentAsByteArray());
			//outputStream.flush();
			IOUtils.write(response.getContentAsByteArray(), outputStream);
			// 异常对象
			//Exception exception = mvcResult.getResolvedException();
			
			// 异常对象
			//Exception exception = mvcResult.getResolvedException();
			
			
		} catch (Exception e) {
			log.error("testDownload3 =====> ", e);
		}
	}		

	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMockMVC() {
		try {
			// 页面/服务 地址
			String url = "/api/sys/login";
			// 请求构建器
			// get 方法
			//MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url);
			// post 方法
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url);
			/*
			 * 设置请求参数
			 */
			requestBuilder.param("username", "admin");
			
			// 模拟 mvc 对象，设置 WebApplicationContext，然后构建 模拟mvc对象
			MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); 
			// mvc 结果
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			
			
			// 响应对象
			MockHttpServletResponse response = mvcResult.getResponse();
			// 获取字符串形式的响应内容
			String result = response.getContentAsString();
			
			System.out.println(result);
			
			// 异常对象
			//Exception exception = mvcResult.getResolvedException();
			
			
		} catch (Exception e) {
			log.error("testMockMVC =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testMockMVCExample() {
		try {
			// 页面/服务 地址
			String url = "/api/sys/login";
			// 请求构建器
			//RequestBuilder requestBuilder = MockMvcRequestBuilders.get(url);
			// get 方法
			//MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url);
			// post 方法
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post(url);
			//String json = "{\"username\":\"admin\", \"password\":\"123456\"}";
			//requestBuilder.content(json);
			/*
			 * 设置请求参数
			 */
			requestBuilder.param("username", "admin");
			requestBuilder.param("password", "123456");
			//MockMvc mockMvc =  MockMvcBuilders.standaloneSetup(userController).build(); 
			
			// 模拟 mvc 对象，设置 WebApplicationContext，然后构建 模拟mvc对象
			MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build(); 
			// mvc 结果
			MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
			
			
			// 响应对象
			MockHttpServletResponse response = mvcResult.getResponse();
			// 获取字符串形式的响应内容
			String result = response.getContentAsString();
			System.out.println(result);
			
			//Map<String, Object> map = mvcResult.getModelAndView().getModel();
			//System.out.println(map);
			// 结果对象
			//Object resultObj = mvcResult.getAsyncResult();
			//System.out.println(resultObj.toString());
			
			// 异常对象
			Exception exception = mvcResult.getResolvedException();
			
			
		} catch (Exception e) {
			log.error("testMockMVCExample =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void test() {
		try {
		
			
		} catch (Exception e) {
			log.error("test =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testTemp() {
		try {
			
			
		} catch (Exception e) {
			log.error("testTemp=====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testCommon() {
		try {
			
			
		} catch (Exception e) {
			log.error("testCommon =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testSimple() {
		try {
			
			
		} catch (Exception e) {
			log.error("testSimple =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 */
	@Test
	public void testBase() {
		try {
			
			
		} catch (Exception e) {
			log.error("testBase =====> ", e);
		}
	}
	
	/**
	 * 
	 * 描述: 解决ide静态导入消除问题 
	 * @author qye.zheng
	 * 
	 */
	@Ignore("解决ide静态导入消除问题 ")
	private void noUse() {
		String expected = null;
		String actual = null;
		Object[] expecteds = null;
		Object[] actuals = null;
		String message = null;
		
		assertEquals(expected, actual);
		assertEquals(message, expected, actual);
		assertNotEquals(expected, actual);
		assertNotEquals(message, expected, actual);
		
		assertArrayEquals(expecteds, actuals);
		assertArrayEquals(message, expecteds, actuals);
		
		assertFalse(true);
		assertTrue(true);
		assertFalse(message, true);
		assertTrue(message, true);
		
		assertSame(expecteds, actuals);
		assertNotSame(expecteds, actuals);
		assertSame(message, expecteds, actuals);
		assertNotSame(message, expecteds, actuals);
		
		assertNull(actuals);
		assertNotNull(actuals);
		assertNull(message, actuals);
		assertNotNull(message, actuals);
		
		assertThat(null, null);
		assertThat(null, null, null);
		
		fail();
		fail("Not yet implemented");
		
	}

}
