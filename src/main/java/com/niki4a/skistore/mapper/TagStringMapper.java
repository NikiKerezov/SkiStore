package com.niki4a.skistore.mapper;

import com.niki4a.skistore.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TagStringMapper {

    //TagStringMapper TAG_STRING_MAPPER = org.mapstruct.factory.Mappers.getMapper(TagStringMapper.class);

    default Tag fromString(String tag) {
        Tag tagEntity = new Tag();
        tagEntity.setTagName(tag);
        return tagEntity;
    }

    default Set<Tag> fromStringSet(Set<String> tagList) {
        HashSet<Tag> tags = new HashSet<>();
        for (String tag : tagList) {
            tags.add(fromString(tag));
        }
        return tags;
    }

    default Set<String> toStringSet(Set<Tag> tagList) {
        HashSet<String> tags = new HashSet<>();
        for (Tag tag : tagList) {
            tags.add(tag.getTagName());
        }
        return tags;
    }

    /*

    Tag fromString(String tag);

    String toString(Tag tag);

    Set<Tag> fromStringSet(Set<String> tagList);

    Set<String> toStringSet(Set<Tag> tagList);

     */
}
