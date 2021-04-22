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
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
     * and with one counter with defense == attack.
     */
    @Test
    void getAttack_equal_1counter_equal() {
        Creature creature = new Creature();
        creature.addCounter("+1/+1");
        assertEquals(creature.getAttack(), 1);
    }


    /**
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
     * and with one counter with defense != attack.
     */
    @Test
    void getAttack_equal_1counter_different() {
        Creature creature = new Creature();
        creature.addCounter("+3/+2");
        assertEquals(creature.getAttack(), 3);
    }


    /**
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense
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
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense
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
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
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
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
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
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
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
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense
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
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense
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
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense
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
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
     * and with one temporary counter with defense == attack.
     */
    @Test
    void getAttack_equal_1temp_equal() {
        Creature creature = new Creature();
        creature.addTempCounter(new Creature(), "+1/+1");
        assertEquals(creature.getAttack(), 1);
    }


    /**
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
     * and with one temporary counter with defense != attack.
     */
    @Test
    void getAttack_equal_1temp_different() {
        Creature creature = new Creature();
        creature.addTempCounter(new Creature(),"+3/+2");
        assertEquals(creature.getAttack(), 3);
    }


    /**
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense
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
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense
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
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
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
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
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
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
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
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense
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
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense
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
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense
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


    /**
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
     * and with a counter and a temporary counter with defense == attack.
     */
    @Test
    void getAttack_equal_counter_equal_temp_equal() {
        Creature creature = new Creature();
        creature.addCounter("+1/+1");
        creature.addTempCounter(new Creature(), "+1/+1");
        assertEquals(creature.getAttack(), 2);
    }


    /**
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
     * and with one counter with defense == attack and one temporary counter with defense != attack.
     */
    @Test
    void getAttack_equal_counter_equal_temp_different() {
        Creature creature = new Creature();
        creature.addCounter("+2/+2");
        creature.addTempCounter(new Creature(),"+3/+2");
        assertEquals(creature.getAttack(), 5);
    }


    /**
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
     * and with one counter with defense != attack and one temporary counter with defense == attack.
     */
    @Test
    void getAttack_equal_counter_different_temp_equal() {
        Creature creature = new Creature();
        creature.addCounter("+3/+2");
        creature.addTempCounter(new Creature(),"+2/+2");
        assertEquals(creature.getAttack(), 5);
    }


    /**
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense equal
     * and with a counter and a temporary counters with defense != attack.
     */
    @Test
    void getAttack_equal_counter_different_temp_different() {
        Creature creature = new Creature();
        creature.addTempCounter(new Creature(), "+3/+1");
        creature.addTempCounter(new Creature(), "+3/+1");
        assertEquals(creature.getAttack(), 6);
    }


    /**
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense
     * different and with a counter and a temporary counter with defense == attack.
     */
    @Test
    void getAttack_different_counter_equal_temp_equal() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");;
        creature.addCounter("+1/+1");
        creature.addTempCounter(new Creature(), "+1/+1");
        assertEquals(creature.getAttack(), 7);
    }


    /**
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense
     * different and with one counter with defense == attack and one temporary counter with defense != attack.
     */
    @Test
    void getAttack_different_counter_equal_temp_different() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addCounter("+2/+2");
        creature.addTempCounter(new Creature(),"+3/+2");
        assertEquals(creature.getAttack(), 10);
    }


    /**
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense
     * different and with one counter with defense != attack and one temporary counter with defense == attack.
     */
    @Test
    void getAttack_different_counter_different_temp_equal() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addCounter("+3/+2");
        creature.addTempCounter(new Creature(),"+2/+2");
        assertEquals(creature.getAttack(), 10);
    }


    /**
     *   Test for the attack function. Testing for the value of the attack with a creature with attack and defense
     * different and with a counter and a temporary counters with defense != attack.
     */
    @Test
    void getAttack_different_counter_different_temp_different() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addTempCounter(new Creature(), "+3/+1");
        creature.addTempCounter(new Creature(), "+3/+1");
        assertEquals(creature.getAttack(), 11);
    }


    /* -----------------------------------------------------------------------------------------------------------------
     * Tests for the getDefense() function
     * -----------------------------------------------------------------------------------------------------------------
     */

    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with defense and defense
     * equal.
     */
    @Test
    void getDefense_equal() {
        Creature creature = new Creature();
        assertEquals(creature.getDefense(), 0);
    }


    /**
     * Test for the getDefense function. Testing for the value of the defense with a creature with 0 defense.
     */
    @Test
    void getDefense_different() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        assertEquals(creature.getDefense(), 6);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * equal and with one counter with defense == attack.
     */
    @Test
    void getDefense_equal_1counter_equal() {
        Creature creature = new Creature();
        creature.addCounter("+1/+1");
        assertEquals(creature.getDefense(), 1);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * equal and with one counter with defense != attack.
     */
    @Test
    void getDefense_equal_1counter_different() {
        Creature creature = new Creature();
        creature.addCounter("+3/+2");
        assertEquals(creature.getDefense(), 2);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * different with one counter with defense == attack.
     */
    @Test
    void getDefense_different_1counter_equal() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addCounter("+2/+2");
        assertEquals(creature.getDefense(), 8);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * different and with one counter with defense != attack.
     */
    @Test
    void getDefense_different_1counter_different() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addCounter("+4/+2");
        assertEquals(creature.getDefense(), 8);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * equal and with two counters with defense == attack.
     */
    @Test
    void getDefense_equal_2counter_equal_equal() {
        Creature creature = new Creature();
        creature.addCounter("+1/+1");
        creature.addCounter("+2/+2");
        assertEquals(creature.getDefense(), 3);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * equal and with one counter with defense != attack and one counter with defense == attack.
     */
    @Test
    void getDefense_equal_1counter_different_equal() {
        Creature creature = new Creature();
        creature.addCounter("+3/+2");
        creature.addCounter("+2/+2");
        assertEquals(creature.getDefense(), 4);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * equal and with two counters with defense != attack.
     */
    @Test
    void getDefense_equal_2counter_different_different() {
        Creature creature = new Creature();
        creature.addCounter("+3/+1");
        creature.addCounter("+1/+5");
        assertEquals(creature.getDefense(), 6);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * different with two counters with defense == attack.
     */
    @Test
    void getDefense_different_2counter_equal_equal() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addCounter("+2/+2");
        creature.addCounter("+4/+4");
        assertEquals(creature.getDefense(), 12);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * different and with one counter with defense != attack and one counter with defense == attack.
     */
    @Test
    void getDefense_different_2counter_different_equal() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addCounter("+4/+2");
        creature.addCounter("+2/+2");
        assertEquals(creature.getDefense(), 10);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * different with two counters with defense != attack.
     */
    @Test
    void getDefense_different_2counter_different_different() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addCounter("+4/+2");
        creature.addCounter("+2/+4");
        assertEquals(creature.getDefense(), 12);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * equal and with one temporary counter with defense == attack.
     */
    @Test
    void getDefense_equal_1temp_equal() {
        Creature creature = new Creature();
        creature.addTempCounter(new Creature(), "+1/+1");
        assertEquals(creature.getDefense(), 1);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * equal and with one temporary counter with defense != attack.
     */
    @Test
    void getDefense_equal_1temp_different() {
        Creature creature = new Creature();
        creature.addTempCounter(new Creature(),"+3/+2");
        assertEquals(creature.getDefense(), 2);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * different with one temporary counter with defense == attack.
     */
    @Test
    void getDefense_different_1temp_equal() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addTempCounter(new Creature(),"+2/+2");
        assertEquals(creature.getDefense(), 8);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * different and with one temporary counter with defense != attack.
     */
    @Test
    void getDefense_different_1temp_different() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addTempCounter(new Creature(),"+4/+2");
        assertEquals(creature.getDefense(), 8);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * equal and with two temporary counters with defense == attack.
     */
    @Test
    void getDefense_equal_2temp_equal_equal() {
        Creature creature = new Creature();
        creature.addTempCounter(new Creature(), "+1/+1");
        creature.addTempCounter(new Creature(), "+1/+1");
        assertEquals(creature.getDefense(), 2);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * equal and with one temporary counter with defense != attack and one temporary counter with defense == attack.
     */
    @Test
    void getDefense_equal_1temp_equal_different() {
        Creature creature = new Creature();
        creature.addTempCounter(new Creature(),"+3/+2");
        creature.addTempCounter(new Creature(),"+2/+2");
        assertEquals(creature.getDefense(), 4);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * equal and with two temporary counters with defense != attack.
     */
    @Test
    void getDefense_equal_2temp_different_different() {
        Creature creature = new Creature();
        creature.addTempCounter(new Creature(), "+3/+1");
        creature.addTempCounter(new Creature(), "+3/+1");
        assertEquals(creature.getDefense(), 2);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * different and with two temporary counters with defense == attack.
     */
    @Test
    void getDefense_different_2temp_equal_equal() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addTempCounter(new Creature(), "+1/+1");
        creature.addTempCounter(new Creature(), "+1/+1");
        assertEquals(creature.getDefense(), 8);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * different and with one temporary counter with defense != attack and one temporary counter with defense == attack.
     */
    @Test
    void getDefense_different_1temp_equal_different() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addTempCounter(new Creature(),"+3/+2");
        creature.addTempCounter(new Creature(),"+2/+2");
        assertEquals(creature.getDefense(), 10);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * different and with two temporary counters with defense != attack.
     */
    @Test
    void getDefense_different_2temp_different_different() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addTempCounter(new Creature(), "+3/+1");
        creature.addTempCounter(new Creature(), "+3/+1");
        assertEquals(creature.getDefense(), 8);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * equal and with a counter and a temporary counter with defense == attack.
     */
    @Test
    void getDefense_equal_counter_equal_temp_equal() {
        Creature creature = new Creature();
        creature.addCounter("+1/+1");
        creature.addTempCounter(new Creature(), "+1/+1");
        assertEquals(creature.getDefense(), 2);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * equal and with one counter with defense == attack and one temporary counter with defense != attack.
     */
    @Test
    void getDefense_equal_counter_equal_temp_different() {
        Creature creature = new Creature();
        creature.addCounter("+2/+2");
        creature.addTempCounter(new Creature(),"+3/+2");
        assertEquals(creature.getDefense(), 4);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * equal and with one counter with defense != attack and one temporary counter with defense == attack.
     */
    @Test
    void getDefense_equal_counter_different_temp_equal() {
        Creature creature = new Creature();
        creature.addCounter("+3/+2");
        creature.addTempCounter(new Creature(),"+2/+2");
        assertEquals(creature.getDefense(), 4);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * equal and with a counter and a temporary counters with defense != attack.
     */
    @Test
    void getDefense_equal_counter_different_temp_different() {
        Creature creature = new Creature();
        creature.addTempCounter(new Creature(), "+3/+1");
        creature.addTempCounter(new Creature(), "+3/+1");
        assertEquals(creature.getDefense(), 2);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * different and with a counter and a temporary counter with defense == attack.
     */
    @Test
    void getDefense_different_counter_equal_temp_equal() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");;
        creature.addCounter("+1/+1");
        creature.addTempCounter(new Creature(), "+1/+1");
        assertEquals(creature.getDefense(), 8);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * different and with one counter with defense == attack and one temporary counter with defense != attack.
     */
    @Test
    void getDefense_different_counter_equal_temp_different() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addCounter("+2/+2");
        creature.addTempCounter(new Creature(),"+3/+2");
        assertEquals(creature.getDefense(), 10);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * different and with one counter with defense != attack and one temporary counter with defense == attack.
     */
    @Test
    void getDefense_different_counter_different_temp_equal() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addCounter("+3/+2");
        creature.addTempCounter(new Creature(),"+2/+2");
        assertEquals(creature.getDefense(), 10);
    }


    /**
     *   Test for the getDefense function. Testing for the value of the defense with a creature with attack and defense
     * different and with a counter and a temporary counters with defense != attack.
     */
    @Test
    void getDefense_different_counter_different_temp_different() {
        Creature creature = new Creature("Creature", "Red", "2", "", 5, 6,
                "Human");
        creature.addTempCounter(new Creature(), "+3/+1");
        creature.addTempCounter(new Creature(), "+3/+1");
        assertEquals(creature.getDefense(), 8);
    }


    /* -----------------------------------------------------------------------------------------------------------------
     * Tests for the getVigilance() function
     * -----------------------------------------------------------------------------------------------------------------
     */

    /**
     *   Test for the getVigilance function. It tests the getVigilance function when the creature doesn't have any
     * effects.
     */
    @Test
    void getVigilance_empty() {
        Creature creature = new Creature("Name", "No color", "0", "", 0, 1,
                "Human");
        assertFalse(creature.getVigilance());
    }


    /**
     *   Test for the getVigilance function. It tests the getVigilance function when the creature has effects but not
     * vigilance.
     */
    @Test
    void getVigilance_eff_not_vigilance() {
        Creature creature = new Creature("Name", "No color", "0", "Haste. Flying.", 0,
                1, "Human");
        assertFalse(creature.getVigilance());
    }


    /**
     *   Test for the getVigilance function. It tests the getVigilance function when the creature has vigilance.
     */
    @Test
    void getVigilance_vigilance() {
        Creature creature = new Creature("Name", "No color", "0", "Vigilance", 0,
                1, "Human");
        assertTrue(creature.getVigilance());
    }


    /**
     *   Test for the getVigilance function. It tests the getVigilance function when the creature has vigilance and also
     * other effects.
     */
    @Test
    void getVigilance_effect_vigilance_effect() {
        Creature creature = new Creature("Name", "No color", "0", "Haste. Vigilance." +
                "Double strike.", 0, 1, "Human");
        assertTrue(creature.getVigilance());
    }


    /**
     *   Test for the getVigilance function. It tests the getVigilance function when the creature has the word vigilance
     * in the effects but broken down.
     */
    @Test
    void getVigilance_Vigi_lance() {
        Creature creature = new Creature("Name", "No color", "0", "Haste. Vigi Rampage" +
                "lance. Double strike.", 0, 1, "Human");
        assertFalse(creature.getVigilance());
    }


    /* -----------------------------------------------------------------------------------------------------------------
     * Tests for the getDeathtouch() function
     * -----------------------------------------------------------------------------------------------------------------
     */

    @Test
    void getDeathtouch() {
        Creature creature = new Creature("Name", "No collor", "0", "", 0,1,
                "");
        assertFalse(creature.getDeathtouch());
    }


    /* -----------------------------------------------------------------------------------------------------------------
     * Tests for the getFlying() function
     * -----------------------------------------------------------------------------------------------------------------
     */

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