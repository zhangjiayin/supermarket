package com.linkwee.product.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.core.base.ServiceResponse;
import com.linkwee.product.dao.ProductInfoRuleDao;
import com.linkwee.product.dao.ProductRuleDao;
import com.linkwee.product.dao.ProductTypeDao;
import com.linkwee.product.service.ProductRuleService;
import com.linkwee.web.model.User;
import com.linkwee.web.model.product.ProductInfoRule;
import com.linkwee.web.model.product.ProductRule;
import com.linkwee.web.model.product.ProductType;

@Service("productRuleService")
public class ProductRuleServiceImpl implements ProductRuleService {
	private static Logger logger = LoggerFactory.getLogger(ProductRuleServiceImpl.class);

	@Autowired
	private ProductInfoRuleDao productInfoRuleDao;
	
	@Autowired
	private ProductRuleDao productRuleDao;

	@Autowired
	private ProductTypeDao productTypeDao;

	@Transactional("productTransactionManager")
	public ServiceResponse<String> addProfitModel(List<ProductRule> list,String productTypeName){
	
		StringBuffer logs = new StringBuffer();
		logs.append("|请求产品参数：").append(list);
		logger.info(logs.toString());
		try {
			
			User authUserInfo = getLoginUserInfo();
			ProductType t = new ProductType();
	    	t.setCreator(authUserInfo.getUsername());
	    	t.setCreateTime(new Date());
			t.setTypeName(productTypeName);
			t.setTypeDesc(productTypeName);
			t.setBusinessType((byte)2);//默认值
			t.setManageWay((byte)1);
			t.setCompriseContentWay((byte)1);
			t.setIsAuto((byte)1);//是否自动派息(1=自动|2=手动|3=用户赎回)
			t.setTypeValue(5);//产品类型(1=天添牛|2=指数牛|3=活期宝 | 4= 惠房宝|5=日益宝)
			t.setDelStatus(0);//1=正常|2=已删除 使用与注解不统一
			productTypeDao.add(t);
			
			Integer typeId = t.getId();
			List<ProductInfoRule> infoRuleList = new ArrayList<ProductInfoRule>();
			
			for(ProductRule item:list){
				StringBuffer ruleName = new StringBuffer();
				ruleName.append(item.getLineMinValue()).append("~").append(item.getLineMaxValue()).append("天的规则");
				item.setFeeRate(new BigDecimal(0));
				item.setTRuleName(ruleName.toString());
				item.setRuleDescription(ruleName.toString());
				item.setRuleDisabled("0");//设置为可用
				productRuleDao.add(item);
				ProductInfoRule infoRule = new ProductInfoRule();
				infoRule.setTypeId(typeId);
				infoRule.setTRuleId(item.getTRuleId());
				StringBuffer desc = new StringBuffer();
				desc.append(item.getLineMinValue()).append("~").append(item.getLineMaxValue());
				infoRule.setDescription(desc.toString());
				infoRuleList.add(infoRule);
			}
			productInfoRuleDao.addBatch(infoRuleList);
		
			logger.info("收益模板添加成功");
			return new ServiceResponse<String>("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ServiceResponse<String>("添加失败");
		}
	}
	
	public User getLoginUserInfo() {
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	User authUserInfo = (User)session.getAttribute("userInfo");
		return authUserInfo;
	}
	
}
