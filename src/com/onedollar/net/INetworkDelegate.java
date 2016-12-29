package com.onedollar.net;

public interface INetworkDelegate {

	public void didStartLoad();

	public void didFinishedLoad(Object resposeResult);

	public void didFailureLoad(String errorMessage);

	public void didLoading(String progress);
}
