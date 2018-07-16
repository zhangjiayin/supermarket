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
package io.jpress.code.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.jfinal.plugin.activerecord.generator.ModelGenerator;
import com.jfinal.plugin.activerecord.generator.TableMeta;

public class JControllerGenerator extends ModelGenerator {

	public JControllerGenerator(String modelPackageName,
			String baseModelPackageName, String modelOutputDir) {
		super(modelPackageName, baseModelPackageName, modelOutputDir);
		
		this.importTemplate = "import io.jpress.core.annotation.UrlMapping;%n"
				+ "import io.jpress.model.%s;%n%n";
		
		this.classDefineTemplate =
				"/**%n" +
				" * Generated by JPress.%n" +
				" */%n" +
				"@UrlMapping(url = \"/admin/%s\", viewPath = \"/WEB-INF/admin/%s\")%n" +
				"public class _%sController extends BaseAdminController<%s> { %n%n";
		
		
		this.daoTemplate = "";
		
	}
	
	@Override
	protected void genImport(TableMeta tableMeta, StringBuilder ret) {
		// TODO Auto-generated method stub
//		super.genImport(tableMeta, ret);
		ret.append(String.format(importTemplate, tableMeta.modelName));
	}
	
	@Override
	protected void genClassDefine(TableMeta tableMeta, StringBuilder ret) {
		ret.append(String.format(classDefineTemplate, tableMeta.name,tableMeta.name,tableMeta.modelName,tableMeta.modelName));
	}
	
	public void generate(List<TableMeta> tableMetas) {
		System.out.println("Generate AdminController ...");
		for (TableMeta tableMeta : tableMetas)
			genModelContent(tableMeta);
		wirtToFile(tableMetas);
	}
	
	
	protected void wirtToFile(TableMeta tableMeta) throws IOException {
		File dir = new File(modelOutputDir);
		if (!dir.exists())
			dir.mkdirs();
		
		String target = modelOutputDir + File.separator + "_"+tableMeta.modelName + "Controller.java";
		
		File file = new File(target);
		if (file.exists()) {
			return ;	// 若 Model 存在，不覆盖
		}
		
		FileWriter fw = new FileWriter(file);
		try {
			fw.write(tableMeta.modelContent);
		}
		finally {
			fw.close();
		}
	}

		
		

}
