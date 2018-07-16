package com.linkwee.web.dao;

import com.linkwee.web.model.CfpCustomerInvertingModel;
import com.linkwee.web.response.CfpCustomerProfitListResp;
import com.linkwee.web.response.CfpTeamListResp;
import com.linkwee.web.response.LcsDetailResp;
import com.linkwee.web.response.LcsSalesAndEarningDetailResp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by lenli on 2016/5/31.
 *理财师销售与收益相关查询
 * @Author Libin
 * @Date 2016/5/31
 */
public interface CfpRelevantProfitDao {

    /**
     * 理财师销售与收益列表查询
     * @param mobile
     * @return
     */
    public List<LcsSalesAndEarningDetailResp> findCfpSaleList(@Param("mobile") String mobile, RowBounds rowBounds);

    /**
     * 查询理财师当前正在投资的客户总的金额
     * @param mobile
     * @return
     */
    public Double findCurrentInvertorAmount(@Param("mobile") String mobile,@Param("searchText")String searchText);

    /**
     * 查询理财师当前正在投资的客户
     * @param mobile
     * @param rowBounds
     * @return
     */
    public List<CfpCustomerInvertingModel> findCurrentInvertorList(@Param("mobile") String mobile,@Param("searchText")String searchText, RowBounds rowBounds);
    public List<CfpCustomerInvertingModel> findCurrentInvertorList(@Param("mobile") String mobile);

    /**
     * 查询理财师下一级与二级理财师的信息与推荐收益情况
     */
    public List<CfpTeamListResp> queryCfpTeamList(@Param("query") LcsDetailResp lcsDetailResp,RowBounds rowBounds);

    /**
     * 查询理财师一级客户投资情况不包括非直属投资客户
     */
    public List<CfpCustomerProfitListResp> queryCfpCustomerProfitList(@Param("query") LcsDetailResp detailResp,RowBounds rowBounds);



}
