package eu.innovtech.WabVcenterPlugin;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.ws.BindingProvider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.vmware.vim25.DynamicProperty;
import com.vmware.vim25.GuestInfo;
import com.vmware.vim25.InvalidLocaleFaultMsg;
import com.vmware.vim25.InvalidLoginFaultMsg;
import com.vmware.vim25.InvalidPropertyFaultMsg;
import com.vmware.vim25.ManagedObjectReference;
import com.vmware.vim25.ObjectContent;
import com.vmware.vim25.ObjectSpec;
import com.vmware.vim25.PropertyFilterSpec;
import com.vmware.vim25.PropertySpec;
import com.vmware.vim25.RetrieveOptions;
import com.vmware.vim25.RetrieveResult;
import com.vmware.vim25.RuntimeFaultFaultMsg;
import com.vmware.vim25.ServiceContent;
import com.vmware.vim25.TraversalSpec;
import com.vmware.vim25.VimPortType;
import com.vmware.vim25.VimService;

public class VcenterWrapper {	
/////////////////////////////////////////////////////////////////////////////////////////////////
	public List<String> extractedVmList;
	public String vcenter_url;
	public String vcenter_user;
	public String vcenter_password;
/////////////////////////////////////////////////////////////////////////////////////////////////
	public VcenterWrapper() {
		extractedVmList= new ArrayList<String>();
		extractedVmList.add("vmName;vmOs;uuid;vmIp");
		vcenter_url="";
		vcenter_user="";
		vcenter_password="";
	}
	public VcenterWrapper(String vcenterurl,String vcenteruser,String vcenterpassword) {
		extractedVmList= new ArrayList<String>();
		extractedVmList.add("vmName;vmOs;uuid;vmIp");
		vcenter_url=vcenterurl;
		vcenter_user=vcenteruser;
		vcenter_password=vcenterpassword;
		//go and extract the vm list
		extractVcenterVmList();
	}
	public List<String> getExtractedVmList() {
		return extractedVmList;
	}
	public void setExtractedVmList(List<String> extractedVmList) {
		this.extractedVmList = extractedVmList;
	}
	public String getVcenter_password() {
		return vcenter_password;
	}
	public void setVcenter_password(String vcenter_password) {
		this.vcenter_password = vcenter_password;
	}
	public String getVcenter_url() {
		return vcenter_url;
	}

	public void setVcenter_url(String vcenter_url) {
		this.vcenter_url = vcenter_url;
	}
	public String getVcenter_user() {
		return vcenter_user;
	}
	public void setVcenter_user(String vcenter_user) {
		this.vcenter_user = vcenter_user;
	}
/////////////////////////////////////////////////////////////////////////////////////////////////
	public void extractVcenterVmList() {
		////////////////////////////////:
		// have to create a stuff to check if there no connection available. for today we say it's always working ...
        List<String> vmList = null;
        //this code have been found on vmware sdk documentation.
        VimService vimService = new VimService();
        VimPortType vimPort = vimService.getVimPort();
        RetrieveResult props=null;
        Map<String, Object> ctxt = ((BindingProvider) vimPort).getRequestContext();
        ctxt.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, vcenter_url);
        ctxt.put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
        ManagedObjectReference serviceInstance = new ManagedObjectReference();
        serviceInstance.setType("ServiceInstance");
        serviceInstance.setValue("ServiceInstance");
        //have to correct this.
        try {
        	try {
				DisableSecurity.trustEveryone();
			} catch (KeyManagementException e1) {
				e1.printStackTrace();
			} catch (NoSuchAlgorithmException e1) {
				e1.printStackTrace();
			}
            ServiceContent serviceContent = vimPort.retrieveServiceContent(serviceInstance);
            vimPort.login(serviceContent.getSessionManager(), vcenter_user, vcenter_password,null);
            // print out the product name, server type, and product version
            //System.out.println(serviceContent.getAbout().getFullName());
            //System.out.println("Server type is " + serviceContent.getAbout().getApiType());
            //System.out.println("API version is " + serviceContent.getAbout().getVersion());
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
			//adding the suff we want to retrieve
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
						 //System.out.println("VM:" + vmName+ " Os:"+vmOs+" UUID:"+uuid+" Ip:"+vmIp);
						 // add the vm to the list
						 extractedVmList.add(vmName+";"+vmOs+";"+uuid+";"+vmIp+";");
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
/////////////////////////////////////////////////////////////////////////////////////////////////
	public List<String> getVmSpecs(String vmName) {
		List<String> osFound=null;
		if(extractedVmList==null) {
			getExtractedVmList();
		}
		//now return the os for this vm.
		List<String> matches = extractedVmList.stream().filter(it -> it.contains(vmName)).collect(Collectors.toList());
		
		return matches;
	}
}
