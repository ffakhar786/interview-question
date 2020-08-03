package com.example.demo.persistence;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UrlEncodedEntityRepository extends JpaRepository<UrlEncodedEntity, Integer>{
	public UrlEncodedEntity findByUrlEncoded(String urlEncoded);
	@Modifying
    @Transactional
    @Query("DELETE FROM UrlEncodedEntity m WHERE m.createdDatetime < :minutes")
    Integer removeOlderThan(@Param("minutes") Date date);
}
