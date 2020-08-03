package com.example.demo.persistence;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TBL_URL_ENCODED")
public class UrlEncodedEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="URL_ENCODED")
	private String urlEncoded;
	
	@Column(name="URL_HASHCODE")
	private Long urlHashcode;
	
	@Column(name="URL_ORIGINAL")
	private String urlOriginal;
	
	@Column(name="CREATED_DATE")
	private Timestamp createdDatetime;
	
}
