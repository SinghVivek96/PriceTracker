package com.vivek.priceTracker.Amazon.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.vivek.priceTracker.Amazon.Entities.AmazonEntity;

@Repository
public class AmazonEntityDAO {
@PersistenceContext
private EntityManager entiyManager;

@Transactional
public void insertWithQuery(AmazonEntity amazonEntity) {
	entiyManager.createNativeQuery("INSERT INTO amazonpricetracker (url, date, price) VALUES (?,?,?)")
  .setParameter(1, amazonEntity.getUrl())
  .setParameter(2, amazonEntity.getDate())
  .setParameter(3, amazonEntity.getPrice())
  .executeUpdate();
}


@Transactional
public void insertWithEntityManager(AmazonEntity amazonEntity) {
    this.entiyManager.persist(amazonEntity);
}

}
