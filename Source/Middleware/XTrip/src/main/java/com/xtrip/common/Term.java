package com.xtrip.common;

import java.util.ArrayList;
import java.util.List;

/**
 * @author longnh
 */
public class Term {
	private String word;
	private List<Integer> heads;
	private List<String> histogram;
	private List<String> p_of_term_given_score;
	private List<String> p_of_score_given_term;

	public Term() {
		word = "";
		heads = new ArrayList<Integer>();
		histogram = new ArrayList<String>();
		p_of_term_given_score = new ArrayList<String>();
		p_of_score_given_term = new ArrayList<String>();
	}

	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<Integer> getHeads() {
		return this.heads;
	}

	public void setHeads(List<Integer> heads) {
		this.heads = heads;
	}
	
	public List<String> getHistogram() {
		return this.histogram;
	}

	public void setHistogram(List<String> histogram) {
		this.histogram = histogram;
	}

	public List<String> getP_of_term_given_score() {
		return this.p_of_term_given_score;
	}

	public void setP_of_term_given_score(List<String> p_of_term_given_score) {
		this.p_of_term_given_score = p_of_term_given_score;
	}

	public List<String> getP_of_score_given_term() {
		return this.p_of_score_given_term;
	}

	public void setP_of_score_given_term(List<String> p_of_score_given_term) {
		this.p_of_score_given_term = p_of_score_given_term;
	}
}
