package net.movielot.movielot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MovielotApplicationTests {

	@Test
	void contextLoads() {
		assertThat(true).isFalse();
	}

}
