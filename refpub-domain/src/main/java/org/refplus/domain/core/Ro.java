package org.refplus.domain.core;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Ro is a Reference Object
 * 
 * 
 * @author Erik van Ingen
 *
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Ro {

	protected List<Code> codeList;
	protected List<MultiLingualString> multiLingualStringList;

	public Ro(Code code, MultiLingualString mls) {
		codeList = new ArrayList<Code>();
		codeList.add(code);
		multiLingualStringList = new ArrayList<MultiLingualString>();
		multiLingualStringList.add(mls);
	}

}
