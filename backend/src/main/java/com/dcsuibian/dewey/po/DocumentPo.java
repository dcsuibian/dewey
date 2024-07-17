package com.dcsuibian.dewey.po;

import com.dcsuibian.dewey.config.CommonConfig;
import com.dcsuibian.dewey.entity.Category;
import com.dcsuibian.dewey.entity.Document;
import com.dcsuibian.dewey.entity.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity(name = "document")
public class DocumentPo {
    private static final ObjectMapper objectMapper = CommonConfig.objectMapper();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String tags;
    private String type;
    private String cover;
    private String content;
    private Long size;
    private Long uploaderId;
    private Long categoryId;
    private String accessLevel;
    private Long createTime;
    private Long updateTime;

    public static DocumentPo convert(Document document) {
        try {
            if (null == document) return null;
            DocumentPo po = new DocumentPo();
            po.setId(document.getId());
            po.setTitle(document.getTitle());
            po.setDescription(document.getDescription());
            po.setTags(objectMapper.writeValueAsString(document.getTags()));
            po.setType(null == document.getType() ? null : document.getType().getCode());
            po.setCover(document.getCover());
            po.setContent(document.getContent());
            po.setSize(document.getSize());
            po.setUploaderId(null == document.getUploader() ? null : document.getUploader().getId());
            po.setCategoryId(null == document.getCategory() ? null : document.getCategory().getId());
            po.setAccessLevel(document.getAccessLevel());
            po.setCreateTime(null == document.getCreateTime() ? null : document.getCreateTime().toEpochMilli());
            po.setUpdateTime(null == document.getUpdateTime() ? null : document.getUpdateTime().toEpochMilli());
            return po;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static Document convert(DocumentPo po) {
        try {
            if (null == po) return null;
            Document document = new Document();
            document.setId(po.getId());
            document.setTitle(po.getTitle());
            document.setDescription(po.getDescription());
            document.setTags(objectMapper.readValue(po.getTags(), new TypeReference<>() {
            }));
            document.setType(Document.Type.fromCode(po.getType()));
            document.setCover(po.getCover());
            document.setContent(po.getContent());
            document.setSize(po.getSize());
            if (null != po.getUploaderId()) {
                User user = new User();
                user.setId(po.getUploaderId());
                document.setUploader(user);
            }
            if (null != po.getCategoryId()) {
                Category category = new Category();
                category.setId(po.getCategoryId());
                document.setCategory(category);
            }
            document.setAccessLevel(po.getAccessLevel());
            document.setCreateTime(null == po.getCreateTime() ? null : Instant.ofEpochMilli(po.getCreateTime()));
            document.setUpdateTime(null == po.getUpdateTime() ? null : Instant.ofEpochMilli(po.getUpdateTime()));
            return document;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
