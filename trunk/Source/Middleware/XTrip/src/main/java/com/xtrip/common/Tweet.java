package com.xtrip.common;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author longnh
 */
public class Tweet {
	private int numTweet;
	private String id;
	private String text;
	private int intScore;
	private double realScore;
	private Map<String, List<Double>> p_of_term_given_score;
	private Map<String, List<Double>> p_of_score_given_term;

	public Tweet() {
		numTweet = 0;
		id = "";
		text = "";
		intScore = 0;
		realScore = 0;
		p_of_term_given_score = new LinkedHashMap<String, List<Double>>();
		p_of_score_given_term = new LinkedHashMap<String, List<Double>>();
	}

	public int getNumTweet() {
		return this.numTweet;
	}

	public void setNumTweet(int numTweet) {
		this.numTweet = numTweet;
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getIntScore() {
		return this.intScore;
	}

	public void setIntScore(int intScore) {
		this.intScore = intScore;
	}

	public double getRealScore() {
		return this.realScore;
	}

	public void setRealScore(double realScore) {
		this.realScore = realScore;
	}
	
	public Map<String, List<Double>> getP_of_term_given_score() {
		return this.p_of_term_given_score;
	}

	public void setP_of_term_given_score(Map<String, List<Double>> p_of_term_given_score) {
		this.p_of_term_given_score = p_of_term_given_score;
	}

	public Map<String, List<Double>> getP_of_score_given_term() {
		return this.p_of_score_given_term;
	}

	public void setP_of_score_given_term(Map<String, List<Double>> p_of_score_given_term) {
		this.p_of_score_given_term = p_of_score_given_term;
	}
}
