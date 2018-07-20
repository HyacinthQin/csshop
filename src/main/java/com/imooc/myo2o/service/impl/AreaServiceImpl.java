package com.imooc.myo2o.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.myo2o.cache.JedisUtil;
import com.imooc.myo2o.dao.AreaDao;
import com.imooc.myo2o.dto.AreaExecution;
import com.imooc.myo2o.entity.Area;
import com.imooc.myo2o.service.AreaService;
@Service
public class AreaServiceImpl implements AreaService{
	@Autowired
	private AreaDao areaDao;
	
	@Autowired
	private JedisUtil.Keys jedisKeys;
	
	@Autowired
	private JedisUtil.Strings jedisStrings;
	
	
	private static Logger logger=LoggerFactory.getLogger(AreaServiceImpl.class);
	
	@Override
	@Transactional
	public List<Area> getAreaList() throws JsonParseException,
			JsonMappingException, IOException {
		String key= AREALISTKEY;
		List<Area> areaList=null;
		ObjectMapper mapper=new ObjectMapper();
		if(!jedisKeys.exists(key)){
			areaList=areaDao.queryArea();
			String jsonString;
			try {
				jsonString = mapper.writeValueAsString(areaList);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				throw new RuntimeException();
			}
			
			jedisStrings.set(key, jsonString);
		}else{
			String jsonString=jedisStrings.get(key);
			JavaType javaType=mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
			try {
				areaList=mapper.readValue(jsonString, javaType);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException();
			}
			
		}
		
		return areaDao.queryArea();
	}

	@Override
	public AreaExecution addArea(Area area) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AreaExecution modifyArea(Area area) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AreaExecution removeArea(long areaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AreaExecution removeAreaList(List<Long> areaIdList) {
		// TODO Auto-generated method stub
		return null;
	}

}
