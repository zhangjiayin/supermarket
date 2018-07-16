package ${dao.strPackage};

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.linkwee.core.datatable.DataTable;
import com.linkwee.core.generic.GenericDao;
import ${entity.fullName};

 /**
 * 
 * @描述： Dao接口
 * 
 * @创建人： ${generate.author}
 * 
 * @创建时间：${generate.createDate}
 * 
 * Copyright (c) 深圳领会科技有限公司-版权所有
 */
public interface ${dao.shortName} extends GenericDao<${entity.shortName},Long>{
	
	 /**
     * 封装DataTable对象查询
     * @param dt
     * @param page
     * @return
     */
	List<${entity.shortName}> selectBySearchInfo(@Param("dt")DataTable dt,RowBounds page);
}
