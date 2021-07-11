package com.vivek.priceTracker.Amazon.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vivek.priceTracker.Amazon.Entities.AmazonEntity;
import com.vivek.priceTracker.Amazon.Entities.AmazonEntityRepository;

@Controller
public class AmazonController {
	@Autowired
	AmazonEntityRepository amazonEntityRepository;

	@RequestMapping(value = { "", "/", "/admin" })
	public String showMainPage() {
		return "welcome";
	}
	/*
	 * @RequestMapping(path = { "/welcome" }) public String showWelcomePage() {
	 * System.out.println("Here"); return "welcome"; }
	 */

	/*
	  @RequestMapping(path = {"/fetch"}) public String showFetchedData() throws	  ParseException { 
	DateTimeFormatter dtf =	  DateTimeFormatter.ofPattern("yyyy-MM-dd"); LocalDateTime now =
	  LocalDateTime.now(); System.out.println(dtf.format(now));
	  amazonEntityRepository.save(new	  AmazonEntity("lkadflkdmfhjjsdf",java.sql.Date.valueOf(dtf.format(now)),100));
	  ArrayList<AmazonEntity> arrayList = new ArrayList<AmazonEntity>(); 
	  arrayList  = (ArrayList<AmazonEntity>) amazonEntityRepository.findAll();
	  System.out.println(arrayList); return "fetched"; }
	 */

	@RequestMapping(value = "/fetch", method = RequestMethod.POST)
	public String postDataToDB(Model model) {
		DateTimeFormatter dtf =	  DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
		LocalDateTime now =	LocalDateTime.now(); System.out.println(dtf.format(now));
		amazonEntityRepository.save(new	  AmazonEntity("lkadflkdmfhjjsdf",java.sql.Date.valueOf(dtf.format(now)),"100"));
		return "SUCCESS";
	}
}