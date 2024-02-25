package ru.itmo.wp.web.page;

import ru.itmo.wp.model.Phase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Map;
import java.util.Objects;

import static java.lang.Math.max;
import static java.lang.Math.min;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class TicTacToePage {
    private static final int BOARD_SIZE = 3;
    private void action(HttpServletRequest request, Map<String, Object> view) {
        HttpSession session = request.getSession();
        if (Objects.isNull(session.getAttribute("state"))) {
            State state = new State(BOARD_SIZE);
            session.setAttribute("state", state);
        }
        State state = (State) session.getAttribute("state");
        view.put("state", state);
    }

    private void onMove(HttpServletRequest request, Map<String, Object> view) {
        HttpSession session = request.getSession();
        State state = (State) session.getAttribute("state");
        for (Map.Entry<String, String[]> parameter : request.getParameterMap().entrySet()) {
            String parameterKey = parameter.getKey();
            if (parameterKey.startsWith("cell_")) {
                state.move(Character.getNumericValue(parameterKey.charAt(parameterKey.length() - 2)),
                        Character.getNumericValue(parameterKey.charAt(parameterKey.length() - 1)));
            }
        }
        session.setAttribute("state", state);
        view.put("state", state);
    }

    private void newGame(HttpServletRequest request, Map<String, Object> view) {
        request.getSession().setAttribute("state", new State(BOARD_SIZE));
        view.put("state", new State(BOARD_SIZE));
    }

    public static class State {
        private final int size;
        private int emptyCells;
        private final Character[][] cells;
        private Phase phase;
        private boolean crossesMove;

        public State(int size) {
            this.size = size;
            cells = new Character[size][size];
            emptyCells = size * size;
            phase = Phase.RUNNING;
            crossesMove = true;
        }

        public int getSize() {
            return size;
        }
        public Character[][] getCells() {
            return cells;
        }

        public void move(int i, int j) {
            if (phase != Phase.RUNNING || max(i, j) >= size  || min(i, j) < 0) {
                return;
            }
            char value = (crossesMove ? 'X' : 'O');
            cells[i][j] = value;
            emptyCells--;
            checkWin(i, j);
            if (emptyCells == 0 && phase == Phase.RUNNING) {
                phase = Phase.DRAW;
            }
            crossesMove = !crossesMove;
        }
        private int checkBeam(int i, int j, int dr, int dc) {
            int count = 0;
            for (int d = 1; max(i + dr * d, j + dc * d) < size && min(i + dr * d, j + dc * d) >= 0; d++) {
                if (cells[i + dr * d][j + dc * d] == cells[i][j]) {
                    count++;
                }
            }
            return count;
        }

        private boolean checkLine(int i, int j, int dr, int dc) {
            return checkBeam(i, j, dr, dc) + checkBeam(i, j, -dr, -dc) == size - 1;
        }
        private void checkWin(int i, int j) {
            if (checkLine(i, j, 1, 0) || checkLine(i, j, 0, 1) ||
                checkLine(i, j, 1, 1) || checkLine(i, j, -1, 1)) {
                if (crossesMove) {
                    phase = Phase.WON_X;
                } else {
                    phase = Phase.WON_O;
                }
            }
        }

        public Phase getPhase() {
            return phase;
        }

        public boolean getCrossesMove() {
            return crossesMove;
        }
    }
}
