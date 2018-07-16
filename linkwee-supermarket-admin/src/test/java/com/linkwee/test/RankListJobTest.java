package com.linkwee.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.linkwee.act.rankList.service.RankListService;
@Component
public class RankListJobTest extends TestSupport{
	
	@Autowired
	private RankListService rankListService;

	@Test
	public void test() {
		start();
		rankListService.updateRanklistVirtualData();
		end();
	}

}
