package org.refplus.domain.core;

import java.util.ArrayList;

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
@EqualsAndHashCode(callSuper = true)
public class Ro extends RefDef {

	public Ro(Code code, MultiLingualString mls) {
		codeList = new ArrayList<Code>();
		codeList.add(code);
		multiLingualStringList = new ArrayList<MultiLingualString>();
		multiLingualStringList.add(mls);
	}

}
