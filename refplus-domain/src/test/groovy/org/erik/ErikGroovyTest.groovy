package org.erik
import spock.lang.Specification
import spock.lang.Unroll


class ErikGroovyTest extends Specification {

	@Unroll
	def 'max(#first, #second) == #result'() {
		expect: Math.max(first, second) == result

		where:
		first | second | result
		1     | 2      | 2
		2     | 1      | 3
		2     | 2      | 2
	}
}

class Book {
	String title
	Author author
}

class Author {
	String first
	String last
}
