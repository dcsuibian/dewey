package com.dcsuibian.dewey.service.impl;

import com.dcsuibian.dewey.entity.Document;
import com.dcsuibian.dewey.po.DocumentPo;
import com.dcsuibian.dewey.repository.DocumentPagePoRepository;
import com.dcsuibian.dewey.repository.DocumentPoRepository;
import com.dcsuibian.dewey.service.DocumentService;
import com.dcsuibian.dewey.vo.PageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {
    private final DocumentPoRepository poRepository;
    private final DocumentPagePoRepository pagePoRepository;

    @Autowired
    public DocumentServiceImpl(DocumentPoRepository poRepository, DocumentPagePoRepository pagePoRepository) {
        this.poRepository = poRepository;
        this.pagePoRepository = pagePoRepository;
    }

    @Override
    public PageWrapper<Document> getNewest(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize); // PageRequest的页码从0开始
        PageWrapper<Document> page = PageWrapper.of(poRepository.findAllByOrderByCreateTimeDesc(pageable), DocumentPo::convert);
        for (Document document : page.getData()) {
            process(document);
        }
        return page;
    }

    private void process(Document document) {
        document.setContent(null);
    }
}
