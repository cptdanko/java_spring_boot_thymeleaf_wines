package com.bhuman.vt.challenge;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bhuman.vt.challenge.data.DataStore;
import com.bhuman.vt.challenge.model.Wine;

@Controller
public class BestWinesSpring {

	//find a way to @Autowire it
	private DataStore ds = new DataStore();
	
	private Wine wine;
	
	@GetMapping("/")
	public String getBestWines(Model model) {
		List<Wine> wines = ds.getWines();
		model.addAttribute("wines", wines);
		return "bestWines";
	}
	
	@GetMapping("/wine")
	public String getWine(@RequestParam(value = "code", defaultValue = "15MPPN002-VK") String code, Model model) {
		this.wine = ds.getWine(code);
		model.addAttribute("wine", this.wine);
		return "wine";
	}
}
