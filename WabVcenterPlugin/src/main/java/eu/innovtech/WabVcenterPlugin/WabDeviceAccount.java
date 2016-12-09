package eu.innovtech.WabVcenterPlugin;

public class WabDeviceAccount {
	public String account_name="";
	public String description="";
	public String password="secret";
	public Boolean autochange=false;
	public String target_auth_type="local";
	public void WabDeviceAccount(String ac,String desc,String pass,boolean au,String targ) {
		account_name=ac;
		description=desc;
		password=pass;
		autochange=au;
		target_auth_type=targ;
	}
	public void setAccountName(String accountN) {
		account_name=accountN;
	}
	public void setDescription(String descriptionS) {
		description=descriptionS;
	}
	public void setPassword(String passwordS) {
		password=passwordS;
	}
	public void setAutochange(boolean autochangeB) {
		autochange=autochangeB;
	}
	public void setTargetAuthType(String targetauthtype) {
		target_auth_type=targetauthtype;
	}
	public void importFromJson(String jsonString) {
		
	}
	
}
