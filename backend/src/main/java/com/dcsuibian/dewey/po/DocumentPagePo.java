package com.dcsuibian.dewey.po;

import com.dcsuibian.dewey.entity.DocumentPage;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "document_page")
public class DocumentPagePo {
    @EmbeddedId
    private DocumentPagePoId id;
    private String content;

    public static DocumentPage convert(DocumentPagePo po) {
        if (null == po) return null;
        DocumentPage documentPage = new DocumentPage();
        documentPage.setPageNumber(null == po.getId() ? null : po.getId().getPageNumber());
        documentPage.setContent(po.getContent());
        return documentPage;
    }
}
