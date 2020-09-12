package life;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class LifeTest {

    @Test
    void neighborsOf_shouldReturnOnlyRealNeighbors() {
        Life life = new Life(Life.empty);
        var cells = life.neighborsOf(new Cell(0, 0));

        assertThat(cells.size(), is(3));
        assertThat(cells, hasItem(new Cell(0, 1)));
        assertThat(cells, hasItem(new Cell(1, 1)));
        assertThat(cells, hasItem(new Cell(1, 0)));

        assertThat(life.neighborsOf(new Cell(1, 0)).size(), is(5));
        assertThat(life.neighborsOf(new Cell(1, 1)).size(), is(8));
        assertThat(life.neighborsOf(new Cell(2, 2)).size(), is(3));
    }

    @Test
    void example2() {
        var cells = new Life().iterate(Life.example2);
        assertThat(cells, equalTo(new char[][]{
                {'X', 'X', '_'},
                {'X', 'X', '_'},
                {'_', '_', '_'}
        }));
    }

    @Test
    void example3() {
        var cells = new Life().iterate(Life.example3);
        assertThat(cells, equalTo(new char[][]{
                {'X', 'X', 'X'},
                {'_', '_', '_'},
                {'_', '_', '_'}
        }));
    }

    @Test
    void example4() {
        var cells = new Life().iterate(Life.example4);
        assertThat(cells, equalTo(new char[][]{
                {'X', 'X', 'X'},
                {'X', 'X', 'X'},
                {'_', '_', '_'}
        }));
    }

    @Test
    void example4bigger() {
        var cells = new Life().iterate(new char[][]{
                {'_', '_', '_', '_', '_'},
                {'_', 'X', 'X', 'X', '_'},
                {'_', '_', 'X', '_', '_'},
                {'_', '_', '_', '_', '_'}
        });
        assertThat(cells, equalTo(new char[][]{
                {'_', '_', 'X', '_', '_'},
                {'_', 'X', 'X', 'X', '_'},
                {'_', 'X', 'X', 'X', '_'},
                {'_', '_', '_', '_', '_'}
        }));
    }
}