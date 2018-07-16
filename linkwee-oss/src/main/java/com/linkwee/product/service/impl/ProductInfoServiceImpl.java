package com.linkwee.product.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.linkwee.core.base.ErrorCodeSupport;
import com.linkwee.core.base.ServiceResponse;
import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.datatable.DataTableReturn;
import com.linkwee.core.orm.paging.Page;
import com.linkwee.core.util.StringUtils;
import com.linkwee.product.dao.ProductCateDao;
import com.linkwee.product.dao.ProductInfoCateDao;
import com.linkwee.product.dao.ProductInfoDao;
import com.linkwee.product.dao.ProductInfoRuleDao;
import com.linkwee.product.dao.ProductProtocalDao;
import com.linkwee.product.dao.ProductRuleDao;
import com.linkwee.product.dao.ProductSaleRewardDao;
import com.linkwee.product.dao.ProductStatisticsDao;
import com.linkwee.product.dao.ProductTypeDao;
import com.linkwee.product.service.ProductInfoService;
import com.linkwee.web.dao.FeerateDao;
import com.linkwee.web.dao.TproductDao;
import com.linkwee.web.model.Feerate;
import com.linkwee.web.model.Tproduct;
import com.linkwee.web.model.User;
import com.linkwee.web.model.product.ProCateSortResponse;
import com.linkwee.web.model.product.ProductCate;
import com.linkwee.web.model.product.ProductInfo;
import com.linkwee.web.model.product.ProductInfoCate;
import com.linkwee.web.model.product.ProductInfoResp;
import com.linkwee.web.model.product.ProductProtocal;
import com.linkwee.web.model.product.ProductRule;
import com.linkwee.web.model.product.ProductSaleReward;
import com.linkwee.web.model.product.ProductStatistics;
import com.linkwee.web.model.product.ProductType;
import com.linkwee.web.util.BigDecimalUtil;

@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService {
	private static Logger logger = LoggerFactory.getLogger(ProductInfoServiceImpl.class);
	
	@Autowired
	private ProductCateDao productCateDao;
	@Autowired
	private ProductInfoCateDao productInfoCateDao;
	@Autowired
	private ProductInfoDao productInfoDao;
	@Autowired
	private ProductProtocalDao productProtocalDao;
	
	@Autowired
	private ProductSaleRewardDao productSaleRewardDao;
	@Autowired
	private FeerateDao feerateDao;
	@Autowired
	private TproductDao tproductDao;
	@Autowired
	private ProductStatisticsDao productStatisticsDao;
	@Autowired
	private ProductTypeDao productTypeDao;
	@Autowired
	private ProductInfoRuleDao productInfoRuleDao;
	@Autowired
	private ProductRuleDao productRuleDao;


	@Override
	@Transactional("productTransactionManager")
	public ServiceResponse<String> addProduct(ProductInfoResp infoResp) {
		String productId = UUID.randomUUID().toString();
		ProductInfo proInfo = conventToProInfo(infoResp,productId,"add");
		StringBuffer logs = new StringBuffer();
		logs.append("|请求产品参数：").append(infoResp.toString());
		logger.info(logs.toString());
		try {
			/**
			 * 添加产品类别信息 分类排序
			 */
			ProductInfoCate cateSort = new ProductInfoCate();
			cateSort.setCateId(infoResp.getProductCate());
			cateSort.setProductId(productId);
			if(infoResp.getCateSort() !=null){
			cateSort.setSort(infoResp.getCateSort());
			}
			/*ProductInfoCate tempProInfoCate = productInfoCateDao.findByCateIdAndSort(infoResp.getProductCate(), infoResp.getCateSort());
			if(tempProInfoCate!=null){//该分类下的该排序已经设置有数据 更改对应产品ID
				//productInfoCateDao.updateByCateIdAndSort(productId, infoResp.getProductCate(), infoResp.getCateSort()); 
				if(!productId.equals(tempProInfoCate.getProductId())){
					productInfoCateDao.updateProSort(infoResp.getProductCate(),infoResp.getCateSort());
					productInfoCateDao.deleteByProAndSortAndCate(productId, infoResp.getProductCate());
					productInfoCateDao.add(cateSort);
				}
			}else{
				productInfoCateDao.deleteByProAndSortAndCate(productId, infoResp.getProductCate());
				productInfoCateDao.add(cateSort);
			}*/
			ProductInfoCate tempProInfoCate = productInfoCateDao.findByCateIdAndProId(infoResp.getProductCate(),productId);
			if(tempProInfoCate!=null){
				productInfoCateDao.updateProCateSort(cateSort);
			}else{
				productInfoCateDao.add(cateSort);
			}
			/**
			 * 列表推荐(热门推荐)
			 */
			if(infoResp.getIsListRecommended()==1){
				cateSort = null;
				cateSort = new ProductInfoCate();
				cateSort.setCateId("1000");
				cateSort.setProductId(productId);
				if(infoResp.getListRecommendedSort() !=null){
					cateSort.setSort(infoResp.getListRecommendedSort());
				}
				cateSort.setDescription(infoResp.getListRecomendedStr());//列表推荐语
				tempProInfoCate = null;
				tempProInfoCate = productInfoCateDao.findByCateIdAndProId("1000", productId);
				/*if(tempProInfoCate!=null){//该分类下的该排序已经设置有数据 更改对应产品ID
					productInfoCateDao.updateByCateIdAndSort(productId, "1000", cateSort.getSort());
				}else{
					productInfoCateDao.deleteByProAndSortAndCate(productId, "1000");
					productInfoCateDao.add(cateSort);
				}*/
				if(tempProInfoCate!=null){
					productInfoCateDao.updateProCateSort(cateSort);
				}else{
					productInfoCateDao.add(cateSort);
				}
			}else{//非热门产品推荐
				productInfoCateDao.deleteByProIdAndCateId(productId, "1000");
			}
			logger.info("产品排序信息持久化完毕");
			
		
			fillProTypeId(infoResp, proInfo);
			
			
			if(proInfo.getCollectLineMinValue()!=null ){
				proInfo.setIsFlow((byte)2);//浮动产品
				proInfo.setIsCollect((byte)3);
				
			}else{
				proInfo.setIsFlow((byte)1);//固定产品
			}
			if(proInfo.getCollectEndTime()!=null){//有募集期倒计时配置
				proInfo.setOpenType(String.valueOf(1));
				proInfo.setOpenLinkurl(open_linkUrl_collect);
				proInfo.setOpenLcsLinkurl(open_lcs_linkUrl_collect);
			}
			// 浮动收益 `f_flow_min_rate` decimal(16,2) DEFAULT NULL COMMENT '浮动最小利率', `f_flow_max_rate` decimal(16,2) DEFAULT NULL COMMENT '浮动最大利率',
			
			productInfoDao.add(proInfo);
			logger.info("产品表录入完");
			
			//佣金 (包含募集期佣金)
			Double collectRatio = infoResp.getCollectRatio() == null ? 0 :infoResp.getCollectRatio();
			feerateDao.orgFeeRatio(productId,infoResp.getFeeRatio(),collectRatio);
			//销售奖励
			ProductSaleReward saleReward = new ProductSaleReward();
			saleReward.setProductId(productId);
			saleReward.setSaleReward("投资额的百分比");
			saleReward.setRewardCode("PERCENTAGE");
			saleReward.setIsReward("Y");
			saleReward.setRewardRatio(infoResp.getSaleReward());
			productSaleRewardDao.add(saleReward);
			logger.info("产品佣金信息持久化完毕");
			/**
			 * 初始化产品销售统计信息
			 */
			ProductStatistics proStat = new ProductStatistics();
			proStat.setProductId(productId);
			proStat.setRemaMoney(proInfo.getBuyTotalMoney());
			proStat.setBuyedTotalMoney(new BigDecimal(0));
			proStat.setCreateTime(new Date());
			productStatisticsDao.add(proStat);
			//复制到channel库
			tproductDao.add(conventToTproduct(proInfo,"add"));
			logger.info("产品信息同步到channel库");
			return new ServiceResponse<String>("添加成功");
		} catch (Exception e) {
			e.printStackTrace();			
			return new ServiceResponse<String>("添加失败",new ErrorCodeSupport(-1,"添加失败"));
		}
		
	}

	public void fillProTypeId(ProductInfoResp infoResp, ProductInfo proInfo) {
		if(infoResp.getProductTypeId()!=null){
			proInfo.setProductTypeId(infoResp.getProductTypeId());
			//设置产品利率浮动区间
			List<Map<String,Object>> listMapRate = productInfoRuleDao.queryFloatRate(infoResp.getProductTypeId());
			if(listMapRate!=null && listMapRate.size()>0 ){
				Map<String,Object> minAndMaxRate = listMapRate.get(0);
				if(minAndMaxRate !=null){
					if( minAndMaxRate.get("minRate")!=null){
						proInfo.setFlowMinRate((BigDecimal)minAndMaxRate.get("minRate"));
					}
					if(minAndMaxRate.get("maxRate")!=null){
						proInfo.setFlowMaxRate((BigDecimal)minAndMaxRate.get("maxRate"));
					}
				}
				
				
			}
		}else{
			if(infoResp.getCollectEndTime()!=null){//有募集期 产品typevalue必须为 5(1=天添牛|2=指数牛|3=活期宝 | 4= 惠房宝|5=日益宝)
				proInfo.setProductTypeId(productTypeDao.queryFloatTypeId());
			}else{//没有募集期固收产品
				proInfo.setProductTypeId(5);//默认值
			}
			
		}
	}
	
	private ProductInfo conventToProInfo(ProductInfoResp infoResp,String productId,String opType) {
		ProductInfo proInfo = new ProductInfo();
		////需要默认设置
		proInfo.setIsShow((byte)2);
		proInfo.setStatus((byte)2);
		proInfo.setIsQuota(1);//目前都为限额产品
		//创建人
    	User authUserInfo = getLoginUserInfo();
    	proInfo.setCreator(authUserInfo.getUsername());
		if("add".equals(opType)){
		proInfo.setGreyFlag(1);//产品灰度 新增是默认启动的
    	proInfo.setCreator(authUserInfo.getUsername());
    	proInfo.setCreateTime(new Date());
		}else{
			proInfo.setUpdater(authUserInfo.getUsername());
			proInfo.setUpdateTime(new Date());
		}
		proInfo.setProductId(productId);
		proInfo.setProductName(infoResp.getProductName());
		if(infoResp.getBuyTotalMoney()!=null){
			proInfo.setBuyTotalMoney(new BigDecimal(infoResp.getBuyTotalMoney()));//产品总额
		}
		if(infoResp.getBuyMinMoney()!=null){
		proInfo.setBuyMinMoney(new BigDecimal(infoResp.getBuyMinMoney()));//起投金额
		}
		if(infoResp.getBuyIncreaseMoney()!=null){
		proInfo.setBuyIncreaseMoney(new BigDecimal(infoResp.getBuyIncreaseMoney()));//递增金额
		}
		if(infoResp.getCustBuyMaxMoney() != null){
		proInfo.setCustBuyMaxMoney(new BigDecimal(infoResp.getCustBuyMaxMoney()));//用户购买上限
		}
		proInfo.setProductIllustrationUrl(infoResp.getProductIllustrationUrl());//产品说明页面
		proInfo.setSecurityGuaranteeUrl(infoResp.getSecurityGuaranteeUrl());//产品安全保障页
	
		if(infoResp.getIsRecommended()!=null){
			proInfo.setIsRecommended((byte)infoResp.getIsRecommended());//首页推荐
			if(infoResp.getIsRecommended()==1){//只能一个是首页推荐
				productInfoDao.updateIndexRecomended();
			}
		}else{
			proInfo.setIsRecommended((byte)2);//首页推荐
		}
		
		if(infoResp.getFixRate() !=null){
		proInfo.setFixRate(new BigDecimal(infoResp.getFixRate()));//年化收益
		}
		//proInfo.setLcsCornerIco(infoResp.getLcsCornerIco());//理财师端角标
		//proInfo.setInvCornerIco(infoResp.getInvCornerIco());//金服端角标
		
		proInfo.setDeadLineValue(infoResp.getDeadLineValue());//投资期限
		proInfo.setDeadLineType((byte)1);//默认为天
		if(!"edit".equals(opType)){
			if(infoResp.getValidBeginDate()!=null){//指定日期起息
				proInfo.setInterestWay((byte)5);//起息方式
			}else{
				proInfo.setInterestWay((byte)2);//T+1
			}
			proInfo.setInterestWay(infoResp.getInterestWay());//起息方式
		}
		
		proInfo.setRepaymentWay(infoResp.getRepaymentWay());
		if(StringUtils.isNotBlank(infoResp.getListRecomendedStr())){
		proInfo.setProductDesc(infoResp.getListRecomendedStr());//App列表推荐语
		}
		
		
		//查找产品购买协议ID
		if(StringUtils.isNotBlank(infoResp.getProductProtocalId())){
			proInfo.setProductProtocalId(infoResp.getProductProtocalId());//产品购买协议地址
		}else{
			Integer protocalId = productProtocalDao.findNullNameProtocal();
			proInfo.setProductProtocalId(protocalId ==null ? String.valueOf(515) : String.valueOf(protocalId));//产品购买协议地址
		}
		if(StringUtils.isNotBlank(infoResp.getRansferProtocalName())){
			proInfo.setRansferProtocalName(infoResp.getRansferProtocalName());
			proInfo.setRansferProtocalUrl(infoResp.getRansferProtocalUrl());
		}
		
		StringBuffer invLabel = new StringBuffer();
		//标签 金服
		if(StringUtils.isNotBlank(infoResp.getInvLabel1())){
			invLabel.append(infoResp.getInvLabel1());
		}
		if(StringUtils.isNotBlank(infoResp.getInvLabel2())){
			invLabel.append(",").append(infoResp.getInvLabel2());
		}
		proInfo.setInvLabel(invLabel.toString());
		//理财师
		StringBuffer lcsLabel = new StringBuffer();
		if(StringUtils.isNotBlank(infoResp.getLcsLabel1())){
			lcsLabel.append(infoResp.getLcsLabel1());
		}
		if(StringUtils.isNotBlank(infoResp.getLcsLabel2())){
			lcsLabel.append(",").append(infoResp.getLcsLabel2());
		}
		proInfo.setLcsLabel(lcsLabel.toString());
		//proInfo.setInvDtlPageDes(infoResp.getInvDtlPageDes());
		//proInfo.setLcsDtlPageDes(infoResp.getLcsDtlPageDes());
		//募集信息
		if(infoResp.getProductCate()!=null && "1001".equals(infoResp.getProductCate()) ){
			proInfo.setIsCollect((byte)2);
		}else{//浮动产品为3
			proInfo.setIsCollect((byte)3);
		}
		proInfo.setIsCollect(infoResp.getIsCollect());
		
		if(infoResp.getCollectEndTime()!=null){//募集有效期到
			proInfo.setCollectEndTime(infoResp.getCollectEndTime());
		}
		if(infoResp.getValidBeginDate()!=null){//产品起息日期
			proInfo.setValidBeginDate(infoResp.getValidBeginDate());
			proInfo.setInterestWay((byte)5);
		}
		if(infoResp.getCollectRate() !=null && infoResp.getHasCollectRate()==1){//募集期收益
			proInfo.setCollectRate(new BigDecimal(infoResp.getCollectRate()));
		}else{
			proInfo.setCollectRate(new BigDecimal(0));
		}
		//募集期佣金存入tfeeRate表中 collecte_ratio
			
			
		if(infoResp.getCollectLineMinValue()!=null){
			proInfo.setCollectLineMinValue(infoResp.getCollectLineMinValue());
		}
		if(infoResp.getCollectLineMaxValue()!=null){
			proInfo.setCollectLineMaxValue(infoResp.getCollectLineMaxValue());
		}
		
		//产品销售状态
		proInfo.setBeginSaleType(Byte.valueOf(String.valueOf(infoResp.getBeginSaleType())));
		switch(infoResp.getBeginSaleType())
		{
		//即时  在售
		case 1: proInfo.setSaleStatus("2");
		if(infoResp.getCollectEndTime()!=null){//带募集期产品
			proInfo.setSaleStatus("6");
		}
				break;
		//定时  
		case 2: 
			proInfo.setSaleStatus("5");
			proInfo.setBeginSaleTime(infoResp.getBeginSaleTime());
			break;
		//预售
		case 3: 
			proInfo.setSaleStatus("1");
			proInfo.setBeginSaleTime(infoResp.getBeginSaleTime());
			proInfo.setOpenType("1");
			if(infoResp.getCollectEndTime()==null){//如果是固定不募集 开售为预售的产品
			proInfo.setOpenLinkurl(open_linkUrl);
			proInfo.setBuySucceedReturnurl(buy_succeed_returnUrl);
			proInfo.setOpenLcsLinkurl(open_lcs_linkUrl);
			}
			break;
		
		default:;
		}
		//固收产品固定日期起息 
		if(proInfo.getDeadLineValue()!=null && proInfo.getValidBeginDate()!=null){
			proInfo.setValidEndDate(DateUtils.addDays(proInfo.getValidBeginDate(), proInfo.getDeadLineValue()));
		}
		//设定产品下架时间
		if(infoResp.getEndSaleTime() != null){
			proInfo.setEndSaleTime(infoResp.getEndSaleTime());
		}
		return proInfo;
	}

	public User getLoginUserInfo() {
		Subject currentUser = SecurityUtils.getSubject();
    	Session session = currentUser.getSession();
    	User authUserInfo = (User)session.getAttribute("userInfo");
		return authUserInfo;
	}

	public ProductInfoResp findProDetail(String productId){
		ProductInfoResp infoResp = new ProductInfoResp();
		 infoResp = productInfoDao.getByProductId(productId);
		//标签分解显示
		String temp = infoResp.getLcsLable();
		if(StringUtils.isNotBlank(temp) ){
			if(temp.contains(",")){
			infoResp.setLcsLabel1(temp.substring(0, temp.indexOf(",")));
			infoResp.setLcsLabel2(temp.substring(temp.indexOf(",")+1));
			}else{
				infoResp.setLcsLabel1(temp);
			}
		}
		temp = infoResp.getInvLable();
		if(StringUtils.isNotBlank(temp)){
			if(temp.contains(",")){
			infoResp.setInvLabel1(temp.substring(0, temp.indexOf(",")));
			infoResp.setInvLabel2(temp.substring(temp.indexOf(",")+1));
			}else{
				infoResp.setInvLabel1(temp);
			}
		}
		//分类排序信息
		ProductInfoCate condit = new ProductInfoCate();
		condit.setProductId(productId);
		List<ProCateSortResponse> proCateList = productInfoCateDao.findProCateSort(productId);
		boolean isListRecomented = false;
		for(ProCateSortResponse item:proCateList){
			if(item.getIsPublic() == 0){//产品固有类型
				infoResp.setCateSort(item.getSort());
				infoResp.setProductCate(item.getCateId());
			}
			if(item.getIsPublic() ==1 && item.getCateId().equals("1000")){//热门产品推荐
				isListRecomented = true;
				infoResp.setIsListRecommended((byte) 1);
				infoResp.setListRecommendedSort(item.getSort());
				if(StringUtils.isBlank(infoResp.getListRecomendedStr())){
				infoResp.setListRecomendedStr(item.getDescription());
				}
			}
		}
		if(StringUtils.isBlank(infoResp.getProductCate())){
			infoResp.setProductCate("1001");//为空则默认为固定收益产品
		}
		if(!isListRecomented){
			infoResp.setIsListRecommended((byte) 0);
		}
		//销售奖励
		ProductSaleReward proSaleReward = productSaleRewardDao.findfeeRaioByProId(productId);
		if(proSaleReward!=null){
			infoResp.setSaleReward(proSaleReward.getRewardRatio());
		}
		//销售佣金
		Feerate feeratCondit = new Feerate();
		feeratCondit.setOrgnumber("99999999");
		feeratCondit.setProductnumber(productId);
		List<Feerate> feeRatioList = feerateDao.list(feeratCondit);
		if(feeRatioList!=null && feeRatioList.size()>0){
			infoResp.setFeeRatio(feeRatioList.get(0).getFeeratio());
			infoResp.setCollectRatio(feeRatioList.get(0).getCollectRatio());
		}
		//产品购买协议地址
		ProductProtocal proProtocal = productProtocalDao.getByPrimaryKey(infoResp.getProductProtocalId());
		if(proProtocal!=null){
			//infoResp.setProductProtocalId(proProtocal.getProtocalFileUrl());
			infoResp.setProductProtocalName(proProtocal.getProtocalName());
		}
		return infoResp;
	}

	/**
	 * 所有产品固有属性
	 * @return
	 */
	public List<ProductCate> queryAllPublicCates() {
		ProductCate condit = new ProductCate();
		condit.setIspublic(0);
		return  productCateDao.list(condit);
		
	}


	@Override
	public ServiceResponse<String> releasePro(String productId) {
		ProductInfoResp proInfoResp = productInfoDao.getByProductId(productId);
		int beginSaleType = proInfoResp.getBeginSaleType();
		ProductInfo proInfo = new ProductInfo();
		proInfo.setProductId(productId);
		proInfo.setGreyFlag(0);
		if(proInfoResp.getCollectEndTime()!=null){//发布后需募集的产品 状态为募集中
			proInfo.setSaleStatus(String.valueOf(6));
		}
		switch(beginSaleType){
		case 1://即时
			proInfo.setBeginSaleTime(new Date());
			break;
		case 2://定时
			break;
		case 3://预售
			break;
		}
		try {
			//对比channel库和产品库的数据
			Tproduct tproduct = tproductDao.getByFid(productId);
			if(tproduct==null){
				return new ServiceResponse<String>("channel库产品表tproduct信息录入失败！");
			}
			String pattern = "yyyy-MM-dd";
			if(proInfoResp.getDeadLineValue()!=null && tproduct.getDeadLineValue()!=null && proInfoResp.getDeadLineValue().intValue()!=tproduct.getDeadLineValue().intValue()){
				return new ServiceResponse<String>("产品期限不统一，发布失败");
			}
			if(proInfoResp.getCollectEndTime()!=null && tproduct.getCollectEndTime()!=null && 
					!com.linkwee.web.util.DateUtils.format(proInfoResp.getCollectEndTime(), pattern).equals(com.linkwee.web.util.DateUtils.format(tproduct.getCollectEndTime(), pattern)) ){
				return new ServiceResponse<String>("募集截止时间不统一，发布失败");
			}
			
			if(proInfoResp.getValidBeginDate()!=null && tproduct.getValidBeginDate()!=null && 
					!com.linkwee.web.util.DateUtils.format(proInfoResp.getValidBeginDate(), pattern).equals(com.linkwee.web.util.DateUtils.format(tproduct.getValidBeginDate(), pattern)) ){
				return new ServiceResponse<String>("起息时间不统一，发布失败");
			}
			if(proInfoResp.getValidEndDate()!=null && tproduct.getValidEndDate()!=null && 
					!proInfoResp.getValidEndDate().equals(com.linkwee.web.util.DateUtils.format(tproduct.getValidEndDate(), com.linkwee.web.util.DateUtils.FORMAT_FULL)) ){
				return new ServiceResponse<String>("起息时间不统一，发布失败");
			}
			
			if(proInfoResp.getInterestWay()!=null && tproduct.getInterestWay()!=null && !tproduct.getInterestWay().equals(String.valueOf(proInfoResp.getInterestWay())) ){
				return new ServiceResponse<String>("产品起息方式不统一，发布失败");
			}
			
			if(proInfoResp.getFixRate()!=null && tproduct.getFixRate()!=null && proInfoResp.getFixRate().intValue()!=tproduct.getFixRate().intValue()){
				return new ServiceResponse<String>("产品固定利率不统一，发布失败");
			}
			if(proInfoResp.getCollectRate()!=null && tproduct.getCollectRate()!=null && proInfoResp.getCollectRate().intValue()!=tproduct.getCollectRate().intValue()){
				return new ServiceResponse<String>("产品募集期利率不统一，发布失败");
			}
			productInfoDao.update(proInfo);
			
			return new ServiceResponse<String>("发布成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ServiceResponse<String>("发布失败");
		}
	}


	@Override
	public ServiceResponse<String> onOrOffPro(String productId,int opType) {
		
		ProductInfo proInfo = new ProductInfo();
		proInfo.setProductId(productId);
		StringBuffer msg = new StringBuffer();
		try {
			switch(opType){
			case 1:
				//上架
				//产品是否已经售罄 剩余额度是否大于0 如果售罄 再上架 产品状态任然为售罄
				Double remainMoney = productStatisticsDao.queryProRemaining(productId);
				if(remainMoney!=null && remainMoney.intValue()>=1){
					proInfo.setSaleStatus("2");
				}
				proInfo.setGreyFlag(0);
				msg.append("上架");
				break;
			case 2:
				//下架
				proInfo.setSaleStatus("4");
				msg.append("下架");
				break;
				default : break;
			}
			
			productInfoDao.update(proInfo);
			msg.append("成功");
			
		} catch (Exception e) {
			e.printStackTrace();
			msg.append("失败");
		}
		return new ServiceResponse<String>(msg.toString());
	}


	@Override
	public ServiceResponse<String> updateProduct(ProductInfoResp infoResp,String opType) {
		
		String productId = infoResp.getProductId();
		if(StringUtils.isBlank(productId)){
			return new ServiceResponse<String>("参数错误");
		}
		ProductInfo proInfo = conventToProInfo(infoResp,productId,opType);
		
		try {
			/**
			 * 修改产品类别信息 分类排序
			 */
			ProductInfoCate cateSort = new ProductInfoCate();
			cateSort.setCateId(infoResp.getProductCate());
			cateSort.setProductId(productId);
			if(infoResp.getCateSort() !=null){
			cateSort.setSort(infoResp.getCateSort());
			}
			/*ProductInfoCate tempProInfoCate = productInfoCateDao.findByCateIdAndSort(infoResp.getProductCate(), infoResp.getCateSort());
			if(tempProInfoCate!=null){//该分类下的该排序已经设置有数据 更改对应产品ID
				if(!productId.equals(tempProInfoCate.getProductId())){
				//productInfoCateDao.updateByCateIdAndSort(productId, infoResp.getProductCate(), infoResp.getCateSort());
				productInfoCateDao.updateProSort(infoResp.getProductCate(),infoResp.getCateSort());
				productInfoCateDao.deleteByProAndSortAndCate(productId, infoResp.getProductCate());
				productInfoCateDao.add(cateSort);
				}
				
			}else{
				productInfoCateDao.deleteByProAndSortAndCate(productId, infoResp.getProductCate());
				productInfoCateDao.add(cateSort);
			}*/
			ProductInfoCate tempProInfoCate = productInfoCateDao.findByCateIdAndProId(infoResp.getProductCate(),productId);
			if(tempProInfoCate!=null){
				productInfoCateDao.updateProCateSort(cateSort);
			}else{
				productInfoCateDao.add(cateSort);
			}
			/**
			 * 更新列表推荐(热门推荐)
			 */
			if(infoResp.getIsListRecommended()==1){
				cateSort = null;
				cateSort = new ProductInfoCate();
				cateSort.setCateId("1000");
				cateSort.setProductId(productId);
				if(infoResp.getListRecommendedSort() !=null){
					cateSort.setSort(infoResp.getListRecommendedSort());
				}
				cateSort.setDescription(infoResp.getListRecomendedStr());//列表推荐语
				proInfo.setProductDesc(infoResp.getListRecomendedStr());
				tempProInfoCate = null;
				tempProInfoCate = productInfoCateDao.findByCateIdAndProId("1000", productId);
				/*if(tempProInfoCate!=null){//该分类下的该排序已经设置有数据 更改对应产品ID
					productInfoCateDao.updateByCateIdAndSort(productId, "1000", cateSort.getSort());
				}else{
					productInfoCateDao.deleteByProAndSortAndCate(productId, "1000");
					productInfoCateDao.add(cateSort);
				}*/
				if(tempProInfoCate!=null){
					productInfoCateDao.updateProCateSort(cateSort);
				}else{
					productInfoCateDao.add(cateSort);
				}
			}else{//非热门产品推荐
				productInfoCateDao.deleteByProIdAndCateId(productId, "1000");
				proInfo.setProductDesc("");
			}
			logger.info("产品排序信息持久化完毕");
			
			
			//修改收益模板
			fillProTypeId(infoResp, proInfo);
			
			productInfoDao.update(proInfo);
			if(StringUtils.isBlank(proInfo.getRansferProtocalName())){
			productInfoDao.updateProProtocal(proInfo);//修改产品协议
			}
			logger.info("产品表更新完");	
			
			//更改channel库产品镜像表
			tproductDao.update(conventToTproduct(proInfo,"update"));
			
			//佣金 更新这个产品关联的所有佣金信息
			if(infoResp.getFeeRatio()!=null){
				Feerate feerate = new Feerate();
				feerate.setProductnumber(productId);
				feerate.setFeeratio(infoResp.getFeeRatio());
				Double collectRatio = null;
				if(infoResp.getCollectRatio() != null){
					collectRatio = infoResp.getCollectRatio();
				}else{
					collectRatio = new Double(0);
				}
				feerate.setCollectRatio(collectRatio);	
				if(StringUtils.isNotBlank(feerateDao.isExistByProId(productId))){//没有对应记录则新增
					feerateDao.update(feerate);
				}else{
					feerateDao.orgFeeRatio(productId, infoResp.getFeeRatio(),collectRatio);
				}
			}
			
			//销售奖励
			if(infoResp.getSaleReward()!=null){
				ProductSaleReward saleReward = new ProductSaleReward();
				saleReward.setProductId(productId);
				saleReward.setRewardRatio(infoResp.getSaleReward());
				ProductSaleReward existProSaleReward = productSaleRewardDao.findfeeRaioByProId(productId);
				if(existProSaleReward!=null){//有设置销售奖励
					if(infoResp.getSaleReward()!=null){
					productSaleRewardDao.update(saleReward);
					}else{
						//不设置销售奖励 删除数据
						productSaleRewardDao.deleteByProId(productId);
					}
				}else{
					if(infoResp.getSaleReward()!=null){
						saleReward.setSaleReward("投资额的百分比");
						saleReward.setRewardCode("PERCENTAGE");
						saleReward.setIsReward("Y");
						productSaleRewardDao.add(saleReward);
					}
				}
			}
			
			logger.info("产品佣金信息持久化完毕");
			//更新 channel库产品信息
			tproductDao.updateByFid(conventToTproduct(proInfo,"update"));
			//更改产品统计信息  如果修改已经在售的产品需要查询已经销售的额度 buyedAmount
			ProductStatistics saleStat = productStatisticsDao.queryBuyedAmountByProId(productId);
			ProductStatistics t = new ProductStatistics();
			BigDecimal buyedAmoutBigDecimal =  new BigDecimal(0);
			if(saleStat!=null && saleStat.getBuyedTotalMoney() != null){
				buyedAmoutBigDecimal = saleStat.getBuyedTotalMoney();
				t.setBuyedTotalPeople(saleStat.getBuyedTotalPeople());
			}
			t.setProductId(productId);
			t.setBuyedTotalMoney(buyedAmoutBigDecimal);
			if(proInfo.getBuyTotalMoney()==null){
				proInfo.setBuyTotalMoney(new BigDecimal(productInfoDao.queryBuyTotalByProId(productId)));
			}
			if(buyedAmoutBigDecimal.intValue() > 0 ){
				t.setRemaMoney(BigDecimalUtil.subtract(proInfo.getBuyTotalMoney(), buyedAmoutBigDecimal));
			}else{
				t.setRemaMoney(proInfo.getBuyTotalMoney());
			}
			t.setUpdateTime(new Date());
			productStatisticsDao.update(t);
			return new ServiceResponse<String>("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new ServiceResponse<String>("修改失败");
		}
	}


	@Override
	public ServiceResponse<String> editProduct(ProductInfoResp infoResp) {
		
		return null;
	}

	@Override
	public Boolean isExistProName(String proName) {
		Boolean rlt = false;
		Integer proId = productInfoDao.findByProName(proName);
		if(proId!=null){
			rlt = true;
		}
		return rlt;
	}
	
	private Tproduct conventToTproduct(ProductInfo proInfo,String type) {
		Tproduct tproduct = new Tproduct();
		BeanUtils.copyProperties(proInfo, tproduct);
		if("add".equals(type)){
		tproduct.setId(tproductDao.getTproductId());
		}
		tproduct.setProductName(proInfo.getProductName());
		tproduct.setProductDesc(proInfo.getProductDesc());
		tproduct.setProductTypeId(String.valueOf(proInfo.getProductTypeId()));
		tproduct.setDeadLineType(proInfo.getDeadLineType());
		tproduct.setDeadLineValue(proInfo.getDeadLineValue());
		tproduct.setFid(proInfo.getProductId());
		tproduct.setFdelstatus((byte)0);
		tproduct.setFcreatetime(proInfo.getCreateTime());
		tproduct.setFupdatetime(proInfo.getUpdateTime());
		tproduct.setCollectBeginTime(proInfo.getCollectBeginTime());
		tproduct.setCollectEndTime(proInfo.getCollectEndTime());
		if(proInfo.getInterestWay() != null){
			tproduct.setInterestWay(String.valueOf(proInfo.getInterestWay()));
		}
		
		tproduct.setValidBeginDate(proInfo.getValidBeginDate());
		tproduct.setValidEndDate(proInfo.getValidEndDate());
		tproduct.setFixRate(proInfo.getFixRate());
		tproduct.setFlowMinRate(proInfo.getFlowMinRate());
		tproduct.setFlowMaxRate(proInfo.getFlowMaxRate());
		if(proInfo.getIsFlow()!=null){
		tproduct.setIsFlow(String.valueOf(proInfo.getIsFlow()));
		}
	
		tproduct.setCollectLineMinValue(proInfo.getCollectLineMinValue());
		tproduct.setCollectLineMaxValue(proInfo.getCollectLineMaxValue());
		tproduct.setCollectRate(proInfo.getCollectRate());
		tproduct.setSaleStatus(proInfo.getSaleStatus());
		//根据typeId 获取typeValue
		if(proInfo.getProductTypeId()!=null){
		ProductType productType = productTypeDao.getByPrimaryKey(proInfo.getProductTypeId());
			if(productType!=null && productType.getTypeValue()!=null && productType.getTypeValue().intValue()==5){//即将汇款按此条件过滤 非浮动产品时 此字段值 为空
			tproduct.setTypeValue(productType.getTypeValue());
			}
		}
		if(proInfo.getFixRate()!=null){
			tproduct.setIsFlow(String.valueOf(1));//默认为固定利率
		}else{
			tproduct.setIsFlow(String.valueOf(2));//浮动利率
		}
		tproduct.setFbiznumber("0001");//设置为默认值
		
		return tproduct;
	}

	@Override
	public ProductInfoResp getByProductId(String productId) {
		return productInfoDao.getByProductId(productId);
	}

	@Override
	public DataTableReturn queryDataTableProductList(DataTable dt) {
		DataTableReturn tableReturn = new DataTableReturn();
		tableReturn.setDraw(dt.getDraw()+1);
		Page<ProductInfo> page = new Page<ProductInfo>(dt.getStart()/dt.getLength()+1,dt.getLength());
		List<ProductInfo> list = productInfoDao.queryDataTableProductList(dt,page);
		tableReturn.setData(list);
		tableReturn.setRecordsFiltered(page.getTotalCount());
		tableReturn.setRecordsTotal(page.getTotalCount());
		return tableReturn;
	}
	
		@Override
	public List<ProductType> queryAllProTypes() {
		ProductType condit = new ProductType();
		condit.setDelStatus(0);//正常状态
		condit.setManageWay((byte)1);
		condit.setTypeValue(5);//只取有募集期的类型
		return  productTypeDao.list(condit);
	}

		@Override
		public Map<String,Object> queryProDaysByTypeId(Integer productTypeId) {
			List<Map<String,Object>> listMapRate = productInfoRuleDao.queryFloatDays(productTypeId);
			if(listMapRate!=null && listMapRate.size()>0){
				 return  listMapRate.get(0);
				
			}else{
				return null;
			}
		}

		@Override
		public List<ProductRule> queryProRuleDteByTypeId(Integer productTypeId) {
			return productRuleDao.queryModeDtlByTypeId(productTypeId);
		}

		@Override
		public List<ProductProtocal> queryAllHasNamesProtocals() {
			return productProtocalDao.findProtocals();
		}
		
		
		@Override
		public ServiceResponse<String> setOver(String productId) {
			
			StringBuffer msg = new StringBuffer();
			try {
				User authUserInfo = getLoginUserInfo();
				Integer showIndex = productInfoDao.queryMinShowIndexByProId(productId);
				productInfoDao.setOver(productId,authUserInfo.getUsername(),showIndex-1);
				msg.append("成功");
				
			} catch (Exception e) {
				e.printStackTrace();
				msg.append("失败");
			}
			return new ServiceResponse<String>(msg.toString());
		}
		

		@Override
		public ServiceResponse<Boolean> proAutoOff() {
			boolean initProductStatus = true;
			try{
				//查询所有待更新的产品数据
				List<ProductInfo> productList = productInfoDao.queryProductStatusList();
				//产品销售状态:1-预售、2-在售、3-售罄、4-下架、5-定时发售、6-募集中、7-募集失败-8募集成功
				for(ProductInfo product:productList){
					if(product.getSaleStatus().equals("1")){
						//预售产品，更新状态
						Integer dateResult= com.linkwee.core.util.DateUtils.compareDate(new Date(), product.getBeginSaleTime());
						if(product.getBeginSaleTime() !=null && dateResult != null && dateResult != -1){
							logger.info("当前时间>>>"+com.linkwee.core.util.DateUtils.format(new Date()));
							//更新channel
							Tproduct tproduct = new Tproduct();
							tproduct.setFid(product.getProductId());
							tproduct.setSaleStatus("2");
							int row = tproductDao.updateByFid(tproduct);
							//当前时间等于或者大于预售时间,更新状态为在售
							if(row > 0){
								ProductInfo proUpdate=new ProductInfo();
								proUpdate.setProductId(product.getProductId());
								proUpdate.setSaleStatus("2");
								productInfoDao.updateStatusInfo(proUpdate);
							}else{
								logger.warn("渠道库tproduct表数据录入失败");
							}
							
							
						}
					}
					if(product.getSaleStatus().equals("5")){
						//定时发售产品，更新状态
						Integer dateResult=com.linkwee.core.util.DateUtils.compareDate(new Date(), product.getBeginSaleTime());
						if(product.getBeginSaleTime() !=null && dateResult != null && dateResult != -1){
							logger.info("当前时间>>>"+com.linkwee.core.util.DateUtils.format(new Date()));
							//更新channel
							Tproduct tproduct = new Tproduct();
							tproduct.setFid(product.getProductId());
							String saleStatus = "2";
							if(product.getCollectEndTime()!=null){//有募集截止时间 是有募集期的产品  定时的 募集产品到指定时间应该将状态设置为 募集中 ：6
								saleStatus = "6";
							}
							tproduct.setSaleStatus(saleStatus);
							int row = tproductDao.updateByFid(tproduct);
							if(row > 0){
								ProductInfo productListResp=new ProductInfo();
								productListResp.setProductId(product.getProductId());
								productListResp.setGreyFlag(0);//灰度关闭
								productListResp.setSaleStatus(saleStatus);
								productInfoDao.updateStatusInfo(productListResp);
							}else{
								logger.warn("渠道库tproduct表数据录入失败");
							}
						}
					}
					if(product.getSaleStatus().equals("2")){
						//在售产品如果设置了 定时下架时间 到时间定时下架
						Integer dateResult=com.linkwee.core.util.DateUtils.compareDate(new Date(), product.getEndSaleTime());
						if(product.getEndSaleTime()!= null && dateResult != null && dateResult != -1){
							/*logger.info("当前时间>>>"+com.linkwee.core.util.DateUtils.format(new Date()));
							//当前时间等于或者小于定时发售时间,更新状态为在售
							ProductInfo productListResp=new ProductInfo();
							productListResp.setProductId(product.getProductId());
							productListResp.setSaleStatus("4");
							
							productInfoDao.updateStatusInfo(productListResp);
							//更新channel
							Tproduct tproduct = new Tproduct();
							tproduct.setFid(product.getProductId());
							tproduct.setSaleStatus(productListResp.getSaleStatus());
							tproductDao.updateByFid(tproduct);*/							
							Integer showIndex = productInfoDao.queryMinShowIndexByProId(product.getProductId());
							productInfoDao.setOver(product.getProductId(),"系统定时器",showIndex-1);
							//更新channel
							Tproduct tproduct = new Tproduct();
							tproduct.setFid(product.getProductId());
							tproduct.setSaleStatus("3");
							tproductDao.updateByFid(tproduct);	
						}
					}
				}
			}catch(Exception e){
				logger.info("更新状态异常");
				initProductStatus=false;
				e.printStackTrace();
			}
			logger.info("更新产品状态结束<<<");
			return new ServiceResponse<Boolean>(initProductStatus);
		}


	
}
