package com.liren.fluentValidator;

import org.junit.Test;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.baidu.unbiz.fluentvalidator.Result;

public class AppTest {

	@Test
	public void AppTest() {
		
		Car car = new Car();
		car.setSeatCount(1);

		Result ret = FluentValidator.checkAll().on(car.getLicensePlate(), new CarLicensePlateValidator())
				.on(car.getManufacturer(), new CarManufacturerValidator())
				.on(car.getSeatCount(), new CarSeatCountValidator()).doValidate().result(null);

		System.out.println(ret);
	}

}
