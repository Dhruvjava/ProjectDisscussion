package org.cb.base.rs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BaseRq implements Serializable {

    @Serial
    private static final long serialVersionUID = 85381423028274032L;

}
