package eu.innovtech.WabVcenterPlugin;

import java.util.*;


//import java.nio.charset.StandardCharsets;
//import java.util.Base64.Encoder;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.net.SocketAddress;
//import java.util.logging.*;
//import java.text.SimpleDateFormat;
import java.util.Date;

//import javax.imageio.IIOException;
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.HttpsURLConnection;
//import javax.net.ssl.SSLSession;
import javax.xml.ws.BindingProvider;
//import javax.xml.ws.soap.SOAPFaultException;

//import java.rmi.RemoteException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
//import java.util.Date;
import java.util.Properties;
import eu.innovtech.WabVcenterPlugin.DisableSecurity;

//import java.sql.SQLException;
import java.io.*;
//import java.net.*;
//import java.util.Base64;
import com.vmware.vim25.*;
//import com.vmware.vim.*;
//import com.vmware.*;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;

//import org.apache.http.ConnectionReuseStrategy;
//import org.apache.http.HttpHost;
//import org.apache.http.HttpResponse;
//import org.apache.http.HttpStatus;
//import org.apache.http.impl.DefaultBHttpClientConnection;
//import org.apache.http.impl.DefaultConnectionReuseStrategy;
//import org.apache.http.message.BasicHttpRequest;
//import org.apache.http.protocol.HttpCoreContext;
//import org.apache.http.protocol.HttpProcessor;
//import org.apache.http.protocol.HttpProcessorBuilder;
//import org.apache.http.protocol.HttpRequestExecutor;
//import org.apache.http.protocol.RequestConnControl;
//import org.apache.http.protocol.RequestContent;
//import org.apache.http.protocol.RequestExpectContinue;
//import org.apache.http.protocol.RequestTargetHost;
//import org.apache.http.protocol.RequestUserAgent;
//import org.apache.http.util.EntityUtils;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.client.methods.HttpRequestBase;

//import java.io.File;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import com.jcabi.ssh.SSH;
import com.jcabi.ssh.SSHByPassword;
import com.jcabi.ssh.Shell;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
//import org.apache.http.ssl.SSLContexts;
//import org.apache.http.util.EntityUtils;

import eu.innovtech.WabVcenterPlugin.WabDeviceService;
import eu.innovtech.WabVcenterPlugin.WabDevice;
//import eu.innovtech.WabVcenterPlugin.WabConnection;


/**
 * @author patrick.szymkowiak@free.fr
 *
 */
/**
 * @author patrick.szymkowiak@free.fr
 *
 */
public class WabCommandLine 
{
	public static Connection connectionDB = null;
	public static InputStream inputStream;
	public static String vcenter_url;
	public static String vcenter_user;
	public static String vcenter_password;
	public static String vcenter_extract_list;
	public static String wab_hostname;
	public static int wab_ssh_port;
	public static String wab_url_api;
	public static String wab_url_api_devices;
	public static String wab_user_api;
	public static String wab_password_api;
	public static String wab_api_key;
	public static String wab_extract_list;
	public static String wab_exclude_list_device;
	public static String wab_testacessdevice_list;
	//should have a better way but will do it later
	public static int wab_defaut_linux_service_number;
	public static String wab_linux_servicename_0;
	public static String wab_linux_service_0;
	public static String wab_linux_subprotocol_0;
	public static String wab_linux_account_0;
	public static int wab_linux_port_0;
	public static String wab_linux_servicename_1;
	public static String wab_linux_service_1;
	public static String wab_linux_subprotocol_1;
	public static String wab_linux_account_1;
	public static int wab_linux_port_1;	

	public static int wab_defaut_windows_service_number;
	public static String wab_windows_servicename_0;
	public static String wab_windows_service_0;
	public static String wab_windows_subprotocol_0;
	public static String wab_windows_account_0;
	public static int wab_windows_port_0;
	public static String wab_windows_servicename_1;
	public static String wab_windows_service_1;
	public static String wab_windows_subprotocol_1;
	public static String wab_windows_account_1;
	public static int wab_windows_port_1;	

	public static VcenterWrapper theVcenterServer;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//public static VirtualMachine[] vmArray;
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void dbConnection() {
		////////////////////////////////////
		//connection to BDD
		////////////////////////////////////
		try {
			Class.forName("org.postgresql.Driver");
			connectionDB = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/virtuvisu","virtuvisu","virtuvisu");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
			System.out.println("Opened database successfully");
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void deleteDevice(String deviceName) throws IOException {
		String listofdevices="";
		CloseableHttpClient httpclient = null;
		    SSLContext sslContext=null;
			try {
				sslContext = new SSLContextBuilder()
					      .loadTrustMaterial(null, (certificate, authType) -> true).build();
				
		        CredentialsProvider credsProvider = new BasicCredentialsProvider();
		        credsProvider.setCredentials(
		                new AuthScope(wab_hostname, 443),
		                new UsernamePasswordCredentials(wab_user_api, wab_password_api));
			    
			    	    httpclient = HttpClients.custom()
			    	      .setSSLContext(sslContext)
			    	      .setDefaultCredentialsProvider(credsProvider)
			    	      .setSSLHostnameVerifier(new NoopHostnameVerifier())
			    	      .build();

	            HttpGet httpget = new HttpGet(wab_url_api_devices+"/"+deviceName);

	            System.out.println("Executing request " + httpget.getRequestLine());

	            CloseableHttpResponse response = null;
				try {
					response = httpclient.execute(httpget);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            try {
	                HttpEntity entity = response.getEntity();

	                System.out.println("----------------------------------------");
	                System.out.println(response.getStatusLine());
	                //EntityUtils.consume(entity);
	                
	                InputStream entityResponse = entity.getContent();
	            	InputStreamReader is = new InputStreamReader(entityResponse);
	                StringBuilder sb = new StringBuilder();
	                BufferedReader br = new BufferedReader(is);
	                 try {
	                    String read = br.readLine();

	                    while(read !=null){
	                        sb.append(read);
	                        read = br.readLine();
	                    }

	                } catch (IOException e) {
	                    e.printStackTrace();
	                }

	                            
	                System.out.println("Content:"+sb.toString());
	            } finally {
	                response.close();
	            }
	        } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
	            try {
					httpclient.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public static void addDevice(String deviceName,String deviceOS,String deviceDescription,String deviceIP,List<WabDeviceService> deviceServiceToAdd) throws Exception {
	CloseableHttpClient httpclient = null;
    SSLContext sslContext=null;
	try {
		sslContext = new SSLContextBuilder()
			      .loadTrustMaterial(null, (certificate, authType) -> true).build();
		
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(
		        new AuthScope(wab_hostname, 443),
		        new UsernamePasswordCredentials(wab_user_api, wab_password_api));
		
			    httpclient = HttpClients.custom()
			      .setSSLContext(sslContext)
			      .setDefaultCredentialsProvider(credsProvider)
			      .setSSLHostnameVerifier(new NoopHostnameVerifier())
			      .build();
			    
			    WabDevice deviceToAdd=new WabDevice();
			    deviceToAdd.setDeviceName(deviceName);
			    deviceToAdd.setAlias("alias");
			    deviceToAdd.setDescription(deviceDescription);
		deviceToAdd.setDeviceHost(deviceIP);		
		deviceToAdd.services=deviceServiceToAdd;
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		System.out.println("JSON to Send:\r\n"+gson.toJson(deviceToAdd).toString()+"\r\n");
		StringEntity params = new StringEntity(gson.toJson(deviceToAdd));
		System.out.println("sending:"+params);	    
		HttpPost  httppost = new HttpPost(wab_url_api_devices);
		httppost.addHeader("content-type", "application/json");
		httppost.setEntity(params);
        System.out.println("Executing request " + httppost.getRequestLine());
        CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httppost);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        HttpEntity entity = response.getEntity();
		System.out.println("----------------------------------------");
		System.out.println(response.getStatusLine());
		
		 InputStream ips  = response.getEntity().getContent();
	        BufferedReader buf = new BufferedReader(new InputStreamReader(ips,"UTF-8"));
	        StringBuilder sb = new StringBuilder();
	        String s;
	        while(true )
	        {
	            s = buf.readLine();
	            if(s==null || s.length()==0)
	                break;
	            sb.append(s);

	        }
	        System.out.println("Content:"+sb.toString());
	        buf.close();
	        ips.close();
		
    } catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e1) {
		e1.printStackTrace();
	} finally {
        try {
			httpclient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public static List<String> listDevices() {
		List<String> listofdevices=new ArrayList<String>();
		List<WabDevice> listofdevicefromwab=null;
		CloseableHttpClient httpclient = null;
		SSLContext sslContext=null;
		try {
			sslContext = new SSLContextBuilder()
					.loadTrustMaterial(null, (certificate, authType) -> true).build();
	
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(
					new AuthScope(wab_hostname, 443),
					new UsernamePasswordCredentials(wab_user_api, wab_password_api));
	
			httpclient = HttpClients.custom()
					.setSSLContext(sslContext)
					.setDefaultCredentialsProvider(credsProvider)
					.setSSLHostnameVerifier(new NoopHostnameVerifier())
					.build();
	
			HttpGet httpget = new HttpGet(wab_url_api_devices);
	
			System.out.println("Executing request " + httpget.getRequestLine());
	
			CloseableHttpResponse response = null;
			try {
				response = httpclient.execute(httpget);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				HttpEntity entity = response.getEntity();
	
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				//EntityUtils.consume(entity);
	
				InputStream entityResponse = entity.getContent();
				InputStreamReader is = new InputStreamReader(entityResponse);
				StringBuilder sb = new StringBuilder();
				BufferedReader br = new BufferedReader(is);
				try {
					String read = br.readLine();
	
					while(read !=null){
						sb.append(read);
						read = br.readLine();
					}
	
				} catch (IOException e) {
					e.printStackTrace();
				}
	
	
				System.out.println("Content:"+sb.toString());
				listofdevicefromwab=Arrays.asList(new Gson().fromJson(sb.toString(), WabDevice[].class));
			} catch (UnsupportedOperationException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {
				try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//return what we want from the wab classes. here the name, service , ip ,first account
		//listofdevices.add("device_name;device_host;protocol;port;account_name;");
		for(WabDevice tempWabDevice: listofdevicefromwab) {
			for(WabDeviceService tempWabDeviceService : tempWabDevice.getServices() ) {
				for(WabDeviceAccount tempWabDeviceAccount : tempWabDeviceService.getAccounts() )
					listofdevices.add(tempWabDevice.device_name+";"+tempWabDevice.device_host+";"
							+tempWabDeviceService.getProtocol()+";"
							+tempWabDeviceService.getSubprotocols().toString()+";"
							+tempWabDeviceService.getPort()+";"
							+tempWabDeviceAccount.getAccount_name()+";"
							+tempWabDeviceService.getService_name()+";"
						);
			}
		}
		return listofdevices;
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void setPropertiesFile() throws IOException {
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
			
			inputStream = new FileInputStream(propFileName);
			
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
	
			Date time = new Date(System.currentTimeMillis());
	
			// get the property value and print it out
			vcenter_url = prop.getProperty("vcenter_url");
			vcenter_user = prop.getProperty("vcenter_user");
			vcenter_password = prop.getProperty("vcenter_password");
			vcenter_extract_list = prop.getProperty("vcenter_extract_list");
			wab_url_api = prop.getProperty("url_wab_api");
			wab_hostname = prop.getProperty("wab_hostname");
			wab_ssh_port = Integer.parseInt(prop.getProperty("wab_ssh_port"));
			wab_url_api_devices = prop.getProperty("url_wab_api_devices");
			wab_user_api=prop.getProperty("wab_user_api");
			wab_password_api=prop.getProperty("wab_password_api");
			wab_extract_list=prop.getProperty("wab_extract_list");
			wab_exclude_list_device=prop.getProperty("wab_exclude_list_device");
			wab_defaut_linux_service_number=Integer.parseInt(prop.getProperty("wab_defaut_linux_service_number"));
			wab_testacessdevice_list=prop.getProperty("wab_testacessdevice_list");
			wab_linux_servicename_0=prop.getProperty("wab_linux_servicename_0");
			wab_linux_service_0=prop.getProperty("wab_linux_service_0");
			wab_linux_subprotocol_0=prop.getProperty("wab_linux_subprotocol_0");
			wab_linux_account_0=prop.getProperty("wab_linux_account_0");
			wab_linux_port_0=Integer.parseInt(prop.getProperty("wab_linux_port_0"));
			wab_linux_servicename_1=prop.getProperty("wab_linux_servicename_1");
			wab_linux_service_1=prop.getProperty("wab_linux_service_1");
			wab_linux_subprotocol_1=prop.getProperty("wab_linux_subprotocol_1");
			wab_linux_account_1=prop.getProperty("wab_linux_account_1");
			wab_linux_port_1=Integer.parseInt(prop.getProperty("wab_linux_port_1"));	
			wab_defaut_windows_service_number=Integer.parseInt(prop.getProperty("wab_defaut_windows_service_number"));
			wab_windows_servicename_0=prop.getProperty("wab_windows_servicename_0");
			wab_windows_service_0=prop.getProperty("wab_windows_service_0");
			wab_windows_subprotocol_0=prop.getProperty("wab_windows_subprotocol_0");
			wab_windows_account_0=prop.getProperty("wab_windows_account_0");
			wab_windows_port_0=Integer.parseInt(prop.getProperty("wab_windows_port_0"));
			wab_windows_servicename_1=prop.getProperty("wab_windows_servicename_1");
			wab_windows_service_1=prop.getProperty("wab_windows_service_1");
			wab_windows_subprotocol_1=prop.getProperty("wab_windows_subprotocol_1");
			wab_windows_account_1=prop.getProperty("wab_windows_account_1");
			wab_windows_port_1=Integer.parseInt(prop.getProperty("wab_windows_port_1"));	
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void addDeviceToWabFromVcenter() throws IOException {
		List<String> vmList;
		VimService vimService = new VimService();
		VimPortType vimPort = vimService.getVimPort();
		RetrieveResult props=null;
		Map<String, Object> ctxt = ((BindingProvider) vimPort).getRequestContext();
		ctxt.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, vcenter_url);
		ctxt.put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
		ManagedObjectReference serviceInstance = new ManagedObjectReference();
		serviceInstance.setType("ServiceInstance");
		serviceInstance.setValue("ServiceInstance");
		try {
			try {
				DisableSecurity.trustEveryone();
			} catch (KeyManagementException e1) {
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
			ServiceContent serviceContent =
					vimPort.retrieveServiceContent(serviceInstance);
			vimPort.login(serviceContent.getSessionManager(), vcenter_user, vcenter_password,null);
			// print out the product name, server type, and product version
			System.out.println(serviceContent.getAbout().getFullName());
			System.out.println("Server type is " + serviceContent.getAbout().getApiType());
			System.out.println("API version is " + serviceContent.getAbout().getVersion());
			ManagedObjectReference viewMgrRef = serviceContent.getViewManager();
			ManagedObjectReference propColl = serviceContent.getPropertyCollector();
			vmList = new ArrayList<String>();
			vmList.add("VirtualMachine");
			ManagedObjectReference cViewRef = vimPort.createContainerView(viewMgrRef,serviceContent.getRootFolder(),vmList,true);
			ObjectSpec oSpec = new ObjectSpec();
			oSpec.setObj(cViewRef);
			oSpec.setSkip(true);
			TraversalSpec tSpec = new TraversalSpec();
			tSpec.setName("traverseEntities");
			tSpec.setPath("view");
			tSpec.setSkip(false);
			tSpec.setType("ContainerView");
			oSpec.getSelectSet().add(tSpec);
			PropertySpec pSpec = new PropertySpec();
			pSpec.setType("VirtualMachine");
			pSpec.getPathSet().add("name");
			pSpec.getPathSet().add("summary.config.instanceUuid");
			pSpec.getPathSet().add("summary.guest.guestId");
			pSpec.getPathSet().add("summary.guest.ipAddress");
			GuestInfo guestInfo = new GuestInfo();
			PropertyFilterSpec fSpec = new PropertyFilterSpec();
			fSpec.getObjectSet().add(oSpec);
			fSpec.getPropSet().add(pSpec);
			fSpec.getPropSet().add(pSpec);
			fSpec.getPropSet().add(pSpec);
			fSpec.getPropSet().add(pSpec);
			List<PropertyFilterSpec> fSpecList = new ArrayList<PropertyFilterSpec>();
			fSpecList.add(fSpec);
			RetrieveOptions ro = new RetrieveOptions();
			try {
				props = vimPort.retrievePropertiesEx(propColl,fSpecList,ro);
			} catch (InvalidPropertyFaultMsg e) {
				e.printStackTrace();
			}
			/////////////////////////////////////////////////////// 
			// go through the returned list and print out the data
			if (props != null) {
				for (ObjectContent oc : props.getObjects()) {
					List<DynamicProperty> dps = oc.getPropSet();
					String vmName = null;
					String path = null;
					String uuid = null;
					String vmOs =null;
					String valueString = null;
					String valueName = null;
					String vmIp = null;
					if (dps != null) {
						for (DynamicProperty dp : dps) {
							valueString = (String) dp.getVal();
							valueName = dp.getName();
							//response
							//name = gluster03
							//summary.config.instanceUuid = 5015003b-fb31-7eea-9457-f92933320c4c
							//summary.guest.guestId = ubuntu64Guest
							if(valueName.equals("name")) {
								//we need to delete the space it's not good for wab
								vmName=valueString.replace(" ","_");
							}
							if(valueName.equals("summary.config.instanceUuid")) {
								uuid=valueString;
							}
							if(valueName.equals("summary.guest.guestId")) {
								vmOs=valueString;
							}
							if(valueName.equals("summary.guest.ipAddress")) {
								vmIp=valueString;
							}
						}
						//deleteDevice(vmName);
						if(vmOs!=null) {
							//////////////////////////////////////////////////////
							// CREATE the object from config file and then add the info from the vcenter
							/////////////////////////////////////////////////////
							// create accounts
							System.out.println("-> Creating account for this device -"+vmName);
							List<WabDeviceAccount> deviceAccountToAdd=new LinkedList<WabDeviceAccount>();
							List<WabDeviceService> deviceServiceToAdd=new LinkedList<WabDeviceService>();
							GsonBuilder builder = new GsonBuilder();
							Gson gsonObj = builder.create();
							if(vmOs.indexOf("win")!=-1)
							{
								System.out.println("-> This is a Windows device.");
								for(int i=1;i<=wab_defaut_windows_service_number;i++) {
									//create the win service name
									WabDeviceAccount[] newAccount=new WabDeviceAccount[10];
									if(i==1) {
										//gsonObj.toJson(wab_windows_account_0);
										int j=0;
										JsonReader reader = new JsonReader(new FileReader(wab_windows_account_0));
										System.out.println(wab_windows_account_0);
										reader.beginArray();
										while (reader.hasNext()) {
											reader.beginObject();
											newAccount[j]=new WabDeviceAccount();
											while (reader.hasNext()) {
												String temp=reader.nextName();
												switch(temp.toLowerCase()) {
												case "account_name":
													String valueOfkey=reader.nextString();
													System.out.println("=>"+valueOfkey);
													newAccount[j].setAccountName(valueOfkey);
													break;
												case "description":
													newAccount[j].setDescription(reader.nextString());
													break;
												case "password":
													newAccount[j].setPassword(reader.nextString());
													break;
												case "autochange":
													newAccount[j].setAutochange(reader.nextBoolean());
													break;
												case "target_auth_type":
													newAccount[j].setTargetAuthType(reader.nextString());
													break;
												}
												System.out.println("-> doing ==>"+temp);
											}
											deviceAccountToAdd.add(newAccount[j]);
											reader.endObject();
											j++;
										}
										reader.endArray();
										reader.close();								
										//create services
										System.out.println("-> Create the service for the device now -"+vmName);
										WabDeviceService newService=new WabDeviceService();
										newService.setServiceName(wab_windows_servicename_0);
										List<String> subprotocolstoset = new LinkedList<String>();
										subprotocolstoset=Arrays.asList(wab_windows_subprotocol_0.split(","));
										//beware this is only for linux device SSH
										//newService.setFingerprint("");
										newService.setSubprotocols(subprotocolstoset);
										newService.setProtocol(wab_windows_service_0);
										newService.setPort(wab_windows_port_0);
										newService.setWabDeviceAccounts(deviceAccountToAdd);
										deviceServiceToAdd.add(newService);
									}
									if(i==2) {
										//gsonObj.toJson(wab_windows_account_0);
										int j=0;
										JsonReader reader = new JsonReader(new FileReader(wab_windows_account_1));
										System.out.println(wab_windows_account_1);
										reader.beginArray();
										while (reader.hasNext()) {
											reader.beginObject();
											newAccount[j]=new WabDeviceAccount();
											while (reader.hasNext()) {
												String temp=reader.nextName();
												switch(temp.toLowerCase()) {
												case "account_name":
													String valueOfkey=reader.nextString();
													System.out.println("=>"+valueOfkey);
													newAccount[j].setAccountName(valueOfkey);
													break;
												case "description":
													newAccount[j].setDescription(reader.nextString());
													break;
												case "password":
													newAccount[j].setPassword(reader.nextString());
													break;
												case "autochange":
													newAccount[j].setAutochange(reader.nextBoolean());
													break;
												case "target_auth_type":
													newAccount[j].setTargetAuthType(reader.nextString());
													break;
												}
												System.out.println("doing ==>"+temp);
											}
											deviceAccountToAdd.add(newAccount[j]);
											reader.endObject();
											j++;
										}
										reader.endArray();
										reader.close();								
										//create services
										WabDeviceService newService=new WabDeviceService();
										newService.setServiceName(wab_windows_servicename_1);
										List<String> subprotocolstoset = new LinkedList<String>();
										subprotocolstoset=Arrays.asList(wab_windows_subprotocol_1.split(","));
										//beware this is only for linux device SSH
										//newService.setFingerprint("");
										newService.setSubprotocols(subprotocolstoset);
										newService.setProtocol(wab_windows_service_1);
										newService.setPort(wab_windows_port_1);
										newService.setWabDeviceAccounts(deviceAccountToAdd);
										deviceServiceToAdd.add(newService);
									}

								}
							}else{
								//we add device that are not windows 
								//we create service for linux
								for(int i=1;i<=wab_defaut_linux_service_number;i++) {
									//create the win service name
									WabDeviceAccount[] newAccount=new WabDeviceAccount[10];
									if(i==1) {
										//gsonObj.toJson(wab_windows_account_0);
										int j=0;
										JsonReader reader = new JsonReader(new FileReader(wab_linux_account_0));
										System.out.println("-> Creating account for this Device "+vmName);
										System.out.println(wab_linux_account_0);
										reader.beginArray();
										while (reader.hasNext()) {
											reader.beginObject();
											newAccount[j]=new WabDeviceAccount();
											while (reader.hasNext()) {
												String temp=reader.nextName();
												switch(temp.toLowerCase()) {
												case "account_name":
													String valueOfkey=reader.nextString();
													System.out.println("=>"+valueOfkey);
													newAccount[j].setAccountName(valueOfkey);
													break;
												case "description":
													newAccount[j].setDescription(reader.nextString());
													break;
												case "password":
													newAccount[j].setPassword(reader.nextString());
													break;
												case "autochange":
													newAccount[j].setAutochange(reader.nextBoolean());
													break;
												case "target_auth_type":
													newAccount[j].setTargetAuthType(reader.nextString());
													break;
												}
												System.out.println("doing ==>"+temp);
											}
											deviceAccountToAdd.add(newAccount[j]);
											reader.endObject();
											j++;
										}
										reader.endArray();
										reader.close();								
										//create services
										WabDeviceService newService=new WabDeviceService();
										newService.setServiceName(wab_linux_servicename_0);
										List<String> subprotocolstoset = new LinkedList<String>();
										subprotocolstoset=Arrays.asList(wab_linux_subprotocol_0.split(","));
										newService.setFingerprint("");
										newService.setSubprotocols(subprotocolstoset);
										newService.setProtocol(wab_linux_service_0);
										newService.setPort(wab_linux_port_0);
										newService.setWabDeviceAccounts(deviceAccountToAdd);
										deviceServiceToAdd.add(newService);
									}
									if(i==2) {
										//gsonObj.toJson(wab_windows_account_0);
										int j=0;
										JsonReader reader = new JsonReader(new FileReader(wab_linux_account_1));
										System.out.println(wab_linux_account_1);
										reader.beginArray();
										while (reader.hasNext()) {
											reader.beginObject();
											newAccount[j]=new WabDeviceAccount();
											while (reader.hasNext()) {
												String temp=reader.nextName();
												switch(temp.toLowerCase()) {
												case "account_name":
													String valueOfkey=reader.nextString();
													System.out.println("=>"+valueOfkey);
													newAccount[j].setAccountName(valueOfkey);
													break;
												case "description":
													newAccount[j].setDescription(reader.nextString());
													break;
												case "password":
													newAccount[j].setPassword(reader.nextString());
													break;
												case "autochange":
													newAccount[j].setAutochange(reader.nextBoolean());
													break;
												case "target_auth_type":
													newAccount[j].setTargetAuthType(reader.nextString());
													break;
												}
												System.out.println("doing ==>"+temp);
											}
											deviceAccountToAdd.add(newAccount[j]);
											reader.endObject();
											j++;
										}
										reader.endArray();
										reader.close();								
										//create services
										WabDeviceService newService=new WabDeviceService();
										newService.setServiceName(wab_linux_servicename_1);
										List<String> subprotocolstoset = new LinkedList<String>();
										subprotocolstoset=Arrays.asList(wab_linux_subprotocol_1.split(","));
										newService.setFingerprint("");
										newService.setSubprotocols(subprotocolstoset);
										newService.setProtocol(wab_linux_service_1);
										newService.setPort(wab_linux_port_1);
										newService.setWabDeviceAccounts(deviceAccountToAdd);
										deviceServiceToAdd.add(newService);
									}
								}
							}						 
							if(vmOs.indexOf("win")!=-1) {
								System.out.println("adding the Windows device to wab "+vmName);
								try {
									addDevice(vmName,vmOs,"this vm "+vmName,vmIp,deviceServiceToAdd);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else{
								//we add device that are not windows 
								//we create service for linux
								System.out.println("adding the Linux device to wab "+vmName);
								try {
									addDevice(vmName,vmOs,"this vm "+vmName,vmIp,deviceServiceToAdd);
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
						//System.out.println("VM:" + vmName+ " Os:"+vmOs+" UUID:"+uuid+" Ip:"+vmIp);
					}
				}
			}
			vimPort.logout(serviceContent.getSessionManager());
		} catch (RuntimeFaultFaultMsg e) {
			e.printStackTrace();
		} catch (InvalidLocaleFaultMsg e) {
			e.printStackTrace();
		} catch (InvalidLoginFaultMsg e) {
			e.printStackTrace();
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void extractVmToFileFromVcenter() throws IOException {
		List<String> vmList;
		vmList=theVcenterServer.getExtractedVmList();
		//the filewriter to extract the vm list in csv
		FileWriter fw = new FileWriter(vcenter_extract_list);
		PrintWriter out = new PrintWriter(fw);
		for(String temp:vmList) {
			out.println(temp);
		}
		//close the filewriter for vm
		out.flush();
		out.close();
		fw.close();
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void testAccessToDevice() throws IOException {
		//////////////////////////////////////////
		// test the connection the device and extract it to a file
		// 
		List<String> vmList;
		vmList=listDevices();
		//the filewriter to extract the vm list in csv and test ssh connection
		FileWriter fw = new FileWriter(wab_testacessdevice_list);
		PrintWriter outfile = new PrintWriter(fw);
		for(String temp:vmList) {
			//extract the data and test RDP or SSH connection write down Good ok Failed
			//we will use the wab_user_api and password to test connections
			// temp is like => device_name;device_ip;protocol;subprotocol;port number;account;service_name;
			String[] elementList=temp.split(";");
			//System.out.println("=>"+temp);
			//System.out.println(" testing device "+elementList[0]);
			if(elementList[2].indexOf("SSH")!=-1) {
				//this is a linux with ssh
				//let's try a ssh connection and write the result in file.
				System.out.println(" testing SSH on device "+elementList[0]);
				String execCommand=elementList[5]+"@"+elementList[0];
				System.out.println(" execute :"+execCommand);
				Shell shellConnect=new SSHByPassword(wab_hostname, wab_ssh_port,wab_user_api, wab_password_api);
				
				String deviceTestingShell = new Shell.Plain(shellConnect).exec(execCommand);
				
				System.out.println(" result of command "+deviceTestingShell);
				// if result is =>Can't open target session 
				// so dead
				// thats good Welcome on Wallix AdminBastion
				if(deviceTestingShell.indexOf("Can\'t")!=-1) {
					outfile.println(elementList[0]+";"+elementList[5]+";FAILED;");
				}else{
					outfile.println(elementList[0]+";"+elementList[5]+";GOOD;");
				}
			}
			
			
			
			//outfile.println(temp);
		}
		//close the filewriter for vm
		outfile.flush();
		outfile.close();
		fw.close();		
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void extractDeviceFromWab() throws IOException {
		//////////////////////////////////////////
		// let's interrogate the wab appliance to get all the device / service / accounts
		// write the result in csv file 
		List<String> vmList;
		vmList=listDevices();
		//the filewriter to extract the vm list in csv
		FileWriter fw = new FileWriter(wab_extract_list);
		PrintWriter out = new PrintWriter(fw);
		for(String temp:vmList) {
			out.println(temp);
		}
		//close the filewriter for vm
		out.flush();
		out.close();
		fw.close();
	}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public static void main( String[] args ) throws IOException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException, CertificateException
    {
		if(args.length==0) {
			System.out.println("Command to be launch on WAB - wallix bastion API . V0.1 dec 2016");
			System.out.println("@ xxartemisxx - github");
			System.out.println("");
			System.out.println("all option has to be specify in config.properties");
			System.out.println("command are.");
			System.out.println("extractvm			=> extract the vm list on the csv file");
			System.out.println("pushvm				=> push vm extracted from vcenter to the wab");
			System.out.println("extractdevice			=> extract wab device to csv file 			");
			System.out.println("testdevice			=> test connectivity to each device 			");
			System.out.println("backupdevice			=> en crypted file to backup the wab device 			[ in DEV ]");
			System.out.println("deletedevice			=> delete device / service / account from a file	[ in DEV ]");
		}else{
			setPropertiesFile();
			String commandTodo=args[0];
			switch(commandTodo) {
			case "extractvm":
				//init vcenter with correct params
				theVcenterServer= new VcenterWrapper(vcenter_url,vcenter_user,vcenter_password);
				extractVmToFileFromVcenter();
				break;
			case "pushvm":
				//init vcenter with correct params
				theVcenterServer= new VcenterWrapper(vcenter_url,vcenter_user,vcenter_password);
				addDeviceToWabFromVcenter();
				break;
			case "extractdevice":
				extractDeviceFromWab();
				break;
			case "testdevice":
				testAccessToDevice();
				break;
			case "deletedevice":
				System.out.println("not functionnal at this time. sorry.");
				break;
			}
		}
    }
}

