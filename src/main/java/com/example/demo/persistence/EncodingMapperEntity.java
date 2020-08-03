package com.example.demo.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name="TBL_ENCODING_MAPPER")
public class EncodingMapperEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="URL_TOKEN_KEY")
	private String key;
	@Column(name="URL_TOKEN_VALUE")
	private String value;
	@Column(name="URL_HASHCODE")
	private Long hashcode;
}
