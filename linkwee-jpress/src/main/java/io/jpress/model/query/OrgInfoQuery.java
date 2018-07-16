/**
 * Copyright (c) 2015-2016, Michael Yang 杨福海 (fuhai999@gmail.com).
 *
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/lgpl-3.0.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jpress.model.query;

import io.jpress.core.db.Jdb;
import io.jpress.model.OrgInfo;
import io.jpress.model.Platform;
import io.jpress.model.vo.Archive;
import io.jpress.template.TemplateManager;
import io.jpress.utils.StringUtils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.ehcache.IDataLoader;

public class OrgInfoQuery extends JBaseQuery {

	protected static final OrgInfo DAO = new OrgInfo();
	private static final OrgInfoQuery QUERY = new OrgInfoQuery();

	public static OrgInfoQuery me() {
		return QUERY;
	}



	public Page<OrgInfo> paginateByModule(int page, int pagesize, String module) {
		return paginate(page, pagesize, module, null, null, null, null, null);
	}

	public Page<OrgInfo> paginateByModuleAndStatus(int page, int pagesize, String module, String status,
			String orderBy) {
		return paginate(page, pagesize, module, null, status, null, null, orderBy);
	}

	public Page<OrgInfo> paginateByModuleAndStatus(int page, int pagesize, String module, String status) {
		return paginate(page, pagesize, module, null, status, null, null, null);
	}

	public Page<OrgInfo> paginateBySearch(int page, int pagesize, String module, String keyword, String status,
			BigInteger[] tids, String month) {
		String[] modules = StringUtils.isNotBlank(module) ? new String[] { module } : null;
		return paginate(page, pagesize, modules, keyword, status, tids, null, month, null);
	}

	public Page<OrgInfo> paginateByModuleInNormal(int page, int pagesize, String module) {
		return paginate(page, pagesize, module, null, Platform.STATUS_NORMAL, null, null, null);
	}

/*	public Page<OrgInfo> paginateByModuleNotInDelete(int page, int pagesize, String module, String keyword,
			BigInteger[] taxonomyIds, String month) {

		StringBuilder sql = new StringBuilder(" from platform c");	
		//userName
		sql.append(" left join user u on c.user_id = u.id ");
		sql.append(" where c.status <> ?");

		LinkedList<Object> params = new LinkedList<Object>();
		params.add(Platform.STATUS_DELETE);


		if (StringUtils.isNotBlank(keyword)) {
			sql.append(" AND c.name like ? ");
			params.add("%" + keyword + "%");
		}

		if (taxonomyIds != null && taxonomyIds.length > 0) {
			sql.append(" AND t.id in " + toString(taxonomyIds));
		}

		if (StringUtils.isNotBlank(month)) {
			sql.append(" DATE_FORMAT( c.created, \"%Y-%m\" ) = ?");
			params.add(month);
		}

		//sql.append(" group by c.id");
		sql.append(" ORDER BY c.created DESC");

		String select = "select c.*,u.username userName";
		if (params.isEmpty()) {
			return DAO.paginate(page, pagesize, true, select, sql.toString());
		}

		return DAO.paginate(page, pagesize, true, select, sql.toString(), params.toArray());
	}*/

/*	public Page<OrgInfo> paginateInNormal(int page, int pagesize, String module, BigInteger[] taxonomyIds,
			String orderBy) {

		LinkedList<Object> params = new LinkedList<Object>();

		String select = "select c.*";

		StringBuilder sql = new StringBuilder(" from content c");
		sql.append(" left join mapping m on c.id = m.`content_id`");
		sql.append(" left join taxonomy  t on  m.`taxonomy_id` = t.id");

		if (orderBy != null && orderBy.startsWith("meta:")) {
			sql.append(
					" left join metadata meta on meta.`object_type`='content' and meta.`object_id`=c.id and meta.`meta_key`=? ");
			params.add(orderBy.substring("meta:".length()));
		}

		sql.append(" WHERE c.status = 'normal' ");

		appendIfNotEmpty(sql, "c.module", module, params, false);

		if (taxonomyIds != null && taxonomyIds.length > 0) {
			if (taxonomyIds.length == 1) {
				sql.append(" AND m.taxonomy_id = ?");
				params.add(taxonomyIds[0]);
			} else {
				sql.append(" AND exists(select 1 from mapping m where m.`taxonomy_id` in " + toString(taxonomyIds)
						+ " and m.`content_id`=c.id) ");
			}
		}

		sql.append(" group by c.id");

		if (orderBy != null && orderBy.startsWith("meta:")) {
			sql.append(" order by meta.`meta_value` + 0 desc ");
		} else {
			buildOrderBy(orderBy, sql);
		}

		if (params.isEmpty()) {
			return DAO.paginate(page, pagesize, true, select, sql.toString());
		}

		return DAO.paginate(page, pagesize, true, select, sql.toString(), params.toArray());
	}*/

	public Page<OrgInfo> paginate(int page, int pagesize, String module, String keyword, String status,
			BigInteger[] taxonomyIds, BigInteger userId, String orderBy) {

		String[] modules = StringUtils.isNotBlank(module) ? new String[] { module } : null;

		return paginate(page, pagesize, modules, keyword, status, taxonomyIds, userId, null, orderBy);
	}

	public Page<OrgInfo> paginate(int page, int pagesize, String[] modules, String keyword, String status,
			BigInteger[] taxonomyIds, BigInteger userId, String orderBy) {

		return paginate(page, pagesize, modules, keyword, status, taxonomyIds, userId, null, orderBy);
	}

	public Page<OrgInfo> paginate(int page, int pagesize, String[] modules, String keyword, String status,
			BigInteger[] taxonomyIds, BigInteger userId, String month, String orderBy) {

		String select = "select c.*,u.username userName";

		StringBuilder sql = new StringBuilder(" from platform c");
		sql.append(" left join user u on c.user_id = u.id ");		

		LinkedList<Object> params = new LinkedList<Object>();

		boolean needWhere = true;
		
		needWhere = appendIfNotEmpty(sql, "c.status", status, params, needWhere);
		needWhere = appendIfNotEmpty(sql, "c.user_id", userId, params, needWhere);

		if (StringUtils.isNotBlank(keyword)) {
			needWhere = appendWhereOrAnd(sql, needWhere);
			sql.append(" c.name like ? ");
			params.add("%" + keyword + "%");
		}

		if (taxonomyIds != null && taxonomyIds.length > 0) {
			needWhere = appendWhereOrAnd(sql, needWhere);
			sql.append(" t.id in " + toString(taxonomyIds));
		}

		if (StringUtils.isNotBlank(month)) {
			needWhere = appendWhereOrAnd(sql, needWhere);
			sql.append(" DATE_FORMAT( c.created, \"%Y-%m\" ) = ?");
			params.add(month);
		}


		//buildOrderBy(orderBy, sql);

		if (params.isEmpty()) {
			return DAO.paginate(page, pagesize, true, select, sql.toString());
		}

		return DAO.paginate(page, pagesize, true, select, sql.toString(), params.toArray());
	}
	
	public List<OrgInfo> queryHotOrgInfos(int rows) {
		StringBuffer sql = new StringBuffer("SELECT o.platformlist_ico,o.org_number,o.name,o.pc_platform_img,");
		sql.append(" o.pc_platform_list_img,");
		sql.append("CASE o.grade ");
		sql.append("WHEN 1 THEN 'B' ");
		sql.append("WHEN 2 THEN 'BB' ");
		sql.append("WHEN 3 THEN 'BBB' ");
		sql.append("WHEN 4 THEN 'A' ");
		sql.append("WHEN 5 THEN 'AA' ");
		sql.append("WHEN 6 THEN 'AAA' ");
		sql.append("ELSE '未定义' END grade,");  	
	   
		sql.append("COUNT(p.org_number) usable_product_acount ");
		sql.append("FROM tcim_org_info o LEFT JOIN tcim_product p ");
		sql.append("ON o.org_number = p.org_number AND p.`status` = 1 ");
		sql.append("WHERE o.`status` = 1 ");
		sql.append("AND o.org_gray_status = 0 ");
		sql.append("AND o.recommend = 1 ");
		sql.append("AND o.homepage_sort IS NOT NULL ");
		sql.append("GROUP BY o.org_number ");
		sql.append("ORDER BY o.homepage_sort ");
		sql.append("LIMIT ");
		sql.append(rows);
		
		return DAO.find(sql.toString());
	}

	protected String toString(Object[] a) {
		int iMax = a.length - 1;
		StringBuilder b = new StringBuilder();
		b.append('(');
		for (int i = 0;; i++) {
			b.append(String.valueOf(a[i]));
			if (i == iMax)
				return b.append(')').toString();
			b.append(", ");
		}
	}

/*	protected void buildOrderBy(String orderBy, StringBuilder fromBuilder) {

		if (StringUtils.isBlank(orderBy)) {
			fromBuilder.append(" ORDER BY c.created DESC");
			return;
		}

		// maybe orderby == "view_count desc";
		String orderbyInfo[] = orderBy.trim().split("\\s+");
		orderBy = orderbyInfo[0];

		if ("view_count".equals(orderBy)) {
			fromBuilder.append(" ORDER BY c.view_count ");
		}

		else if ("comment_count".equals(orderBy)) {
			fromBuilder.append(" ORDER BY c.comment_count ");
		}

		else if ("modified".equals(orderBy)) {
			fromBuilder.append(" ORDER BY c.modified ");
		}

		else if ("vote_up".equals(orderBy)) {
			fromBuilder.append(" ORDER BY c.vote_up ");
		}

		else if ("vote_down".equals(orderBy)) {
			fromBuilder.append(" ORDER BY c.vote_down ");
		}

		else if ("order_number".equals(orderBy)) {
			fromBuilder.append(" ORDER BY c.order_number ");
		}

		else if ("parent_id".equals(orderBy)) {
			fromBuilder.append(" ORDER BY c.parent_id ");
		}

		else if ("object_id".equals(orderBy)) {
			fromBuilder.append(" ORDER BY c.object_id ");
		}

		else if ("price".equals(orderBy)) {
			fromBuilder.append(" ORDER BY c.price ");
		}

		else if ("comment_time".equals(orderBy)) {
			fromBuilder.append(" ORDER BY c.comment_time ");
		}

		else if ("rate".equals(orderBy)) {
			fromBuilder.append(" ORDER BY c.rate ");
		}

		else if ("rate_count".equals(orderBy)) {
			fromBuilder.append(" ORDER BY c.rate_count ");
		}

		else {
			fromBuilder.append(" ORDER BY c.created ");
		}

		if (orderbyInfo.length == 1) {
			fromBuilder.append(" DESC ");
		} else {
			fromBuilder.append(orderbyInfo[1]);
		}

	}*/

	public Long findCountByModuleAndStatus(String status) {
		return DAO.doFindCount("status=?",  status);
	}

	public List<OrgInfo> findListInNormal(int page, int pagesize) {
		return findListInNormal(page, pagesize, null, null, null, null, null, null, null, null, null, null, null, null,
				null);
	}

	public List<OrgInfo> findListInNormal(int page, int pagesize, String module) {
		String[] modules = new String[] { module };
		return findListInNormal(page, pagesize, null, null, null, null, modules, null, null, null, null, null, null,
				null, null);
	}

	public List<OrgInfo> findListInNormal(int page, int pagesize, BigInteger taxonomyId) {
		return findListInNormal(page, pagesize, null, null, new BigInteger[] { taxonomyId }, null, null, null, null,
				null, null, null, null, null, null);
	}

	public List<OrgInfo> findListInNormal(int page, int pagesize, String orderBy, String keyword, BigInteger[] typeIds,
			String[] typeSlugs, String[] modules, String[] styles, String[] flags, String[] slugs, BigInteger[] userIds,
			BigInteger[] parentIds, String[] tags, Boolean hasThumbnail, String month) {

		if (modules == null) {
			modules = TemplateManager.me().currentTemplateModulesAsArray();
		}

		StringBuilder sql = new StringBuilder(" select  c.* from content c ");
		sql.append(" left join mapping m on c.id = m.`content_id`");
		sql.append(" left join taxonomy  t on  m.`taxonomy_id` = t.id");

		sql.append(" where c.status = 'normal' ");
		LinkedList<Object> params = new LinkedList<Object>();
		appendIfNotEmpty(sql, "m.taxonomy_id", typeIds, params, false);
		appendIfNotEmpty(sql, "c.module", modules, params, false);
		appendIfNotEmpty(sql, "c.style", styles, params, false);
		appendIfNotEmpty(sql, "c.slug", slugs, params, false);
		appendIfNotEmpty(sql, "c.user_id", userIds, params, false);
		appendIfNotEmpty(sql, "c.parent_id", parentIds, params, false);
		appendIfNotEmpty(sql, "t.slug", typeSlugs, params, false);
		appendIfNotEmptyWithLike(sql, "c.flag", flags, params, false);

		if (null != tags && tags.length > 0) {
			appendIfNotEmpty(sql, "t.title", tags, params, false);
			sql.append(" AND t.`type`='tag' ");
		}

		if (StringUtils.isNotBlank(keyword)) {
			sql.append(" AND c.title like ?");
			params.add("%" + keyword + "%");
		}

		if (StringUtils.isNotBlank(month)) {
			sql.append(" AND DATE_FORMAT( c.created, \"%Y-%m\" ) = ?");
			params.add(month);
		}

		if (null != hasThumbnail) {
			if (hasThumbnail) {
				sql.append(" AND c.thumbnail is not null ");
			} else {
				sql.append(" AND c.thumbnail is null ");
			}
		}

		sql.append("GROUP BY c.id");

		//buildOrderBy(orderBy, sql);

		sql.append(" LIMIT ?, ?");
		params.add(page - 1);
		params.add(pagesize);

		return DAO.find(sql.toString(), params.toArray());
	}

	public List<OrgInfo> findByModule(String module) {
		return DAO.doFind("module = ? ", module);
	}

	public OrgInfo findByName( String name, int limit) {
		return DAO.doFindFirst("name = ? order by id desc limit ?", name, limit);
	}

	public OrgInfo findFirstByModuleAndTitle( String title) {
		return DAO.doFindFirst("name = ? order by id desc",  title);
	}

	public OrgInfo findFirstByModuleAndText(String module, String text) {
		return DAO.doFindFirst("module = ? and text = ? order by id desc", module, text);
	}

	public OrgInfo findFirstByModuleAndObjectId(BigInteger objectId) {
		return DAO.doFindFirst("object_id = ? order by id desc", objectId);
	}

	public OrgInfo findFirstByModuleAndObjectId(String module, BigInteger objectId, BigInteger userId) {
		return DAO.doFindFirst("module = ? and object_id = ? and user_id = ? order by id desc", module, objectId,
				userId);
	}

	public OrgInfo findFirstByModuleAndUserId(String module, BigInteger userId) {
		return DAO.doFindFirst("module = ? and user_id = ? order by id desc", module, userId);
	}

	public List<OrgInfo> findListByModuleAndObjectId(String module, BigInteger objectId) {
		return DAO.doFind("module = ? and object_id = ? order by id desc", module, objectId);
	}

	public List<OrgInfo> findListByModuleAndUserId(String module, BigInteger userId) {
		return DAO.doFind("module = ? and user_id = ? order by id desc", module, userId);
	}

	public List<OrgInfo> searchByModuleAndTitle(String module, String title, int limit) {
		return DAO.doFind("module = ? and title like ? order by id desc limit ?", module, "%" + title + "%", limit);
	}



	/*private String buildKey(String module, Object... params) {
		StringBuffer keyBuffer = new StringBuffer(module == null ? "" : "module:" + module);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				keyBuffer.append("-p").append(i).append(":").append(params[i]);
			}
		}
		return keyBuffer.toString().replace(" ", "");
	}*/

/*	public List<OrgInfo> findArchiveByModule(String module) {
		StringBuilder sqlBuilder = new StringBuilder(
				" select  c.*,DATE_FORMAT( c.created, \"%Y-%m\" ) as archiveDate from content c ");
		sqlBuilder.append(" where module = ? ");
		sqlBuilder.append(" order by c.created DESC");
		return DAO.find(sqlBuilder.toString(), module);
	}*/

/*	public OrgInfo findBySlug(final String slug) {
		final StringBuilder sql = new StringBuilder(" select  c.* from  platform c ");
		sql.append(" WHERE c.slug = ?");
		return DAO.getCache(slug, new IDataLoader() {
			@Override
			public Object load() {
				return DAO.findFirst(sql.toString(), slug);
			}
		});
	}*/

	public OrgInfo findById(final BigInteger id) {
		return DAO.getCache(id, new IDataLoader() {
			@Override
			public Object load() {
				return DAO.findById(id);
			}
		});

	}



	public long findCountByModule(String module) {
		return DAO.doFindCount("module = ?", module);
	}

	public long findCountInNormalByModule() {
		return DAO.doFindCount("status <> ?",Platform.STATUS_DELETE);
	}

	public long findCountInNormalByModuleAndUserId(String module, BigInteger userId) {
		return DAO.doFindCount("module = ? AND status <> ? and user_id = ? ", module, Platform.STATUS_DELETE, userId);
	}

	public long findCountInNormalByParentId(BigInteger id, String module) {
		if (id == null) {
			return DAO.doFindCount("parent_id is null AND module = ? AND status <> ?", module, Platform.STATUS_DELETE);
		}
		return DAO.doFindCount("parent_id = ? AND module = ? AND status <> ?", id, module, Platform.STATUS_DELETE);
	}




	public List<Archive> findArchives(String module) {
		String sql = "SELECT DATE_FORMAT( c.created, \"%Y-%m\" ) as d, COUNT( * ) count FROM content c"
				+ " WHERE c.module = ? GROUP BY d";
		List<Record> list = Jdb.find(sql, module);
		if (list == null || list.isEmpty())
			return null;

		List<Archive> datas = new ArrayList<Archive>();
		for (Record r : list) {
			String date = r.getStr("d");
			if (StringUtils.isNotBlank(date)) {
				datas.add(new Archive(date, r.getLong("count")));
			}
		}
		return datas;
	}

}
