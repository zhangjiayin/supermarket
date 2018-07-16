package com.xiaoniu.sms.service;

import com.xiaoniu.sms.req.CheckCodeReq;
import com.xiaoniu.sms.req.GenerateCodeReq;
import com.xiaoniu.sms.util.SmsResult;

/**
 * 随机验证码
 * @author 颜彩云
 *
 */
public interface IRandomImageCodeService {
	/**
	 * 生成验证码
	 * @param genCodeDto, 生成随机验证码参数, @see GenerateCodeReq
	 * @return
	 */
	public SmsResult<String> generateImageCode(GenerateCodeReq genCodeDto);
	/**
	 * 校验验证码
	 * @param checkCodeDto, 校验验证码参数, @see CheckCodeReq
	 */
	public SmsResult<Object> checkImageCode(CheckCodeReq checkCodeDto);
}
