package com.sowmik.springboot_api.services;

import com.sowmik.springboot_api.dao.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    //it tells that this particular dependency should be automatically wired at runtime.
    //Spring will search for a implementation class of this particular interface which is in this case paymentDaoImpl.
    @Autowired
    private PaymentDao dao;

    public PaymentDao getDao() {
        return dao;
    }

    public void setDao(PaymentDao dao) {
        this.dao = dao;
    }
}
