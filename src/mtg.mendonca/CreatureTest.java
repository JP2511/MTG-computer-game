package mtg.mendonca;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatureTest {

    /* -----------------------------------------------------------------------------------------------------------------
     * Tests for the getAttack() function
     * -----------------------------------------------------------------------------------------------------------------
     */

    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal.
     */
    @Test
    void getAttack_equal() {
        Creature creature = new Creature();
        assertEquals(creature.getAttack(), 0);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with 0 attack.
     */
    @Test
    void getAttack_different() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        assertEquals(creature.getAttack(), 5);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
     * and with one counter with defense == attack.
     */
    @Test
    void getAttack_equal_1counter_equal() {
        Creature creature = new Creature();
        creature.addCounter("+1/+1");
        assertEquals(creature.getAttack(), 1);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
     * and with one counter with defense != attack.
     */
    @Test
    void getAttack_equal_1counter_different() {
        Creature creature = new Creature();
        creature.addCounter("+3/+2");
        assertEquals(creature.getAttack(), 3);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense
     * different with one counter with defense == attack.
     */
    @Test
    void getAttack_different_1counter_equal() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addCounter("+2/+2");
        assertEquals(creature.getAttack(), 7);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense
     * different and with one counter with defense != attack.
     */
    @Test
    void getAttack_different_1counter_different() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addCounter("+4/+2");
        assertEquals(creature.getAttack(), 9);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
     * and with two counters with defense == attack.
     */
    @Test
    void getAttack_equal_2counter_equal_equal() {
        Creature creature = new Creature();
        creature.addCounter("+1/+1");
        creature.addCounter("+2/+2");
        assertEquals(creature.getAttack(), 3);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
     * and with one counter with defense != attack and one counter with defense == attack.
     */
    @Test
    void getAttack_equal_1counter_different_equal() {
        Creature creature = new Creature();
        creature.addCounter("+3/+2");
        creature.addCounter("+2/+2");
        assertEquals(creature.getAttack(), 5);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
     * and with two counters with defense != attack.
     */
    @Test
    void getAttack_equal_2counter_different_different() {
        Creature creature = new Creature();
        creature.addCounter("+3/+1");
        creature.addCounter("+1/+3");
        assertEquals(creature.getAttack(), 4);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense
     * different with two counters with defense == attack.
     */
    @Test
    void getAttack_different_2counter_equal_equal() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addCounter("+2/+2");
        creature.addCounter("+4/+4");
        assertEquals(creature.getAttack(), 11);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense
     * different and with one counter with defense != attack and one counter with defense == attack.
     */
    @Test
    void getAttack_different_2counter_different_equal() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addCounter("+4/+2");
        creature.addCounter("+2/+2");
        assertEquals(creature.getAttack(), 11);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense
     * different with two counters with defense != attack.
     */
    @Test
    void getAttack_different_2counter_different_different() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addCounter("+4/+2");
        creature.addCounter("+2/+4");
        assertEquals(creature.getAttack(), 11);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
     * and with one temporary counter with defense == attack.
     */
    @Test
    void getAttack_equal_1temp_equal() {
        Creature creature = new Creature();
        creature.addTempCounter(new Creature(), "+1/+1");
        assertEquals(creature.getAttack(), 1);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
     * and with one temporary counter with defense != attack.
     */
    @Test
    void getAttack_equal_1temp_different() {
        Creature creature = new Creature();
        creature.addTempCounter(new Creature(),"+3/+2");
        assertEquals(creature.getAttack(), 3);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense
     * different with one temporary counter with defense == attack.
     */
    @Test
    void getAttack_different_1temp_equal() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addTempCounter(new Creature(),"+2/+2");
        assertEquals(creature.getAttack(), 7);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense
     * different and with one temporary counter with defense != attack.
     */
    @Test
    void getAttack_different_1temp_different() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addTempCounter(new Creature(),"+4/+2");
        assertEquals(creature.getAttack(), 9);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
     * and with two temporary counters with defense == attack.
     */
    @Test
    void getAttack_equal_2temp_equal_equal() {
        Creature creature = new Creature();
        creature.addTempCounter(new Creature(), "+1/+1");
        creature.addTempCounter(new Creature(), "+1/+1");
        assertEquals(creature.getAttack(), 2);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
     * and with one temporary counter with defense != attack and one temporary counter with defense == attack.
     */
    @Test
    void getAttack_equal_1temp_equal_different() {
        Creature creature = new Creature();
        creature.addTempCounter(new Creature(),"+3/+2");
        creature.addTempCounter(new Creature(),"+2/+2");
        assertEquals(creature.getAttack(), 5);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
     * and with two temporary counters with defense != attack.
     */
    @Test
    void getAttack_equal_2temp_different_different() {
        Creature creature = new Creature();
        creature.addTempCounter(new Creature(), "+3/+1");
        creature.addTempCounter(new Creature(), "+3/+1");
        assertEquals(creature.getAttack(), 6);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense
     * different and with two temporary counters with defense == attack.
     */
    @Test
    void getAttack_different_2temp_equal_equal() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addTempCounter(new Creature(), "+1/+1");
        creature.addTempCounter(new Creature(), "+1/+1");
        assertEquals(creature.getAttack(), 7);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense
     * different and with one temporary counter with defense != attack and one temporary counter with defense == attack.
     */
    @Test
    void getAttack_different_1temp_equal_different() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addTempCounter(new Creature(),"+3/+2");
        creature.addTempCounter(new Creature(),"+2/+2");
        assertEquals(creature.getAttack(), 10);
    }


    /**
     * Test for the attack function. Testing for the value of the attack with a creature with attack and defense
     * different and with two temporary counters with defense != attack.
     */
    @Test
    void getAttack_different_2temp_different_different() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addTempCounter(new Creature(), "+3/+1");
        creature.addTempCounter(new Creature(), "+3/+1");
        assertEquals(creature.getAttack(), 11);
    }

    // missing tests with both temporary counters and normal counters

    /* -----------------------------------------------------------------------------------------------------------------
     * Tests for the getDefense() function
     * -----------------------------------------------------------------------------------------------------------------
     */
    @Test
    void getDefense() {
    }

    @Test
    void isDead() {
    }

    @Test
    void getVigilance() {
    }

    @Test
    void getDeathtouch() {
    }

    @Test
    void getFlying() {
    }

    @Test
    void getReach() {
    }

    @Test
    void getFirstStrike() {
    }

    @Test
    void getDoubleStrike() {
    }

    @Test
    void getFlash() {
    }

    @Test
    void alterDefense() {
    }

    @Test
    void setTurnInWhichItWasPlayed() {
    }

    @Test
    void resetDefense() {
    }

    @Test
    void doDefense() {
    }

    @Test
    void doAttack() {
    }

    @Test
    void testDoAttack() {
    }

    @Test
    void attackInParts() {
    }

    @Test
    void earlyPhaseAttack() {
    }

    @Test
    void normalPhaseAttack() {
    }

    @Test
    void canAttack() {
    }

    @Test
    void getCardAttributes() {
    }
}