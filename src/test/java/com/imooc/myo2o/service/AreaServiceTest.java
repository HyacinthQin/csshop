package com.imooc.myo2o.service;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.imooc.myo2o.BaseTest;
import com.imooc.myo2o.entity.Area;

public class AreaServiceTest extends BaseTest{
	@Autowired
	private AreaService areaService;
	@Test
	public void testGetAreaList() throws JsonParseException, JsonMappingException, IOException{
		List<Area> areaList=areaService.getAreaList();
		System.out.println(areaList);
		
		
	}
}
