package com.niki4a.skistore.mapper;

import com.niki4a.skistore.controller.resources.CategoryResource;
import com.niki4a.skistore.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {ProductMapper.class})
public interface CategoryMapper {
    CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

    CategoryResource toCategoryResource(Category category);
    Category fromCategoryResource(CategoryResource categoryResource);

    List <CategoryResource> toCategoryResourceList(List<Category> categoryList);
    List <Category> fromCategoryResourceList(List<CategoryResource> categoryResourceList);
}
