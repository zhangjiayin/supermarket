package com.linkwee.web.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.linkwee.api.request.act.SocksCollectHelpRequest;
import com.linkwee.core.generic.GenericDao;
import com.linkwee.core.generic.GenericServiceImpl;
import com.linkwee.web.dao.ActChristmasSocksDetailMapper;
import com.linkwee.web.model.ActChristmasSocks;
import com.linkwee.web.model.ActChristmasSocksDetail;
import com.linkwee.web.service.ActChristmasSocksDetailService;
import com.linkwee.web.service.ActChristmasSocksService;


 /**
 * 
 * @描述：ActChristmasSocksDetailService 服务实现类
 * 
 * @创建人： Mignet
 * 
 * @创建时间：2017年11月20日 13:54:37
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
@Service("actChristmasSocksDetailService")
public class ActChristmasSocksDetailServiceImpl extends GenericServiceImpl<ActChristmasSocksDetail, Long> implements ActChristmasSocksDetailService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ActChristmasSocksDetailServiceImpl.class);
	
	@Resource
	private ActChristmasSocksDetailMapper actChristmasSocksDetailMapper;
	@Resource
	private ActChristmasSocksService actChristmasSocksService;
	
	@Override
    public GenericDao<ActChristmasSocksDetail, Long> getDao() {
        return actChristmasSocksDetailMapper;
    }

	@Override
	public List<ActChristmasSocksDetail> queryHelpRecord(String userId) {
		return actChristmasSocksDetailMapper.queryHelpRecord(userId);
	}

	@Override
	public int help(String userId, SocksCollectHelpRequest req) throws Exception{
		ActChristmasSocksDetail socksDetail = new ActChristmasSocksDetail();
		socksDetail.setUserId(userId);
		socksDetail.setOpenid(req.getOpenid());
		socksDetail = selectOne(socksDetail);
		if(socksDetail != null) {
			LOGGER.info("每个微信账号只能给同一个用户助力一次,userId={},req={}",userId,JSON.toJSONString(req));
			throw new Exception("only_help_one");
		}
		socksDetail = new ActChristmasSocksDetail();
		ActChristmasSocks actChristmasSocks = new ActChristmasSocks();
		actChristmasSocks.setUserId(userId);
		actChristmasSocks = actChristmasSocksService.selectOne(actChristmasSocks);
		int socksNum = 0;
		if(actChristmasSocks != null){
			//TODO 生成袜子数量
			socksNum = generateSockNum(actChristmasSocks.getHelpCount(),actChristmasSocks.getSocksNum());
			actChristmasSocks.setHelpCount(actChristmasSocks.getHelpCount()+1);
			actChristmasSocks.setSocksNum(actChristmasSocks.getSocksNum() + socksNum);
			actChristmasSocks.setLastUpdateTime(new Date());
			actChristmasSocksService.update(actChristmasSocks);
			socksDetail.setSocksNum(socksNum);
		}else{
			throw new Exception("用户记录不存在，数据异常");
		}	
		socksDetail.setCreateTime(new Date());
		socksDetail.setUserId(userId);
		socksDetail.setOpenid(req.getOpenid());
		socksDetail.setWeixinIcoUrl(req.getWeixinIcoUrl());
		socksDetail.setWeixinNickname(req.getWeixinNickname());
		insert(socksDetail);
		return socksNum;
	}
	
	private int generateSockNum(int helpCount,int sockNum){
		int[] array = {-1,0,1,2};
		Set<Integer> hasFindSet = new HashSet<Integer>();
		if(helpCount == 4){
			return 6 - sockNum;
		}else if(helpCount == 9){
			return 9 - sockNum;
		}else if(helpCount == 29){
			return 29 - sockNum;
		}else if(helpCount == 59){
			return 49 - sockNum;
		}else if(helpCount == 79){
			return 69 - sockNum;
		}else if(helpCount == 129){
			return 99 - sockNum;
		}else if(helpCount == 249){
			return 199 - sockNum;
		}else if(helpCount == 0){
			return 1;
		}else if(helpCount > 0 && helpCount < 4){
			while(true){
				int index = randomnum(0, 3);
				hasFindSet.add(index);
				int sum = sockNum + array[index];
				if(sum > 0 && sum < 6){
					return array[index];
				}
				if(hasFindSet.size() == 4){
					return 0;
				}
			}			
		}else if(helpCount > 4 && helpCount < 9){
			while(true){
				int index = randomnum(0, 3);
				hasFindSet.add(index);
				int sum = sockNum + array[index];
				if(sum > 6 && sum < 9){
					return array[index];
				}
				if(hasFindSet.size() == 4){
					return 0;
				}
			}
		}else if(helpCount > 9 && helpCount < 29){
			while(true){
				int index = randomnum(0, 3);
				hasFindSet.add(index);
				int sum = sockNum + array[index];
				if(sum > 9 && sum < 29){
					return array[index];
				}
				if(hasFindSet.size() == 4){
					return 0;
				}
			}
		}else if(helpCount > 29 && helpCount < 59){
			while(true){
				int index = randomnum(0, 3);
				hasFindSet.add(index);
				int sum = sockNum + array[index];
				if(sum > 29 && sum < 49){
					return array[index];
				}
				if(hasFindSet.size() == 4){
					return 0;
				}
			}
		}else if(helpCount > 59 && helpCount < 79){
			while(true){
				int index = randomnum(0, 3);
				hasFindSet.add(index);
				int sum = sockNum + array[index];
				if(sum > 49 && sum < 69){
					return array[index];
				}
				if(hasFindSet.size() == 4){
					return 0;
				}
			}
		}else if(helpCount > 79 && helpCount < 129){
			while(true){
				int index = randomnum(0, 3);
				hasFindSet.add(index);
				int sum = sockNum + array[index];
				if(sum > 69 && sum < 99){
					return array[index];
				}
				if(hasFindSet.size() == 4){
					return 0;
				}
			}
		}else if(helpCount > 129 && helpCount < 249){
			while(true){
				int index = randomnum(0, 3);
				hasFindSet.add(index);
				int sum = sockNum + array[index];
				if(sum > 99 && sum < 199){
					return array[index];
				}
				if(hasFindSet.size() == 4){
					return 0;
				}
			}
		}else{
			while(true){
				int index = randomnum(0, 3);
				hasFindSet.add(index);
				int sum = sockNum + array[index];
				if(sum < 1990){
					return array[index];
				}
				if(hasFindSet.size() == 4){
					return 0;
				}
			}
		}
	}
	
	// 获取2个值之间的随机数
    private static int randomnum(int smin, int smax){
        int range = smax - smin;
        double rand = Math.random();
        return (int) (smin + Math.round(rand * range));
    }

}
