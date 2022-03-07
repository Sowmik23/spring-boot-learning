package com.sowmik.springboot_api.dao;


import org.springframework.stereotype.Repository;

//we can mark it as stereotype @Component or @Repository
//these annotation tells that this is a spring bean and a object of this paymentDaoImpl should be created
//and where ever it is required should be injected.
//@Repository handles database core. and is used for data access core.
@Repository
public class PaymentDaoImpl implements PaymentDao{
}
