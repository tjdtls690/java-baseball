package baseball.view;

import baseball.dto.RefereeDTO;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OutputView {
    private static final String APPLICATION_START_MESSAGE = "숫자 야구 게임을 시작합니다.";
    private static final String UTILITY_CLASS_CREATE_EXCEPTION_MESSAGE = "생성할 수 없는 View 클래스입니다.";
    private static final String BASEBALL_GAME_END_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
    private static final String DELIMITER = " ";
    private static final String NOTHING_DISPLAY = "낫싱";
    private static final String BALL_DISPLAY = "볼";
    private static final String STRIKE_DISPLAY = "스트라이크";
    
    private OutputView() {
        throw new IllegalStateException(UTILITY_CLASS_CREATE_EXCEPTION_MESSAGE);
    }
    
    public static void printApplicationStartMessage() {
        System.out.println(APPLICATION_START_MESSAGE);
    }
    
    public static void printResult(final RefereeDTO refereeDTO) {
        final List<Integer> pitchResults = refereeDTO.getPitchResults();
        final List<String> ballStatusDisplay = List.of(BALL_DISPLAY, STRIKE_DISPLAY);
        
        if (isNothing(pitchResults)) {
            System.out.println(NOTHING_DISPLAY);
            return;
        }
        System.out.println(parsePlayResults(pitchResults, ballStatusDisplay));
    }
    
    private static boolean isNothing(final List<Integer> pitchResults) {
        return pitchResults.stream()
                .noneMatch(OutputView::isPitchResultNotZero);
    }
    
    private static boolean isPitchResultNotZero(final Integer pitchResult) {
        return pitchResult != 0;
    }
    
    private static String parsePlayResults(final List<Integer> pitchResults, final List<String> ballStatusDisplay) {
        return IntStream.rangeClosed(0, 1)
                .filter(resultIndex -> isPitchResultNotZero(pitchResults, resultIndex))
                .mapToObj(resultIndex -> pitchResults.get(resultIndex) + ballStatusDisplay.get(resultIndex))
                .collect(Collectors.joining(DELIMITER));
    }
    
    private static boolean isPitchResultNotZero(final List<Integer> pitchResults, final int resultIndex) {
        return pitchResults.get(resultIndex) != 0;
    }
    
    public static void baseBallGameEndMessagePrint() {
        System.out.println(BASEBALL_GAME_END_MESSAGE);
    }
}
