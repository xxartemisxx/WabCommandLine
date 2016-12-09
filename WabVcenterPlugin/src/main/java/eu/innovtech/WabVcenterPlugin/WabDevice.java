package eu.innovtech.WabVcenterPlugin;
import java.util.LinkedList;
import java.util.List;

import eu.innovtech.WabVcenterPlugin.WabDeviceService;


public class WabDevice {
	 public String device_name;
	 public String alias;
	 public String description;
	 public String device_host;
     public List<WabDeviceService> services;
     public void WabDevice() {
    	 device_name="";
    	 description="";
    	 alias="";
    	 device_host="";
         services=new LinkedList<WabDeviceService>();
     }
     public void setDeviceName(String deviceN){
    	 device_name=deviceN;
     }
     public void setDescription(String descriptionS){
    	 description=descriptionS;
     }
     public void setAlias(String aliasS){
    	 alias=aliasS;
     }
     public void setDeviceHost(String deviceHostS){
    	 device_host=deviceHostS;
     }
     
}
