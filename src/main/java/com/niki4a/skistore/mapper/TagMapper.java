package com.niki4a.skistore.mapper;

import com.niki4a.skistore.controller.resources.TagResource;
import com.niki4a.skistore.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface TagMapper {
    TagMapper TAG_MAPPER = org.mapstruct.factory.Mappers.getMapper(TagMapper.class);

   // @Mapping(target = "products", qualifiedByName = "toProductResourceWithoutTags")
    TagResource toTagResource(Tag tag);

    //@Mapping(target = "products", qualifiedByName = "toProductWithoutTags")
    Tag fromTagResource(TagResource tagResource);

    List<TagResource> toTagResourceList(List<Tag> tagList);
    List<Tag> fromTagResourceList(List<TagResource> tagResourceList);
}
