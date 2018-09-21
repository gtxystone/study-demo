package com.liren.fluentValidator;

import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

public class CarLicensePlateValidator extends ValidatorHandler<String> implements Validator<String>{

	@Override
	public boolean validate(ValidatorContext context, String t) {
		// TODO Auto-generated method stub
		return super.validate(context, t);
	}

}
