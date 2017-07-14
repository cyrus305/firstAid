package com.iaid.webservice.dao;

import com.iaid.webservice.dto.BloodDonation;
import org.skife.jdbi.v2.DBI;

/**
 * Created by Crawlers on 8/30/2016.
 */
public class BloodDonationDao extends AbstractDao<BloodDonation>{
  public BloodDonationDao(DBI dbi){
    super(dbi);
  }
}
