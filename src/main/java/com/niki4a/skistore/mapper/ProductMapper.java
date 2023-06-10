package com.niki4a.skistore.mapper;

import com.niki4a.skistore.controller.resources.ProductResource;
import com.niki4a.skistore.entity.Category;
import com.niki4a.skistore.entity.Product;
import com.niki4a.skistore.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, TagStringMapper.class})
public interface ProductMapper {
    //ProductMapper PRODUCT_MAPPER = org.mapstruct.factory.Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "category", target = "category.categoryName")
    @Mapping(source = "tags", target = "tags")
    Product fromProductResource(ProductResource productResource);

    @Mapping(source = "category.categoryName", target = "category")
    @Mapping(source = "tags", target = "tags")
    ProductResource toProductResource(Product product);

    List<ProductResource> toProductResourceList(List<Product> productList);

    List<Product> fromProductResourceList(List<ProductResource> productResourceList);



}
