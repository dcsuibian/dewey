package com.dcsuibian.dewey.po;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class DocumentPagePoId {
    private Long documentId;
    private Integer pageNumber;
}
