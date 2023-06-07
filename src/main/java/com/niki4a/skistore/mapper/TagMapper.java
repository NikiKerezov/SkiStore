package com.niki4a.skistore.mapper;

import com.niki4a.skistore.controller.resources.TagResource;
import com.niki4a.skistore.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface TagMapper {
    TagMapper TAG_MAPPER = org.mapstruct.factory.Mappers.getMapper(TagMapper.class);

    TagResource toTagResource(Tag tag);

    Tag fromTagResource(TagResource tagResource);

    List<TagResource> toTagResourceList(List<Tag> tagList);
    List<Tag> fromTagResourceList(List<TagResource> tagResourceList);

    default Tag fromString(String tag) {
        Tag tagEntity = new Tag();
        tagEntity.setTagName(tag);
        return tagEntity;
    }

    default Set<Tag> fromStringSet(Set<String> tagList) {
        Set<Tag> tags = new HashSet<>();
        for (String tag : tagList) {
            tags.add(fromString(tag));
        }
        return tags;
    }

    default Set<String> toStringSet(Set<Tag> tagList) {
        Set<String> tags = new HashSet<>();
        for (Tag tag : tagList) {
            tags.add(tag.getTagName());
        }
        return tags;
    }
}
