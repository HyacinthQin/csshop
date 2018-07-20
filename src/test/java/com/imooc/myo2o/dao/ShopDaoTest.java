package com.imooc.myo2o.dao;


import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.myo2o.BaseTest;
import com.imooc.myo2o.entity.Area;
import com.imooc.myo2o.entity.PersonInfo;
import com.imooc.myo2o.entity.Shop;
import com.imooc.myo2o.entity.ShopCategory;


public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;
	@Test
	public void testInsertShop(){
		Shop shop=new Shop();
		PersonInfo owner=new PersonInfo();
		Area area=new Area();
		ShopCategory shopCategory=new ShopCategory();
		owner.setUserId(8L);
		area.setAreaId(3);
		shopCategory.setShopCategoryId(14L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试店铺");
		shop.setShopAddr("测试店铺地址");
		shop.setShopDesc("Test");
		shop.setPhone("PHONE");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(1);
		shop.setPriority(1);
		shop.setAdvice("审核中");
		int effectNum=shopDao.insertShop(shop);
		System.out.println(effectNum);
		
	}
	@Test
	public void testQueryShopListAndCount(){
		Shop shopCondtion=new Shop();
		PersonInfo owner =new PersonInfo();
		owner.setUserId(8L);
		shopCondtion.setOwner(owner);
		List<Shop> shoplist=shopDao.queryShopList(shopCondtion, 0, 5);
		int count=shopDao.queryShopCount(shopCondtion);
		System.out.println("店铺列表的大小"+shoplist.size());
		System.out.println("店铺总数"+count);
		ShopCategory sc=new ShopCategory();
		sc.setShopCategoryId(10L);
		shoplist=shopDao.queryShopList(shopCondtion, 0, 2);
		count=shopDao.queryShopCount(shopCondtion);

		System.out.println("新店铺的大小"+shoplist.size());
		System.out.println("店铺总数"+count);
		
	}
}
