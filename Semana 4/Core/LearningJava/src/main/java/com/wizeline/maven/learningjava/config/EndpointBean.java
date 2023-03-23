package com.wizeline.maven.learningjava.config;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Component
@Validated
@ConfigurationProperties(prefix = "consumers")
public class EndpointBean implements Serializable {

	private static final long serialVersionUID = -7673847978264607965L;

	@NotBlank(message = "Field is mandatory")	
    @NotEmpty
	private String enpointLogin;

	@NotBlank(message = "Field is mandatory")	
    @NotEmpty
	private String createuser;

	@NotBlank(message = "Field is mandatory")	
    @NotEmpty
	private String createusers;

	@NotBlank(message = "Field is mandatory")	
    @NotEmpty
	private String userAccount;

	@NotBlank(message = "Field is mandatory")	
    @NotEmpty
	private String accounts;

	@NotBlank(message = "Field is mandatory")	
    @NotEmpty
	private String accountsGroupByType;

	public String getEnpointLogin() {
		return enpointLogin;
	}

	public void setEnpointLogin(String enpointLogin) {
		this.enpointLogin = enpointLogin;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}

	public String getCreateusers() {
		return createusers;
	}

	public void setCreateusers(String createusers) {
		this.createusers = createusers;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getAccounts() {
		return accounts;
	}

	public void setAccounts(String accounts) {
		this.accounts = accounts;
	}

	public String getAccountsGroupByType() {
		return accountsGroupByType;
	}

	public void setAccountsGroupByType(String accountsGroupByType) {
		this.accountsGroupByType = accountsGroupByType;
	}
	
}