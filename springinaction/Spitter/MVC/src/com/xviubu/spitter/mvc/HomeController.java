package com.xviubu.spitter.mvc;

import javax.inject.Inject;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xviubu.spitter.service.SpitterService;

@Controller
public class HomeController
{
	@Inject
	public HomeController(SpitterService spitterService)
	{
		this.spitterService = spitterService;
	}

	public String showHomePage(Map<String,Object> model)
	{
		model.put("spittles",spitterService.getRecentSpittles(DEFAULT_SPITTERS_PER_PAGE));

		return "home"; //返回视图名称
	}
	private SpitterService spitterService;
	public static final int DEFAULT_SPITTERS_PER_PAGE = 25;
}
