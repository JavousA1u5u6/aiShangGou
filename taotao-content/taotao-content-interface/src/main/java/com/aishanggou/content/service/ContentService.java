package com.aishanggou.content.service;

import java.util.List;

import com.aishanggou.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {

	TaotaoResult insertContent(TbContent content);
	List<TbContent> getContentList(long cid);
}
