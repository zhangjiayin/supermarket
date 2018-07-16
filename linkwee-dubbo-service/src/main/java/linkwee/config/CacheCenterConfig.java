package linkwee.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CacheCenterConfig {

	@Value("${cc.set}")
	private String set;
	
	@Value("${cc.del}")
	private String del;
	
	@Value("${cc.get}")
	private String get;
	
	@Value("${cc.testIndex}")
	private String testIndex;

	public String getSet() {
		return set;
	}

	public void setSet(String set) {
		this.set = set;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getGet() {
		return get;
	}

	public void setGet(String get) {
		this.get = get;
	}

	public String getTestIndex() {
		return testIndex;
	}

	public void setTestIndex(String testIndex) {
		this.testIndex = testIndex;
	}
}
