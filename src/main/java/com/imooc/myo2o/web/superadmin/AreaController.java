package com.imooc.myo2o.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.myo2o.entity.Area;
import com.imooc.myo2o.entity.ConstantForSuperAdmin;
import com.imooc.myo2o.service.AreaService;

@Controller
@RequestMapping("/superadmin")
public class AreaController {
	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "/listareas", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> listAreas() {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<Area> list = new ArrayList<Area>();
		try {
			list = areaService.getAreaList();
			modelMap.put(ConstantForSuperAdmin.PAGE_SIZE, list);
			modelMap.put(ConstantForSuperAdmin.TOTAL, list.size());

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		return modelMap;
	}

}
