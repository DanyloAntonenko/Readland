package com.nure.readland.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private Integer userId;
	private Integer bookId;
	private String text;
	private Integer mark;
}
