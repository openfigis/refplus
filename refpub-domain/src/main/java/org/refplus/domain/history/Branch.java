package org.refplus.domain.history;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.refplus.domain.life.Life;

@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Branch {

	private Life life;

}
