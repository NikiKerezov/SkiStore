package com.niki4a.skistore.mapper;

import com.niki4a.skistore.controller.resources.ProductResource;
import com.niki4a.skistore.entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = {CategoryMapper.class, TagMapper.class})
public interface ProductMapper {
    ProductMapper PRODUCT_MAPPER = org.mapstruct.factory.Mappers.getMapper(ProductMapper.class);

    ProductResource toProductResource(Product product);
    Product fromProductResource(ProductResource productResource);

    List<ProductResource> toProductResourceList(List<Product> productList);
    List<Product> fromProductResourceList(List<ProductResource> productResourceList);
}
