package life;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static org.junit.jupiter.api.Assertions.*;

class LifeTest {

    @Test
    void neighborOf_shouldReturnOnlyRealNeighbors() {
        var cells = new Life(Life.empty).neighborOf(new Cell(0, 0));

        assertThat(cells.size(), is(3));
        assertThat(cells, hasItem(new Cell(0, 1)));
        assertThat(cells, hasItem(new Cell(1, 1)));
        assertThat(cells, hasItem(new Cell(1, 0)));
    }
}