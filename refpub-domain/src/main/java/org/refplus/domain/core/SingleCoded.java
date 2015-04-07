package org.refplus.domain.core;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public abstract class SingleCoded {

	protected Code conceptCode;
	protected List<MultiLingualString> name;
}
