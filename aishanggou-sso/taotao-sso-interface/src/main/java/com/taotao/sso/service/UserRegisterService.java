package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;

public interface UserRegisterService {

	TaotaoResult checkUserInfo(String param, int type);
}
