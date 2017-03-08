package com.assignment.apriori.model;

/**
 * @author bharatjain
 * @machine Mac OS Sierra (10.12.3)
 */
public class RuleModel {

	private float supportCount;
	private float confidence;
	private String lhs;
	private String rhs;
	
	
	public float getSupportCount() {
		return supportCount;
	}
	public void setSupportCount(float supportCount) {
		this.supportCount = supportCount;
	}
	public float getConfidence() {
		return confidence;
	}
	public void setConfidence(float confidence) {
		this.confidence = confidence;
	}
	public String getLhs() {
		return lhs;
	}
	public void setLhs(String lhs) {
		this.lhs = lhs;
	}
	public String getRhs() {
		return rhs;
	}
	public void setRhs(String rhs) {
		this.rhs = rhs;
	}
}
