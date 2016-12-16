package eu.innovtech.WabVcenterPlugin;
import java.util.LinkedList;
import java.util.List;

import eu.innovtech.WabVcenterPlugin.WabDeviceAccount;

public class WabDeviceService {
	public String service_name;
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String protocol;
    public String getProtocol() {
		return protocol;
	}
	public int port;
	public int getPort() {
		return port;
	}
	public List<String> subprotocols;
	public List<String> getSubprotocols() {
		return subprotocols;
	}
	public String fingerprint;
    public List<WabDeviceAccount> accounts;
    public List<WabDeviceAccount> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<WabDeviceAccount> accounts) {
		this.accounts = accounts;
	}
	public void WabDeviceService() {
    	service_name="";
    	protocol="SSH";
    	fingerprint=null;
    	subprotocols=null;
    	port=666;
    	accounts=new LinkedList<WabDeviceAccount>();
    }
    public void setServiceName(String serviceNameS) {
    	service_name = serviceNameS;
    }
    public void setProtocol(String serviceprotocolS) {
    	protocol = serviceprotocolS;
    }
    public void setPort(int portS) {
    	port = portS;
    }
    public void setFingerprint(String fingerprintS) {
    	fingerprint = fingerprintS;
    }
    public void setSubprotocols( List<String> subprotocolstoset) {
    	subprotocols=subprotocolstoset;
    }
    public void setWabDeviceAccounts(List<WabDeviceAccount> newAccounts) {
    	accounts=newAccounts;
    }
}
