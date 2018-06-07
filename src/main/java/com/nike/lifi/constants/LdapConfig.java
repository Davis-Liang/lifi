package com.nike.lifi.constants;

public class LdapConfig {

	private String ldapUrl;
	private String auth;
	private String domain;
	private String clazz;
	private String enable;
	private String defPass;

	public String getLdapUrl() {
		return ldapUrl;
	}

	public void setLdapUrl(String ldapUrl) {
		this.ldapUrl = ldapUrl;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getDefPass() {
		return defPass;
	}

	public void setDefPass(String defPass) {
		this.defPass = defPass;
	}

}
