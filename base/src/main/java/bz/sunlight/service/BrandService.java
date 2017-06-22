package bz.sunlight.service;

import java.util.List;

import bz.sunlight.entity.Brand;

public interface BrandService {

	List<Brand> getBrand(Short status);
}
