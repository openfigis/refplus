package org.refplus.domain.core;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public abstract class RefDef {

	protected List<Code> codeList;
	protected List<MultiLingualString> multiLingualStringList;

}
