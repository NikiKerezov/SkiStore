package com.niki4a.skistore.mapper;

import com.niki4a.skistore.controller.resources.ProductResource;
import com.niki4a.skistore.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class, TagMapper.class})
public interface ProductMapper {
    ProductMapper PRODUCT_MAPPER = org.mapstruct.factory.Mappers.getMapper(ProductMapper.class);

    ProductResource toProductResource(Product product);
    Product fromProductResource(ProductResource productResource);

    List<ProductResource> toProductResourceList(List<Product> productList);
    List<Product> fromProductResourceList(List<ProductResource> productResourceList);
/*
    @Named("toProductResourceWithoutCategory")
    @Mapping(target = "category", ignore = true)
    ProductResource toProductResourceWithoutCategory(Product product);

    @Named("toProductWithoutCategory")
    @Mapping(target = "category", ignore = true)
    Product toProductWithoutCategory(ProductResource productResource);

    @Named("toProductResourceWithoutTags")
    @Mapping(target = "tags", ignore = true)
    ProductResource toProductResourceWithoutTags(Product product);

    @Named("toProductWithoutTags")
    @Mapping(target = "tags", ignore = true)
    Product toProductWithoutTags(ProductResource productResource);

 */
}
