package baseball.controller;

import baseball.domain.BaseBallGame;
import baseball.domain.Referee;
import baseball.dto.ScoresDTO;
import baseball.dto.SelectedNumberForRestartDTO;
import baseball.dto.UserNumbersDTO;
import baseball.view.InputView;
import baseball.view.OutputView;

public class BaseBall {
    private static final int RESTART_NUMBER = 1;
    
    public void start() {
        OutputView.printApplicationStartMessage();
        playBaseBallGameUntilEnd();
    }
    
    private void playBaseBallGameUntilEnd() {
        int selectedNumberForRestart = RESTART_NUMBER;
        
        while (isBaseBallGameRestart(selectedNumberForRestart)) {
            playBaseBallGameOnce();
            selectedNumberForRestart = inputNumberForRestart();
        }
    }
    
    private static boolean isBaseBallGameRestart(final int selectedNumberForRestart) {
        return selectedNumberForRestart == RESTART_NUMBER;
    }
    
    private void playBaseBallGameOnce() {
        compareBallsRepeatedly(new BaseBallGame());
        OutputView.baseBallGameEndMessagePrint();
    }
    
    private void compareBallsRepeatedly(final BaseBallGame baseBallGame) {
        boolean isBaseBallGameEnd = false;
        
        while (!isBaseBallGameEnd) {
            final Referee referee = compareBallsOnce(baseBallGame);
            isBaseBallGameEnd = referee.isEnd();
        }
    }
    
    private Referee compareBallsOnce(final BaseBallGame baseBallGame) {
        final Referee referee = playResult(baseBallGame);
        OutputView.printResult(new ScoresDTO(referee));
        return referee;
    }
    
    private Referee playResult(final BaseBallGame baseBallGame) {
        final UserNumbersDTO userNumbersDTO = inputUserBalls();
        final String userNumbers = userNumbersDTO.getUserNumbers();
        
        return baseBallGame.play(userNumbers);
    }
    
    private UserNumbersDTO inputUserBalls() {
        return InputView.inputUserBalls();
    }
    
    private static int inputNumberForRestart() {
        SelectedNumberForRestartDTO selectedNumberForRestartDTO = InputView.inputNumberForRestart();
        return selectedNumberForRestartDTO.getSelectedNumberForRestart();
    }
}
