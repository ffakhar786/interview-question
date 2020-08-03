package com.example.demo.service;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.persistence.UrlEncodedEntity;
import com.example.demo.persistence.UrlEncodedEntityRepository;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
/** Represents a QueryString URL Service.
 * @author Fakhar Mumtaz
 * @version 1.0
 * @since 1.0
 */
@Service
public class QueryStringService {
	Logger logger = LogManager.getLogger(QueryStringService.class);
	private BiMap<String, String> keyBiMap = HashBiMap.create();
	private BiMap<String, String> encodingBiMapper = HashBiMap.create();

	/*
	 * @Autowired private EncodingMapperEntityRepository
	 * encodingMapperEntityRepository;
	 */
	@Autowired
	private UrlEncodedEntityRepository urlEncodedEntityRepository;

	public QueryStringService() {
		keyBiMap.put("A01", "A");
		keyBiMap.put("A02", "B");
		keyBiMap.put("A03", "5");
		keyBiMap.put("A04", "C");
		keyBiMap.put("A05", "6");
		keyBiMap.put("A06", "D");
		keyBiMap.put("A07", "2");
		keyBiMap.put("A08", "F");
		keyBiMap.put("A09", "X");
		keyBiMap.put("A10", "G");
		keyBiMap.put("A11", "Y");
		keyBiMap.put("A12", "8");
		keyBiMap.put("A13", "Z");
		keyBiMap.put("A14", "W");
		keyBiMap.put("A15", "U");
		keyBiMap.put("A16", "P");
	}

	public String encodeUrlBase64(String url) {
		String tiny = "please check url";
		Encoder encoder = Base64.getEncoder();
		if (url != null && url.length() > 0) {
			tiny = encoder.encodeToString(url.getBytes());
			UrlEncodedEntity obj = new UrlEncodedEntity(); 
			obj.setUrlEncoded(tiny);
			obj.setUrlHashcode(new Long(url.hashCode())); 
			obj.setUrlOriginal(url);
			obj.setCreatedDatetime(new Timestamp(new Date().getTime()));

			urlEncodedEntityRepository.save(obj);
		}
		return tiny;
	}

	public String encodeQueryStringUrl(String url) {
		encodingBiMapper = HashBiMap.create();
		encodeUrl(url);
		Set<String> keyS = encodingBiMapper.keySet();
		String tiny = "";
		for (String s : keyS) {
			String tmp = keyBiMap.get(s);
			tiny += tmp;
			/*
			 * EncodingMapperEntity entity = new EncodingMapperEntity(); entity.setKey(s);
			 * entity.setValue(tmp); entity.setHashcode(new Long(url.hashCode()));
			 * encodingMapperEntityRepository.save(entity);
			 */
		}
		logger.trace(tiny);

		UrlEncodedEntity obj = new UrlEncodedEntity(); 
		obj.setUrlEncoded(tiny);
		obj.setUrlHashcode(new Long(url.hashCode())); 
		obj.setUrlOriginal(url);
		obj.setCreatedDatetime(new Timestamp(new Date().getTime()));

		urlEncodedEntityRepository.save(obj);
		return tiny;
	}

	private void encodeUrl(String url) {
		if (url != null && url.length() > 0)
			url = url.toLowerCase();
		else
			return;
		String[] arr = url.split("/");
		String key = "";
		int i = 1;
		for (String s : arr) {
			key = "A" + (i <= 9 ? "0" + i : i);
			s = s + (s == null || "".equals(s) ? "/" : "/");
			if (i == arr.length) {
				// remove '/' from last token
				s = s.substring(0, s.length() - 1);
			}
			encodingBiMapper.put(key, s);
			logger.trace("key=" + key + ", " + s);
			i++;
		}
	}

	public String decodeQueryStringUrl(String tiny) {
		String sBuilder = "";
		if (encodingBiMapper != null && encodingBiMapper.size() > 0) {
			char[] carr = tiny.toUpperCase().toCharArray();
			for (char c : carr) {
				String s = Character.toString(c);
				String key = keyBiMap.inverse().get(s);
				String token = encodingBiMapper.get(key);
				sBuilder += token;
			}

			logger.trace(sBuilder);

		} else {
			// please fetch from DB now
			if (tiny != null && tiny.length() > 0) {
				UrlEncodedEntity dbObj = urlEncodedEntityRepository.findByUrlEncoded(tiny);
				if (dbObj != null)
					sBuilder = dbObj.getUrlOriginal();
			}
		}
		return sBuilder;
	}


	public String decodeUrlBase64(String tiny) {
		String url = "tiny Url is null";
		Decoder decoder = Base64.getDecoder();
		if(tiny!=null && tiny.length()>0) {
			byte[] decBytes = decoder.decode(tiny);
			url = new String(decBytes);
		}
		return url;
	}

	public Integer runScheduler() {
		logger.info("Scheduler is running to delete the older records... ");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -30);

		java.sql.Date thiryMinutesBefore = new java.sql.Date(cal.getTimeInMillis());

		Integer numOfRecords = urlEncodedEntityRepository.removeOlderThan(thiryMinutesBefore);
		return numOfRecords;
	}

}
