package com.imooc.myo2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.myo2o.BaseTest;
import com.imooc.myo2o.entity.ProductCategory;

public class ProductCategoryDaoTest extends BaseTest{
	
	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Test
	public void testBatchInsertProductCategory(){
		ProductCategory pc=new ProductCategory();
		pc.setProductCategoryName("ces");
		pc.setPriority(111);
		pc.setCreateTime(new Date());
		pc.setShopId(20L);
		ProductCategory pc1=new ProductCategory();
		pc1.setProductCategoryName("ces");
		pc1.setPriority(111);
		pc1.setCreateTime(new Date());
		pc1.setShopId(20L);
		List<ProductCategory> list=new ArrayList<ProductCategory>();
		list.add(pc);
		list.add(pc1);
		int effectedNum=productCategoryDao.batchInsertProductCategory(list);
		
	}

}
