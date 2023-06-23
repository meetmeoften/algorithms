package com.lld.movieticket.account;

import com.lld.chess.AccountStatus;

public abstract class Account {

	private String id;
	private String password;
	private AccountStatus status;

	public boolean resetPassword;

}
