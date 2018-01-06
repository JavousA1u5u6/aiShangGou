package com.taotao.item.pojo;

import com.taotao.pojo.TbItem;

public class Item extends TbItem {
	
	//因为父类提供的方法 无法满足需求  所以进行了拓展
	public String[] getImages() {
		/*String image = this.getImage();
		if (image != null && !"".equals(image)) {
			return image.split(",");
		}
		return null;*/
		//现获取image 图片连接字符串
		String image = this.getImage();
		if(image!=null && !"".equals(image)){
			return image.split(",");
		}
		return null;
	}
	
	public Item(){
		
	}

	public Item(TbItem tbItem) {
		this.setBarcode(tbItem.getBarcode());
		this.setCid(tbItem.getCid());
		this.setCreated(tbItem.getCreated());
		this.setId(tbItem.getId());
		this.setImage(tbItem.getImage());
		this.setNum(tbItem.getNum());
		this.setPrice(tbItem.getPrice());
		this.setSellPoint(tbItem.getSellPoint());
		this.setStatus(tbItem.getStatus());
		this.setTitle(tbItem.getTitle());
		this.setUpdated(tbItem.getUpdated());
	}
	
}
