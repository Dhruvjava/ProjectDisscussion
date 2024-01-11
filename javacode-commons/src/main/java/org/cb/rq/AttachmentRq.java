package org.cb.rq;

import org.cb.base.rs.BaseRq;
import org.springframework.core.io.InputStreamSource;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttachmentRq extends BaseRq {

    private static final long serialVersionUID = -6325010288537883666L;

    private InputStreamSource byteSource;

    private String name;

    private String type;

    private String contentType;

}
