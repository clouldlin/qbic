package co.kr.qbic.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import co.kr.qbic.main.service.MainService;

@Service("mainService")
public class MainServiceImpl implements MainService {

	@Resource(name="mainDAO")
	MainDAO mainDAO;
	
	@Override
	public List selectPosAll() throws Exception
	{
		return mainDAO.selectPosAll();
	}
	
	@Override
	public List selectPosPhobon() throws Exception
	{
		return mainDAO.selectPosPhobon();
	}
}
