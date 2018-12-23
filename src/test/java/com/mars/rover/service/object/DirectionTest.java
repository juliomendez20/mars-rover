package com.mars.rover.service.object;

import static org.junit.Assert.*;

import org.junit.Test;

public class DirectionTest {

	@Test
	public void testRotateRight() {
		
		assertEquals(Direction.N.rotateRight(),Direction.E);
		assertEquals(Direction.E.rotateRight(),Direction.S);
		assertEquals(Direction.S.rotateRight(),Direction.W);
		assertEquals(Direction.W.rotateRight(),Direction.N);
		
	}
	
	@Test
	public void testRotateLeft() {
		
		assertEquals(Direction.N.rotateLeft(),Direction.W);
		assertEquals(Direction.W.rotateLeft(),Direction.S);
		assertEquals(Direction.S.rotateLeft(),Direction.E);
		assertEquals(Direction.E.rotateLeft(),Direction.N);
		
	}

}
