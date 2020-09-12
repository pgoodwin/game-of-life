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
}