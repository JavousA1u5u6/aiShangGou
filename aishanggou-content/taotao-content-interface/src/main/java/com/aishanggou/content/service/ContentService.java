package com.aishanggou.content.service;

import java.util.List;

import com.aishanggou.po.TbContent;
import com.taotao.common.pojo.TaotaoResult;


public interface ContentService {

	TaotaoResult insertContent(TbContent content);
	List<TbContent> getContentList(long cid);
}
