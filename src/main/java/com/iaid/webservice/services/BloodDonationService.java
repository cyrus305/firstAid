package com.iaid.webservice.services;

import com.iaid.webservice.dao.AbstractDao;
import com.iaid.webservice.dto.BloodDonation;

/**
 * Created by Crawlers on 8/30/2016.
 */
public class BloodDonationService extends AbstractService<BloodDonation>{
  public BloodDonationService(AbstractDao<BloodDonation> dao) {
    super(dao);
  }
}
