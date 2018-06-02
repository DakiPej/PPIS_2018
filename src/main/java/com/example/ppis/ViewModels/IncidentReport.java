package com.example.ppis.ViewModels;

public class IncidentReport {
	
	private int createdNumber ; 
	private int closedNumber ; 
	private int notWorkingOnNumber ; 
	private int workingOnNumber ; 
	private int escalatedNumber ; 
	private int notEscalatedNumber ; 
	private int resolvedNumber ; 
	private int notResolvedNumber ;
	
	public IncidentReport()	{}
	
	public IncidentReport(int createdNumber
			, int closedNumber
			, int notWorkingOnNumber
			, int workingOnNumber 
			, int escalatedNumber
			, int notEscalatedNumber
			, int resolvedNumber
			, int notResolvedNumber)	{
		this.createdNumber = createdNumber ; 
		this.closedNumber = closedNumber ; 
		this.notWorkingOnNumber = notWorkingOnNumber ; 
		this.workingOnNumber = workingOnNumber ; 
		this.escalatedNumber = escalatedNumber ; 
		this.notEscalatedNumber = notEscalatedNumber ;
		this.resolvedNumber = resolvedNumber ; 
		this.notResolvedNumber = notResolvedNumber ; 
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

	public int getNotEscalatedNumber() {
		return notEscalatedNumber;
	}

	public void setNotEscalatedNumber(int notEscalatedNumber) {
		this.notEscalatedNumber = notEscalatedNumber;
	}

	public int getResolvedNumber() {
		return resolvedNumber;
	}

	public void setResolvedNumber(int resolvedNumber) {
		this.resolvedNumber = resolvedNumber;
	}

	public int getNotResolvedNumber() {
		return notResolvedNumber;
	}

	public void setNotResolvedNumber(int notResolvedNumber) {
		this.notResolvedNumber = notResolvedNumber;
	}
	
}
