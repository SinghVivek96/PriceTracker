package com.vivek.priceTracker.Amazon.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vivek.priceTracker.Amazon.DAO.AmazonEntityDAO;
import com.vivek.priceTracker.Amazon.Entities.AmazonEntity;
import com.vivek.priceTracker.Amazon.Entities.AmazonEntityCopy;
import com.vivek.priceTracker.Amazon.Entities.AmazonEntityRepository;
import com.vivek.priceTracker.Amazon.Entities.Response;


@RestController
@RequestMapping("/welcome/update")
public class RestAmazonController {
 
  List<AmazonEntity> cust = new ArrayList<AmazonEntity>();
 
  @Autowired
  AmazonEntityRepository amazonEntityRepository;
  @Autowired
  AmazonEntityDAO amazonEntityDAO;
 
  @PostMapping(value = "/save")
  public Response postCustomer(@RequestBody AmazonEntityCopy entity) {
	  System.out.println("Hereloll");
	  System.out.println(entity);
	  //System.out.println(entity.getUrl());
	  AmazonEntity entityFinal = new AmazonEntity(entity.getUrl(),java.sql.Date.valueOf(entity.getDate()),entity.getPrice());
	  amazonEntityDAO.insertWithEntityManager(entityFinal);
	  //System.out.println(entityFinal);
	  Response response = new Response("Done", entity);
	  return response;
  }
}

