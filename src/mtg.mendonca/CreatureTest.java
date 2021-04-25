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
     * Tests for the getHaste() function
     * -----------------------------------------------------------------------------------------------------------------
     */

    /**
     *   Test for the getHaste function. It tests the getHaste function when the creature doesn't have any effects.
     */
    @Test
    void getHaste_empty() {
        Creature creature = new Creature("Name", "No color", "0", "", 0, 1,
                "Human");
        assertFalse(creature.getHaste());
    }


    /**
     *   Test for the getHaste function. It tests the getHaste function when the creature has effects but not haste.
     */
    @Test
    void getHaste_eff_not_Haste() {
        Creature creature = new Creature("Name", "No color", "0", "Flying. Trample.",
                0, 1, "Human");
        assertFalse(creature.getHaste());
    }


    /**
     *   Test for the getHaste function. It tests the getHaste function when the creature has haste.
     */
    @Test
    void getHaste_Haste() {
        Creature creature = new Creature("Name", "No color", "0", "Haste", 0,
                1, "Human");
        assertTrue(creature.getHaste());
    }


    /**
     *   Test for the getHaste function. It tests the getHaste function when the creature has haste and also other
     * effects.
     */
    @Test
    void getHaste_effect_Haste_effect() {
        Creature creature = new Creature("Name", "No color", "0", "Trample. Haste." +
                "Double strike.", 0, 1, "Human");
        assertTrue(creature.getHaste());
    }


    /**
     *   Test for the getHaste function. It tests the getHaste function when the creature has the word haste in the
     * effects but broken down.
     */
    @Test
    void getHaste_Has_te() {
        Creature creature = new Creature("Name", "No color", "0", "Trample. Has Rampage" +
                "te. Double strike.", 0, 1, "Human");
        assertFalse(creature.getHaste());
    }


    /**
     *   Test for the getHaste function. It tests the getHaste function when the creature has the word haste in the
     * effects but it's all in lowercase.
     */
    @Test
    void getHaste_Haste_lower() {
        Creature creature = new Creature("Name", "No color", "0", "Flying and haste." +
                "Double strike.", 0, 1, "Human");
        assertTrue(creature.getHaste());
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


    /**
     *   Test for the getVigilance function. It tests the getVigilance function when the creature has the word vigilance
     * in the effects but it's all in lowercase.
     */
    @Test
    void getVigilance_Vigilance_lower() {
        Creature creature = new Creature("Name", "No color", "0", "Haste and vigilance" +
                ". Double strike.", 0, 1, "Human");
        assertTrue(creature.getVigilance());
    }


    /* -----------------------------------------------------------------------------------------------------------------
     * Tests for the getDeathtouch() function
     * -----------------------------------------------------------------------------------------------------------------
     */

    /**
     *   Test for the getDeathtouch function. It tests the getDeathtouch function when the creature doesn't have any
     * effects.
     */
    @Test
    void getDeathtouch_empty() {
        Creature creature = new Creature("Name", "No color", "0", "", 0, 1,
                "Human");
        assertFalse(creature.getDeathtouch());
    }


    /**
     *   Test for the getDeathtouch function. It tests the getDeathtouch function when the creature has effects but not
     * deathtouch.
     */
    @Test
    void getDeathtouch_eff_not_Deathtouch() {
        Creature creature = new Creature("Name", "No color", "0", "Haste. Flying.", 0,
                1, "Human");
        assertFalse(creature.getDeathtouch());
    }


    /**
     *   Test for the getDeathtouch function. It tests the getDeathtouch function when the creature has deathtouch.
     */
    @Test
    void getDeathtouch_Deathtouch() {
        Creature creature = new Creature("Name", "No color", "0", "Deathtouch", 0,
                1, "Human");
        assertTrue(creature.getDeathtouch());
    }


    /**
     *   Test for the getDeathtouch function. It tests the getDeathtouch function when the creature has deathtouch and
     * also other effects.
     */
    @Test
    void getDeathtouch_effect_Deathtouch_effect() {
        Creature creature = new Creature("Name", "No color", "0", "Haste. Deathtouch." +
                "Double strike.", 0, 1, "Human");
        assertTrue(creature.getDeathtouch());
    }


    /**
     *   Test for the getDeathtouch function. It tests the getDeathtouch function when the creature has the word
     * deathtouch in the effects but broken down.
     */
    @Test
    void getDeathtouch_Death_touch() {
        Creature creature = new Creature("Name", "No color", "0", "Haste. Death Rampage" +
                "touch. Double strike.", 0, 1, "Human");
        assertFalse(creature.getDeathtouch());
    }


    /**
     *   Test for the getDeathtouch function. It tests the getDeathtouch function when the creature has the word
     * deathtouch in the effects but it's all in lowercase.
     */
    @Test
    void getDeathtouch_Deathtouch_lower() {
        Creature creature = new Creature("Name", "No color", "0", "Haste and deathtouch" +
                ". Double strike.", 0, 1, "Human");
        assertTrue(creature.getDeathtouch());
    }


    /* -----------------------------------------------------------------------------------------------------------------
     * Tests for the getFlying() function
     * -----------------------------------------------------------------------------------------------------------------
     */

    /**
     *   Test for the getFlying function. It tests the getFlying function when the creature doesn't have any
     * effects.
     */
    @Test
    void getFlying_empty() {
        Creature creature = new Creature("Name", "No color", "0", "", 0, 1,
                "Human");
        assertFalse(creature.getFlying());
    }


    /**
     *   Test for the getFlying function. It tests the getFlying function when the creature has effects but not flying.
     */
    @Test
    void getFlying_eff_not_Flying() {
        Creature creature = new Creature("Name", "No color", "0", "Haste. Trample.",
                0, 1, "Human");
        assertFalse(creature.getFlying());
    }


    /**
     *   Test for the getFlying function. It tests the getFlying function when the creature has flying.
     */
    @Test
    void getFlying_Flying() {
        Creature creature = new Creature("Name", "No color", "0", "Flying", 0,
                1, "Human");
        assertTrue(creature.getFlying());
    }


    /**
     *   Test for the getFlying function. It tests the getFlying function when the creature has flying and also other
     * effects.
     */
    @Test
    void getFlying_effect_Flying_effect() {
        Creature creature = new Creature("Name", "No color", "0", "Haste. Flying." +
                "Double strike.", 0, 1, "Human");
        assertTrue(creature.getFlying());
    }


    /**
     *   Test for the getFlying function. It tests the getFlying function when the creature has the word flying in the
     * effects but broken down.
     */
    @Test
    void getFlying_Fly_ing() {
        Creature creature = new Creature("Name", "No color", "0", "Haste. Fly Rampage" +
                "ing. Double strike.", 0, 1, "Human");
        assertFalse(creature.getFlying());
    }


    /**
     *   Test for the getFlying function. It tests the getFlying function when the creature has the word flying in the
     * effects but it's all in lowercase.
     */
    @Test
    void getFlying_Flying_lower() {
        Creature creature = new Creature("Name", "No color", "0", "Haste and flying" +
                ". Double strike.", 0, 1, "Human");
        assertTrue(creature.getFlying());
    }


    /* -----------------------------------------------------------------------------------------------------------------
     * Tests for the getReach() function
     * -----------------------------------------------------------------------------------------------------------------
     */

    /**
     *   Test for the getReach function. It tests the getReach function when the creature doesn't have any effects.
     */
    @Test
    void getReach_empty() {
        Creature creature = new Creature("Name", "No color", "0", "", 0, 1,
                "Human");
        assertFalse(creature.getReach());
    }


    /**
     *   Test for the getReach function. It tests the getReach function when the creature has effects but not reach.
     */
    @Test
    void getReach_eff_not_Reach() {
        Creature creature = new Creature("Name", "No color", "0", "Haste. Trample.",
                0, 1, "Human");
        assertFalse(creature.getReach());
    }


    /**
     *   Test for the getReach function. It tests the getReach function when the creature has reach.
     */
    @Test
    void getReach_Reach() {
        Creature creature = new Creature("Name", "No color", "0", "Reach", 0,
                1, "Human");
        assertTrue(creature.getReach());
    }


    /**
     *   Test for the getReach function. It tests the getReach function when the creature has reach and also other
     * effects.
     */
    @Test
    void getReach_effect_Reach_effect() {
        Creature creature = new Creature("Name", "No color", "0", "Haste. Reach." +
                "Double strike.", 0, 1, "Human");
        assertTrue(creature.getReach());
    }


    /**
     *   Test for the getReach function. It tests the getReach function when the creature has the word reach in the
     * effects but broken down.
     */
    @Test
    void getReach_Re_ach() {
        Creature creature = new Creature("Name", "No color", "0", "Haste. Re Rampage" +
                "ach. Double strike.", 0, 1, "Human");
        assertFalse(creature.getReach());
    }


    /**
     *   Test for the getReach function. It tests the getReach function when the creature has the word reach in the
     * effects but it's all in lowercase.
     */
    @Test
    void getReach_Reach_lower() {
        Creature creature = new Creature("Name", "No color", "0", "Haste and reach." +
                "Double strike.", 0, 1, "Human");
        assertTrue(creature.getReach());
    }


    /* -----------------------------------------------------------------------------------------------------------------
     * Tests for the getFirstStrike() function
     * -----------------------------------------------------------------------------------------------------------------
     */

    /**
     *   Test for the getFirstStrike function. It tests the getFirstStrike function when the creature doesn't have any
     * effects.
     */
    @Test
    void getFirstStrike_empty() {
        Creature creature = new Creature("Name", "No color", "0", "", 0, 1,
                "Human");
        assertFalse(creature.getFirstStrike());
    }


    /**
     *   Test for the getFirstStrike function. It tests the getFirstStrike function when the creature has effects but
     * not first strike.
     */
    @Test
    void getFirstStrike_eff_not_FirstStrike() {
        Creature creature = new Creature("Name", "No color", "0", "Haste. Flying.", 0,
                1, "Human");
        assertFalse(creature.getFirstStrike());
    }


    /**
     *   Test for the getFirstStrike function. It tests the getFirstStrike function when the creature has first strike.
     */
    @Test
    void getFirstStrike_FirstStrike() {
        Creature creature = new Creature("Name", "No color", "0", "First Strike", 0,
                1, "Human");
        assertTrue(creature.getFirstStrike());
    }


    /**
     *   Test for the getFirstStrike function. It tests the getFirstStrike function when the creature has first strike
     * and also other effects.
     */
    @Test
    void getFirstStrike_effect_FirstStrike_effect() {
        Creature creature = new Creature("Name", "No color", "0", "Haste. First Strike." +
                "Double strike.", 0, 1, "Human");
        assertTrue(creature.getFirstStrike());
    }


    /**
     *   Test for the getFirstStrike function. It tests the getFirstStrike function when the creature has the word
     * first strike in the effects but broken down.
     */
    @Test
    void getFirstStrike_Vigi_lance() {
        Creature creature = new Creature("Name", "No color", "0", "Haste. First Rampage" +
                "Strike. Double strike.", 0, 1, "Human");
        assertFalse(creature.getFirstStrike());
    }


    /**
     *   Test for the getFirstStrike function. It tests the getFirstStrike function when the creature has the word
     * first strike in the effects but it's the first letter of the first word is in uppercase and the first letter of
     * the last word is in lowercase.
     */
    @Test
    void getFirstStrike_FirstStrike_upper_lower() {
        Creature creature = new Creature("Name", "No color", "0", "Haste and First strike" +
                ". Double strike.", 0, 1, "Human");
        assertTrue(creature.getFirstStrike());
    }


    /**
     *   Test for the getFirstStrike function. It tests the getFirstStrike function when the creature has the word
     * first strike in the effects but it's all in lowercase.
     */
    @Test
    void getFirstStrike_FirstStrike_lower() {
        Creature creature = new Creature("Name", "No color", "0", "Haste and first strike" +
                ". Double strike.", 0, 1, "Human");
        assertTrue(creature.getFirstStrike());
    }


    /* -----------------------------------------------------------------------------------------------------------------
     * Tests for the getDoubleStrike() function
     * -----------------------------------------------------------------------------------------------------------------
     */

    @Test
    void getDoubleStrike() {
    }


    /* -----------------------------------------------------------------------------------------------------------------
     * Tests for the getFlash() function
     * -----------------------------------------------------------------------------------------------------------------
     */

    @Test
    void getFlash() {
    }


    /* -----------------------------------------------------------------------------------------------------------------
     * Tests for the alterDefense() function
     * -----------------------------------------------------------------------------------------------------------------
     */

    @Test
    void alterDefense() {
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