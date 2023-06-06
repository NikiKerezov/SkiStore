package com.niki4a.skistore.mapper;

import com.niki4a.skistore.controller.resources.CategoryResource;
import com.niki4a.skistore.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface CategoryMapper {
    CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

    //@Mapping(target = "products", qualifiedByName = "toProductResourceWithoutCategory")
    Category fromCategoryResource(CategoryResource categoryResource);

   // @Mapping(target = "products", qualifiedByName = "toProductWithoutCategory")
    CategoryResource toCategoryResource(Category category);

    List <CategoryResource> toCategoryResourceList(List<Category> categoryList);
    List <Category> fromCategoryResourceList(List<CategoryResource> categoryResourceList);
}
