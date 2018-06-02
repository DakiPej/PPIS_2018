package com.example.ppis.ViewModels;

public class RequestReport {

	private int createdNumber ; 
	private int closedNumber ; 
	private int notWorkingOnNumber ; 
	private int workingOnNumber ; 
	private int escalatedNumber ; 
	private int notEscalatedNumber ; 
	
	public RequestReport ()	{}
	
	public RequestReport(int createdNumber
							, int closedNumber
							, int notWorkingOnNumber
							, int workingOnNumber 
							, int escalatedNumber
							, int notEscalatedNumber)	{
		this.createdNumber = createdNumber ; 
		this.closedNumber = closedNumber ; 
		this.notWorkingOnNumber = notWorkingOnNumber ; 
		this.workingOnNumber = workingOnNumber ; 
		this.escalatedNumber = escalatedNumber ; 
		this.notEscalatedNumber = notEscalatedNumber ; 
	}

	public int getCreatedNumber() {
		return createdNumber;
	}

	public void setCreatedNumber(int createdNumber) {
		this.createdNumber = createdNumber;
	}

	public int getClosedNumber() {
		return closedNumber;
	}

	public void setClosedNumber(int closedNumber) {
		this.closedNumber = closedNumber;
	}

	public int getNotWorkingOnNumber() {
		return notWorkingOnNumber;
	}

	public void setNotWorkingOnNumber(int notWorkingOnNumber) {
		this.notWorkingOnNumber = notWorkingOnNumber;
	}

	public int getWorkingOnNumber() {
		return workingOnNumber;
	}

	public void setWorkingOnNumber(int workingOnNumber) {
		this.workingOnNumber = workingOnNumber;
	}

	public int getEscalatedNumber() {
		return escalatedNumber;
	}

	public void setEscalatedNumber(int escalatedNumber) {
		this.escalatedNumber = escalatedNumber;
	}
}
