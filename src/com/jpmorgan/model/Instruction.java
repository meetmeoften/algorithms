package com.jpmorgan.model;

import java.util.Currency;
import java.util.Date;

public class Instruction {

	String entity;
	Double agreedFx;
	Integer units;
	Integer pricePerUnit;
	Date instructionDate;
	Date settlementDate;
	Currency currrency;
	InstructionAction instructionAction;

	public Instruction() {
		// TODO Auto-generated constructor stub
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public Double getAgreedFx() {
		return agreedFx;
	}

	public void setAgreedFx(Double agreedFx) {
		this.agreedFx = agreedFx;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public Integer getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(Integer pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public Date getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(Date instructionDate) {
		this.instructionDate = instructionDate;
	}

	public Date getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(Date settlementDate) {
		this.settlementDate = settlementDate;
	}

	public Currency getCurrrency() {
		return currrency;
	}

	public void setCurrrency(Currency currrency) {
		this.currrency = currrency;
	}

	public InstructionAction getInstructionAction() {
		return instructionAction;
	}

	public void setInstructionAction(InstructionAction instructionAction) {
		this.instructionAction = instructionAction;
	}

}
