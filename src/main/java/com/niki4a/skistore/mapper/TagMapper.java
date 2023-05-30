package com.niki4a.skistore.mapper;

import com.niki4a.skistore.controller.resources.TagResource;
import com.niki4a.skistore.entity.Tag;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper (uses = {ProductMapper.class})
public interface TagMapper {
    TagMapper TAG_MAPPER = org.mapstruct.factory.Mappers.getMapper(TagMapper.class);

    TagResource toTagResource(Tag tag);
    Tag fromTagResource(TagResource tagResource);

    List<TagResource> toTagResourceList(List<Tag> tagList);
    List<Tag> fromTagResourceList(List<TagResource> tagResourceList);
}
