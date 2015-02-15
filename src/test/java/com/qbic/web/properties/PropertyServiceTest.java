package com.qbic.web.properties;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import egovframework.rte.fdl.property.EgovPropertyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/resource/spring/context-common.xml", 
		"file:src/main/resource/spring/context-datasource.xml", 
		"file:src/main/resource/spring/context-idgen.xml", 
		"file:src/main/resource/spring/context-pagination.xml", 
		"file:src/main/resource/spring/context-properties.xml", 
		//"file:src/main/resource/spring/context-servlet.xml", 
		"file:src/main/resource/spring/context-sqlmap.xml"})

public class PropertyServiceTest {
	@Resource(name="propertyService")
	protected EgovPropertyService propertyService ;
	 
	@Test
	public void nothing(){}
		
	@Test
	public void testPropertiesService() throws Exception {
	   assertEquals("/app/specimen_new/webapp/upload/tmp",propertyService.getString("Globals.tmp.fileStorePath"));
	}
}
