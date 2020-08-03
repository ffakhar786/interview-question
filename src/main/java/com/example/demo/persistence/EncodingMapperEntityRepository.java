package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncodingMapperEntityRepository extends JpaRepository<EncodingMapperEntity, Integer>{
	public List<EncodingMapperEntity> findByHashcode(Long hashcode);
}
